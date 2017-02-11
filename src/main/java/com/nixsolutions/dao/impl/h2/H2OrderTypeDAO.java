package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.OrderType;
import com.nixsolutions.dao.OrderTypeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2OrderTypeDAO implements OrderTypeDAO {

    private static final Logger LOG = LogManager.getLogger(H2OrderTypeDAO.class.getName());
    private Session session;

    @Override
    public boolean add(OrderType orderType) {
        LOG.traceEntry("Launched adding order type to the database {}", orderType.getOrderType());
        try {
            session = getSessionFactory().openSession();
            session.save(orderType);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Order type {} add to the database.", orderType.getOrderType());
        return true;
    }

    @Override
    public boolean edit(OrderType orderType) {
        LOG.traceEntry("Launched editing order type {}", orderType.getOrderType());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(orderType);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Order type {} updated successful.", orderType.getOrderType());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting order type from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete OrderType where orderTypeID = :id");
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
        LOG.traceExit("Order type with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public OrderType getOrderType(Integer id) {
        LOG.traceEntry("Launched find order type by id = {}", id);
        OrderType orderType;
        try {
            session = getSessionFactory().openSession();
            orderType = (OrderType) session.get(OrderType.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Order type with id = {} found in database.", id);
        return orderType;
    }

    @Override
    public List<OrderType> getAllOrderTypes() {
        LOG.traceEntry("Launched find all order types.");
        List<OrderType> orderTypeList;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from OrderType");
            orderTypeList = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(orderTypeList.size() + " order types found in the database.");
        return orderTypeList;
    }
}
