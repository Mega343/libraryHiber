package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "genre", uniqueConstraints={@UniqueConstraint(columnNames={"genre"})})
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreID;
    @NotNull
    @Column(name = "genre")
    private String genre;

    public Genre() {
    }

    public Genre(Integer genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreID=" + genreID +
                ", genre='" + genre + '\'' +
                '}';
    }
}
