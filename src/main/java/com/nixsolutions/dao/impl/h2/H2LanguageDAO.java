package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Language;
import com.nixsolutions.dao.LanguageDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2LanguageDAO implements LanguageDAO {

    private static final Logger LOG = LogManager.getLogger(H2LanguageDAO.class.getName());
    private Session session;
    private Transaction transaction;

    @Override
    public boolean add(Language language) {
        LOG.traceEntry("Launched adding language to the database {}", language.getLanguage());
        try {
            session = getSessionFactory().openSession();
            session.saveOrUpdate(language);
        } catch (Exception e){
            LOG.catching(e);
         //   transaction.rollback();
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Language {} add to the database.", language.getLanguage());
        return true;
    }

    @Override
    public boolean edit(Language language) {
        LOG.traceEntry("Launched editing language {}", language.getLanguage());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(language);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Language {} updated successful.", language.getLanguage());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting language from the database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Language where languageID = :id");
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
        LOG.traceExit("Language with id = {} deleted from the database.", id);
        return true;
    }

    @Override
    public Language getLanguage(Integer id) {
        LOG.traceEntry("Launched find language by id = {}", id);
        Language language;
        try {
            session = getSessionFactory().openSession();
            language = (Language) session.get(Language.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Language with id = {} found in the database.", id);
        return language;
    }

    @Override
    public List<Language> searchLanguageByName(String name) {
        LOG.traceEntry("Launched find language by name = {}", name);
        List<Language> languageList;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Language where language = :language");
            query.setParameter("language", name);
            languageList = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Language with name = {} found in database.", name);
        return languageList;
    }

    @Override
    public List<Language> getAllLanguages() {
        LOG.traceEntry("Launched find all languages.");
        List<Language> languageList;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Language");
            languageList = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(languageList.size() + " languages found in the database.");
        return languageList;
    }
}
