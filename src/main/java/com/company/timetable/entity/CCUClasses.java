package com.company.timetable.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@JmixEntity
@Table(name = "CCU_CLASSES", indexes = {
        @Index(name = "IDX_CCUCLASSES_AUDITORIUM_ID", columnList = "AUDITORIUM_ID"),
        @Index(name = "IDX_CCUCLASSES_TEACHER_ID", columnList = "TEACHER_ID"),
        @Index(name = "IDX_CCUCLASSES_GROUP_ID", columnList = "GROUP_ID")
})
@Entity
public class CCUClasses {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "DAY_", nullable = false)
    @NotNull
    private LocalDate day;

    @Column(name = "TIME_", nullable = false)
    @NotNull
    private LocalTime time;

    @JoinColumn(name = "AUDITORIUM_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Auditorium auditorium;

    @JoinColumn(name = "TEACHER_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Teacher teacher;

    @JoinColumn(name = "GROUP_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
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