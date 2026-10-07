package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ownerId")
    private long ownerId;

    @Column(name = "teamId")
    private Long teamId;

    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;


    public long getOwnerId() {
        return ownerId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
