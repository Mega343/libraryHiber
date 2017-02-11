package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Document;
import com.nixsolutions.dao.DocumentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2DocumentDAO implements DocumentDAO {

    private static final Logger LOG = LogManager.getLogger(H2DocumentDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Document document) {
        LOG.traceEntry("Launched adding document to the database {}", document.toString());
        try {
            session = getSessionFactory().openSession();
            session.save(document);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document {} add to the database.", document.toString());
        return true;
    }

    @Override
    public boolean edit(Document document) {
        LOG.traceEntry("Launched editing document {}", document.getDocumentID());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(document);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document with id = {} updated successful.", document.getDocumentID());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting document from the database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Document where documentID = :id");
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
        LOG.traceExit("Document with id = {} deleted from the database.", id);
        return true;
    }

    @Override
    public Document getDocument(Integer id) {
        LOG.traceEntry("Launched find document by id = {}", id);
        Document document;
        try {
            session = getSessionFactory().openSession();
            document = (Document) session.get(Document.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Document with id = {} found in the database.", id);
        return document;
    }
}
