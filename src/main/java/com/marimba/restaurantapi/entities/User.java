package com.marimba.restaurantapi.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    //segunda prueba
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;


    @Column(name = "NAME", unique = false, nullable = false)
    private String name;

    @Column(name = "LAST_NAME", unique = false, nullable = true)
    private String lastName;

    @Column(name = "EMAIL", unique = false, nullable = false)
    private String email;

    @Column(name = "PASSWORD", unique = false, nullable = false)
    private String password;

    @Column(name = "ROL_ID", unique = false, nullable = true)
    private String rolId;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Reservation> reservations;
*/



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }

    /*public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }*/
}
