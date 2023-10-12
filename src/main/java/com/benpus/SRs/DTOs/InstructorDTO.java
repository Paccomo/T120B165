package com.benpus.SRs.DTOs;

public class InstructorDTO {
    private Integer id;
    private String name;
    private String surname;
    private ShootingRangeMinimalDTO fk_shootingRange;

    public InstructorDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ShootingRangeMinimalDTO getFk_shootingRange() {
        return fk_shootingRange;
    }

    public void setFk_shootingRange(ShootingRangeMinimalDTO fk_shootingRange) {
        this.fk_shootingRange = fk_shootingRange;
    }
}
