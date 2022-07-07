package com.company.timetable.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "GROUP_")
@Entity(name = "Group_")
public class Group {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "group", optional = false)
    private CCUClasses cCUClasses;

    public CCUClasses getCCUClasses() {
        return cCUClasses;
    }

    public void setCCUClasses(CCUClasses cCUClasses) {
        this.cCUClasses = cCUClasses;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}