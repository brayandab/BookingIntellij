package com.marimba.restaurantapi.services.impl;

import com.marimba.restaurantapi.dtos.EmailTemplateDto;
import com.marimba.restaurantapi.entities.Notification;
import com.marimba.restaurantapi.exceptions.InternalServerErrorException;
import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.exceptions.NotFountException;
import com.marimba.restaurantapi.repositories.NotificationRepository;
import com.marimba.restaurantapi.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    //Proceso para mandar el email
    @Override
    public String processSendEmail(String receiver, String templateCode, String currentName)
            throws MarimbaException {

        final EmailTemplateDto emailTemplateDto = findTemplateAndReplace(templateCode,currentName);

        this.sendEmail(receiver,emailTemplateDto.getSubject(),emailTemplateDto.getTemplate());
        return "EMAIL_SENT";
    }

    private void sendEmail(String receiver,final String subject, final String template)
            throws MarimbaException{
        final MimeMessage email = javaMailSender.createMimeMessage();
        final MimeMessageHelper content;
        try{
            content = new MimeMessageHelper(email, true);
            content.setSubject(subject);
            content.setTo(receiver);
            content.setText(template,true);
        }catch (Exception e){
            LOGGER.error("INTERNAL_SERVER_ERROR",e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        javaMailSender.send(email);
    }
    private EmailTemplateDto findTemplateAndReplace(final String templateCode, final String currentName) throws MarimbaException{

        final Notification notificationTemplate = notificationRepository.findByTemplateCode(templateCode)
                .orElseThrow(()-> new NotFountException("TEMPLATE_NOT_FOUNT", "CODE_TEMPLATE_NOT_FOUNT"));

        final EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
            emailTemplateDto.setSubject(notificationTemplate.getTemplateCode());
            emailTemplateDto.setTemplate(notificationTemplate.getTemplate().replaceAll("\\{"+"name"+"\\}",currentName));
        return emailTemplateDto;
    }

}
