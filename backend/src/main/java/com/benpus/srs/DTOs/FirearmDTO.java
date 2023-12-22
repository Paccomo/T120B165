package com.benpus.srs.DTOs;

public class FirearmDTO {
    private Integer id;
    private String manufacturer;
    private String model;
    private String caliber;
    private Double price;
    private ShootingRangeMinimalDTO fk_shootingRange;
    private String picture;

    public FirearmDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShootingRangeMinimalDTO getFk_shootingRange() {
        return fk_shootingRange;
    }

    public void setFk_shootingRange(ShootingRangeMinimalDTO fk_shootingRange) {
        this.fk_shootingRange = fk_shootingRange;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
