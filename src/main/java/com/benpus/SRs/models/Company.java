package com.benpus.SRs.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    private String postalCode;
    @Column(nullable = false)
    private String city;
    private String phoneNumber;
    private String email;
    private String website;
    @Column(nullable = false)
    private Boolean isApproved;
    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User fk_user;


    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public User getFk_user() {
        return fk_user;
    }

    public void setFk_user(User fk_user) {
        this.fk_user = fk_user;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(address, company.address) && Objects.equals(postalCode, company.postalCode) && Objects.equals(city, company.city) && Objects.equals(phoneNumber, company.phoneNumber) && Objects.equals(email, company.email) && Objects.equals(website, company.website) && Objects.equals(isApproved, company.isApproved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, postalCode, city, phoneNumber, email, website, isApproved);
    }
}
