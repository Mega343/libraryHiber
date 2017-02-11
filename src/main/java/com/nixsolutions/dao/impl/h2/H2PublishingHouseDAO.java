package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Author;
import com.nixsolutions.bean.PublishingHouse;
import com.nixsolutions.dao.PublishingHouseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2PublishingHouseDAO implements PublishingHouseDAO {

    private static final Logger LOG = LogManager.getLogger(H2PublishingHouseDAO.class.getName());
    private Session session;

    @Override
    public boolean add(PublishingHouse ph) {
        LOG.traceEntry("Launched adding publishing house to the database {}", ph.getPublishingHouseName());
        try {
            session = getSessionFactory().openSession();
            session.saveOrUpdate(ph);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Publishing house {} add to the database.", ph.getPublishingHouseName());
        return true;
    }

    @Override
    public boolean edit(PublishingHouse ph) {
        LOG.traceEntry("Launched editing publishing house {}", ph.getPublishingHouseName());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(ph);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Publishing house with id = {} updated successful.", ph.getPublishingHouseID());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting publishing house from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete PublishingHouse where publishingHouseID = :id");
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
        LOG.traceExit("Publishing house with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public PublishingHouse getPublishingHouse(Integer id) {
        LOG.traceEntry("Launched find publishing house by id = {}", id);
        PublishingHouse ph;
        try {
            session = getSessionFactory().openSession();
            ph = (PublishingHouse) session.get(PublishingHouse.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Publishing house with id = {} found in database.", id);
        return ph;
    }

    @Override
    public List<PublishingHouse> getPublishingHouseByName(String name) {
        LOG.traceEntry("Launched find publishing house by name = {}", name);
        List<PublishingHouse> publishingHouses;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from PublishingHouse where publishingHouseName = :name");
            query.setParameter("name", name);
            publishingHouses = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Publishing house with name = {} found in database.", name);
        return publishingHouses;
    }

    @Override
    public List<PublishingHouse> getAllPublishingHouses() {
        LOG.traceEntry("Launched find all publishing houses.");
        List<PublishingHouse> publishingHouseList;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from PublishingHouse");
            publishingHouseList = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(publishingHouseList.size() + " publishing houses found in the database.");
        return publishingHouseList;
    }
}
