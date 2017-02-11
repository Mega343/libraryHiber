package com.nixsolutions.bean;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "shelf")
public class Shelf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    private Integer shelfID;
    @NotNull
    @Column(name = "shelf_number")
    private Integer shelfNumber;

    public Shelf() {
    }

    public Shelf(Integer shelfID, Integer shelfNumber) {
        this.shelfID = shelfID;
        this.shelfNumber = shelfNumber;
    }

    public Integer getShelfID() {
        return shelfID;
    }

    public void setShelfID(Integer shelfID) {
        this.shelfID = shelfID;
    }

    public Integer getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(Integer shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfID=" + shelfID +
                ", shelfNumber=" + shelfNumber +
                '}';
    }
}
