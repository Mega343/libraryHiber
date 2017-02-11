package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Address;
import com.nixsolutions.dao.AddressDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2AddressDAO implements AddressDAO {

    private static final Logger LOG = LogManager.getLogger(H2AddressDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Address address) {
        LOG.traceEntry("Launched adding address to the database {}", address.toString());
        try {
            session = getSessionFactory().openSession();
            session.save(address);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Address {} add to the database.", address.toString());
        return true;
    }

    @Override
    public boolean edit(Address address) {
        LOG.traceEntry("Launched editing address to the database {}", address.toString());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(address);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Address {} updated successful.", address.toString());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        LOG.traceEntry("Launched deleting address from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Address where address_id = :id");
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
        LOG.traceExit("Address with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public Address getAddress(Long id) {
        LOG.traceEntry("Launched find address by id = {}", id);
        Address address;
        try {
            session = getSessionFactory().openSession();
            address = (Address) session.get(Address.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Address with id = {} found in database.", id);
        return address;
    }
}
