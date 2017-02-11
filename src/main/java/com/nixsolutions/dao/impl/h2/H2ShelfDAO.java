package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Shelf;
import com.nixsolutions.dao.ShelfDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2ShelfDAO implements ShelfDAO {

    private static final Logger LOG = LogManager.getLogger(H2ShelfDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Shelf shelf) {
        LOG.traceEntry("Launched adding shelf to database {}", shelf.getShelfNumber());
        try {
            session = getSessionFactory().openSession();
            session.saveOrUpdate(shelf);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Shelf {} add to the database.", shelf.getShelfNumber());
        return true;
    }

    @Override
    public boolean edit(Shelf shelf) {
        LOG.traceEntry("Launched editing shelf {}", shelf.getShelfNumber());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(shelf);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Shelf id = {} updated successful.", shelf.getShelfID());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting shelf from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Shelf where shelfID = :id");
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
        LOG.traceExit("Shelf with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public Shelf getShelf(Integer id) {
        LOG.traceEntry("Launched find shelf by id = {}", id);
        Shelf shelf;
        try {
            session = getSessionFactory().openSession();
            shelf = (Shelf) session.get(Shelf.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Shelf with id = {} found in database.", id);
        return shelf;
    }


    @Override
    public List<Shelf> getAllShelves() {
        LOG.traceEntry("Launched find all shelves.");
        List<Shelf> shelves;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Shelf");
            shelves = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(shelves.size() + " shelves found in the database.");
        return shelves;
    }
}
