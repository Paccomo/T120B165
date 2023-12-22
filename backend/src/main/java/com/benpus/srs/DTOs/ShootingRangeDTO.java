package com.benpus.srs.DTOs;

public class ShootingRangeDTO {
    private Integer id;
    private String address;
    private String city;
    private Boolean isOutdoor;
    private Double price;
    private int maxShooters;
    private CompanyMinimalDTO fk_company;
    private BusinessHoursDTO fk_hours;

    public ShootingRangeDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(Boolean outdoor) {
        isOutdoor = outdoor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMaxShooters() {
        return maxShooters;
    }

    public void setMaxShooters(int maxShooters) {
        this.maxShooters = maxShooters;
    }

    public CompanyMinimalDTO getFk_company() {
        return fk_company;
    }

    public void setFk_company(CompanyMinimalDTO fk_company) {
        this.fk_company = fk_company;
    }

    public BusinessHoursDTO getFk_hours() {
        return fk_hours;
    }

    public void setFk_hours(BusinessHoursDTO fk_hours) {
        this.fk_hours = fk_hours;
    }
}
