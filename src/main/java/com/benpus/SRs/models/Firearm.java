package com.benpus.SRs.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Firearms")
public class Firearm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String caliber;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "fk_shootingRange", nullable = false)
    private ShootingRange fkShootingRange;
    private String picture;

    public Firearm() {
    }

    public ShootingRange getFk_shootingRange() {
        return fkShootingRange;
    }

    public void setFk_shootingRange(ShootingRange fk_shootingRange) {
        this.fkShootingRange = fk_shootingRange;
    }

    public String getCaliber() {
        return caliber;
    }

    public Integer getId() {
        return id;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Firearms{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firearm firearms = (Firearm) o;
        return Objects.equals(id, firearms.id) && Objects.equals(manufacturer, firearms.manufacturer) && Objects.equals(model, firearms.model) && Objects.equals(price, firearms.price) && Objects.equals(picture, firearms.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, price, picture);
    }
}
