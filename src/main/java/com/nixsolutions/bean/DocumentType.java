package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "document_type", uniqueConstraints={@UniqueConstraint(columnNames={"document_type"})})
public class DocumentType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id")
    private Integer documentTypeID;
    @NotNull
    @Column(name = "document_type")
    private String documentType;

    public DocumentType() {
    }

    public DocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentTypeID() {
        return documentTypeID;
    }

    public void setDocumentTypeID(Integer documentTypeID) {
        this.documentTypeID = documentTypeID;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "documentTypeID=" + documentTypeID +
                ", documentType='" + documentType + '\'' +
                '}';
    }
}
