package com.company.timetable.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "CCU_CLASSES", indexes = {
        @Index(name = "IDX_CCUCLASSES_TEACHER_ID", columnList = "TEACHER_ID"),
        @Index(name = "IDX_CCUCLASSES_CLASSES_ID", columnList = "CLASSES_ID")
})
@Entity
public class CCUClasses {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "DAY_", nullable = false)
    @NotNull
    private LocalDate day;

    @Column(name = "TIME_", nullable = false)
    @NotNull
    private LocalTime time;

    @JoinColumn(name = "CLASSES_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Auditorium classes;

    @JoinColumn(name = "TEACHER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Teacher teacher;

    @JoinTable(name = "CCU_CLASSES_GROUP_LINK",
            joinColumns = @JoinColumn(name = "C_C_U_CLASSES_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Group> group;

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public List<Group> getGroup() {
        return group;
    }

    public Auditorium getClasses() {
        return classes;
    }

    public void setClasses(Auditorium classes) {
        this.classes = classes;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}