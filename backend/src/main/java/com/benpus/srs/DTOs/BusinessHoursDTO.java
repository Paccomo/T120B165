package com.benpus.srs.DTOs;

import java.time.LocalTime;

public class BusinessHoursDTO {
    private LocalTime monOpen;
    private LocalTime monClose;
    private LocalTime tueOpen;
    private LocalTime tueClose;
    private LocalTime wedOpen;
    private LocalTime wedClose;
    private LocalTime thurOpen;
    private LocalTime thurClose;
    private LocalTime friOpen;
    private LocalTime friClose;
    private LocalTime satOpen;
    private LocalTime satClose;
    private LocalTime sunOpen;
    private LocalTime sunClose;

    public BusinessHoursDTO() {
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
}
