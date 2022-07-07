package com.company.timetable.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "AUDITORIUM")
@Entity
public class Auditorium {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "IS_LECTURE", nullable = false)
    @NotNull
    private Boolean isLecture = false;

    @Column(name = "IS_LABORATOIUM", nullable = false)
    @NotNull
    private Boolean isLaboratoium = false;

    @Column(name = "CAPACITY")
    private String capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Boolean getIsLaboratoium() {
        return isLaboratoium;
    }

    public void setIsLaboratoium(Boolean isLaboratoium) {
        this.isLaboratoium = isLaboratoium;
    }

    public Boolean getIsLecture() {
        return isLecture;
    }

    public void setIsLecture(Boolean isLecture) {
        this.isLecture = isLecture;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}