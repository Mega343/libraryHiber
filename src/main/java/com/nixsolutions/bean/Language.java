package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "language", uniqueConstraints={@UniqueConstraint(columnNames={"language"})})
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageID;
    @NotNull
    @Column(name = "language")
    private String language;

    public Language() {
    }

    public Language(Integer languageID, String language) {
        this.languageID = languageID;
        this.language = language;
    }

    public Integer getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageID=" + languageID +
                ", language='" + language + '\'' +
                '}';
    }
}
