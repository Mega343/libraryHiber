package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "publishing_house", uniqueConstraints={@UniqueConstraint(columnNames={"publishing_house_name"})})
public class PublishingHouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publishing_house_id")
    private Integer publishingHouseID;
    @NotNull
    @Column(name = "publishing_house_name")
    private String publishingHouseName;

    public PublishingHouse() {
    }

    public PublishingHouse(Integer publishingHouseID, String publishingHouseName) {
        this.publishingHouseID = publishingHouseID;
        this.publishingHouseName = publishingHouseName;
    }

    public Integer getPublishingHouseID() {
        return publishingHouseID;
    }

    public void setPublishingHouseID(Integer publishingHouseID) {
        this.publishingHouseID = publishingHouseID;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public void setPublishingHouseName(String publishingHouseName) {
        this.publishingHouseName = publishingHouseName;
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "publishingHouseID=" + publishingHouseID +
                ", publishingHouseName='" + publishingHouseName + '\'' +
                '}';
    }
}
