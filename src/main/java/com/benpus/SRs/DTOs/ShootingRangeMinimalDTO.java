package com.benpus.SRs.DTOs;

public class ShootingRangeMinimalDTO {
    private Integer id;
    private String address;
    private String city;
    private CompanyMinimalDTO fk_company;

    public ShootingRangeMinimalDTO() {
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

    public CompanyMinimalDTO getFk_company() {
        return fk_company;
    }

    public void setFk_company(CompanyMinimalDTO fk_company) {
        this.fk_company = fk_company;
    }
}
