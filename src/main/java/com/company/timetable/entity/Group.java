package com.company.timetable.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "GROUP_", indexes = {
        @Index(name = "IDX_GROUP_SCHEDULE_ID", columnList = "SCHEDULE_ID")
})
@Entity(name = "Group_")
public class Group {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @JoinTable(name = "GROUP_STUDENT_LINK",
            joinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Student> student;
    @JoinColumn(name = "SCHEDULE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CCUClasses schedule;

    public CCUClasses getSchedule() {
        return schedule;
    }

    public void setSchedule(CCUClasses schedule) {
        this.schedule = schedule;
    }

    public List<Student> getStudent() {
        return student;
    }


    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}