package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.DocumentType;
import com.nixsolutions.dao.DocumentTypeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2DocumentTypeDAO implements DocumentTypeDAO {

    private static final Logger LOG = LogManager.getLogger(H2DocumentTypeDAO.class.getName());
    private Session session;

    @Override
    public boolean add(DocumentType docType) {
        LOG.traceEntry("Launched adding document type to the database {}", docType.getDocumentType());
        try {
            session = getSessionFactory().openSession();
            session.save(docType);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document type {} add to the database.", docType.getDocumentType());
        return true;
    }

    @Override
    public boolean edit(DocumentType docType) {
        LOG.traceEntry("Launched editing document type {}", docType.getDocumentType());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(docType);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document type {} updated successful.", docType.getDocumentType());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting document type from the database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete DocumentType where documentTypeID = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document type with id = {} deleted from the database.", id);
        return true;
    }

    @Override
    public DocumentType getDocumentType(Integer id) {
        LOG.traceEntry("Launched find document type by id = {}", id);
        DocumentType docType;
        try {
            session = getSessionFactory().openSession();
            docType = (DocumentType) session.get(DocumentType.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document type with id = {} found in the database.", id);
        return docType;
    }

    @Override
    public List<DocumentType> findDocumentTypeByName(String name) {
        LOG.traceEntry("Launched find document type by name = {}", name);
        List<DocumentType> documentType;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from DocumentType where documentType = :name");
            query.setParameter("name", name);
            documentType = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document type with name = {} found in database.", name);
        return documentType;
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        LOG.traceEntry("Launched find all document types.");
        List<DocumentType> documentTypeList;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from DocumentType");
            documentTypeList = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(documentTypeList.size() + " document types found in the database.");
        return documentTypeList;
    }
}
