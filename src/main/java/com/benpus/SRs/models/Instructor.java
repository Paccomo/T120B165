package com.benpus.SRs.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @ManyToOne
    @JoinColumn(name = "fk_shootingRange", nullable = false)
    private ShootingRange fkShootingRange;

    public Instructor() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ShootingRange getFk_shootingRange() {
        return fkShootingRange;
    }

    public void setFk_shootingRange(ShootingRange fk_shootingRange) {
        this.fkShootingRange = fk_shootingRange;
    }

    @Override
    public String toString() {
        return "Instructors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
