package com.benpus.srs.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "FirearmReservations")
public class FirearmReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_firearm", nullable = false)
    private Firearm fk_firearm;
    @ManyToOne
    @JoinColumn(name = "fk_reservation", nullable = false)
    private Reservation fk_reservation;

    public FirearmReservation() {
    }

    public Firearm getFk_firearm() {
        return fk_firearm;
    }

    public void setFk_firearm(Firearm fk_firearm) {
        this.fk_firearm = fk_firearm;
    }

    public Reservation getFk_reservation() {
        return fk_reservation;
    }

    public void setFk_reservation(Reservation fk_reservation) {
        this.fk_reservation = fk_reservation;
    }

    @Override
    public String toString() {
        return "FirearmReservations{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirearmReservation that = (FirearmReservation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
