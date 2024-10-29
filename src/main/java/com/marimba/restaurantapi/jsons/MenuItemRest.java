package com.marimba.restaurantapi.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemRest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("unit")
    private Long unit;

    @JsonProperty("vat")
    private Double vat;

    @JsonProperty("discount")
    private Double discount;

    @JsonProperty("subTotal")
    private Double subTotal;


    @JsonProperty("total")
    private Double total;

    @JsonProperty("menu")
    private MenuRest menu;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public MenuRest getMenu() {
        return menu;
    }

    public void setMenu(MenuRest menu) {
        this.menu = menu;
    }
}
