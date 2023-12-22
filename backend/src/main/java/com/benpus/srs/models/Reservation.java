package com.benpus.srs.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime timeFrom;
    @Column(nullable = false)
    private LocalDateTime timeTo;
    @ManyToOne
    @JoinColumn(name = "fk_shootingRange", nullable = false)
    private ShootingRange fk_shootingRange;
    @ManyToOne
    @JoinColumn(name = "fk_instructor", nullable = false)
    private Instructor fk_instructor;

    public Reservation() {
    }

    public Instructor getFk_instructor() {
        return fk_instructor;
    }

    public void setFk_instructor(Instructor fk_instructor) {
        this.fk_instructor = fk_instructor;
    }

    public ShootingRange getFk_shootingRange() {
        return fk_shootingRange;
    }

    public void setFk_shootingRange(ShootingRange fk_shootingRange) {
        this.fk_shootingRange = fk_shootingRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(timeFrom, that.timeFrom) && Objects.equals(timeTo, that.timeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber, email, timeFrom, timeTo);
    }
}
