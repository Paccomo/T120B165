package com.benpus.srs.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "BusinessHours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private LocalTime monOpen;
    @Column()
    private LocalTime monClose;
    @Column()
    private LocalTime tueOpen;
    @Column()
    private LocalTime tueClose;
    @Column()
    private LocalTime wedOpen;
    @Column()
    private LocalTime wedClose;
    @Column()
    private LocalTime thurOpen;
    @Column()
    private LocalTime thurClose;
    @Column()
    private LocalTime friOpen;
    @Column()
    private LocalTime friClose;
    @Column()
    private LocalTime satOpen;
    @Column()
    private LocalTime satClose;
    @Column()
    private LocalTime sunOpen;
    @Column()
    private LocalTime sunClose;

    public BusinessHours() {
    }

    public Integer getId() {
        return id;
    }

    public LocalTime getMonOpen() {
        return monOpen;
    }

    public void setMonOpen(LocalTime monOpen) {
        this.monOpen = monOpen;
    }

    public LocalTime getMonClose() {
        return monClose;
    }

    public void setMonClose(LocalTime monClose) {
        this.monClose = monClose;
    }

    public LocalTime getTueOpen() {
        return tueOpen;
    }

    public void setTueOpen(LocalTime tueOpen) {
        this.tueOpen = tueOpen;
    }

    public LocalTime getTueClose() {
        return tueClose;
    }

    public void setTueClose(LocalTime tueClose) {
        this.tueClose = tueClose;
    }

    public LocalTime getWedOpen() {
        return wedOpen;
    }

    public void setWedOpen(LocalTime wedOpen) {
        this.wedOpen = wedOpen;
    }

    public LocalTime getWedClose() {
        return wedClose;
    }

    public void setWedClose(LocalTime wedClose) {
        this.wedClose = wedClose;
    }

    public LocalTime getThurOpen() {
        return thurOpen;
    }

    public void setThurOpen(LocalTime thurOpen) {
        this.thurOpen = thurOpen;
    }

    public LocalTime getThurClose() {
        return thurClose;
    }

    public void setThurClose(LocalTime thurClose) {
        this.thurClose = thurClose;
    }

    public LocalTime getFriOpen() {
        return friOpen;
    }

    public void setFriOpen(LocalTime friOpen) {
        this.friOpen = friOpen;
    }

    public LocalTime getFriClose() {
        return friClose;
    }

    public void setFriClose(LocalTime friClose) {
        this.friClose = friClose;
    }

    public LocalTime getSatOpen() {
        return satOpen;
    }

    public void setSatOpen(LocalTime satOpen) {
        this.satOpen = satOpen;
    }

    public LocalTime getSatClose() {
        return satClose;
    }

    public void setSatClose(LocalTime satClose) {
        this.satClose = satClose;
    }

    public LocalTime getSunOpen() {
        return sunOpen;
    }

    public void setSunOpen(LocalTime sunOpen) {
        this.sunOpen = sunOpen;
    }

    public LocalTime getSunClose() {
        return sunClose;
    }

    public void setSunClose(LocalTime sunClose) {
        this.sunClose = sunClose;
    }

    @Override
    public String toString() {
        return "BuisinessHours{" +
                "id=" + id +
                ", monOpen=" + monOpen +
                ", monClose=" + monClose +
                ", tueOpen=" + tueOpen +
                ", tueClose=" + tueClose +
                ", wedOpen=" + wedOpen +
                ", wedClose=" + wedClose +
                ", thurOpen=" + thurOpen +
                ", thurClose=" + thurClose +
                ", friOpen=" + friOpen +
                ", friClose=" + friClose +
                ", satOpen=" + satOpen +
                ", satClose=" + satClose +
                ", sunOpen=" + sunOpen +
                ", sunClose=" + sunClose +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessHours that = (BusinessHours) o;
        return Objects.equals(monOpen, that.monOpen) && Objects.equals(monClose, that.monClose) && Objects.equals(tueOpen, that.tueOpen) && Objects.equals(tueClose, that.tueClose) && Objects.equals(wedOpen, that.wedOpen) && Objects.equals(wedClose, that.wedClose) && Objects.equals(thurOpen, that.thurOpen) && Objects.equals(thurClose, that.thurClose) && Objects.equals(friOpen, that.friOpen) && Objects.equals(friClose, that.friClose) && Objects.equals(satOpen, that.satOpen) && Objects.equals(satClose, that.satClose) && Objects.equals(sunOpen, that.sunOpen) && Objects.equals(sunClose, that.sunClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monOpen, monClose, tueOpen, tueClose, wedOpen, wedClose, thurOpen, thurClose, friOpen, friClose, satOpen, satClose, sunOpen, sunClose);
    }
}
