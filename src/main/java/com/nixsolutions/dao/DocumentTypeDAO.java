package com.nixsolutions.dao;


import com.nixsolutions.bean.DocumentType;

import java.util.List;

public interface DocumentTypeDAO {

    public boolean add(DocumentType docType);

    public boolean edit(DocumentType docType);

    public boolean delete(Integer id);

    public DocumentType getDocumentType(Integer id);

    public List<DocumentType> findDocumentTypeByName(String name);

    public List<DocumentType> getAllDocumentTypes();
}
