package com.benpus.SRs.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "ShootingRanges")
public class ShootingRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Boolean isOutdoor;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private int maxShooters;
    @ManyToOne
    @JoinColumn(name = "fk_company", nullable = false)
    private Company fkCompany;
    @ManyToOne
    @JoinColumn(name = "fk_hours", nullable = false)
    private BusinessHours fk_hours;

    public ShootingRange() {
    }

    public Integer getId() {
        return id;
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

    public boolean isOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(boolean outdoor) {
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

    public Boolean getOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(Boolean outdoor) {
        isOutdoor = outdoor;
    }

    public Company getFk_company() {
        return fkCompany;
    }

    public void setFk_company(Company fk_company) {
        this.fkCompany = fk_company;
    }

    public BusinessHours getFk_hours() {
        return fk_hours;
    }

    public void setFk_hours(BusinessHours fk_hours) {
        this.fk_hours = fk_hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShootingRange that = (ShootingRange) o;
        return isOutdoor == that.isOutdoor && maxShooters == that.maxShooters && Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, isOutdoor, price, maxShooters);
    }

    @Override
    public String toString() {
        return "ShootingRanges{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", isOutdoor=" + isOutdoor +
                ", price=" + price +
                ", maxShooters=" + maxShooters +
                '}';
    }
}
