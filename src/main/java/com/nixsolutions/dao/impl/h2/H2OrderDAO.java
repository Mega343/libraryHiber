package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Order;
import com.nixsolutions.dao.OrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2OrderDAO implements OrderDAO {

    private static final Logger LOG = LogManager.getLogger(H2OrderDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Order order) {
        LOG.traceEntry("Launched adding order to the database {}", order.toString());
        try {
            session = getSessionFactory().openSession();
            session.save(order);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Order add to the database = {}", order.toString());
        return true;
    }

    @Override
    public boolean edit(Order order) {
        LOG.traceEntry("Launched editing order to the database {}", order.toString());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(order);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Order updated successful = {}", order.toString());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        LOG.traceEntry("Launched deleting order from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Order where orderID = :id");
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
        LOG.traceExit("Order with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public Order getOrder(Long id) {
        LOG.traceEntry("Launched find order by id = {}", id);
        Order order;
        try {
            session = getSessionFactory().openSession();
            order = (Order) session.get(Order.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Order with id = {} found in database.", id);
        return order;
    }



    @Override
    public List<Order> getAllOrders() {
        LOG.traceEntry("Launched find all order.");
        List<Order> orders;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Order");
            orders = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(orders.size() + " found in the database.");
        return orders;
    }
}
