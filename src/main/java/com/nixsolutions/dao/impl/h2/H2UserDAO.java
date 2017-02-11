package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.User;
import com.nixsolutions.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2UserDAO implements UserDAO {

    private static final Logger LOG = LogManager.getLogger(H2UserDAO.class.getName());
    private Session session;

    @Override
    public boolean add(User user) {
        LOG.traceEntry("Launched adding user to the database {}", user.toString());
        try {
            session = getSessionFactory().openSession();
            session.save(user);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("User add to the database = {}", user.toString());
        return true;
    }

    @Override
    public boolean edit(User user) {
        LOG.traceEntry("Launched editing user {}", user.toString());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(user);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("User with id = {} updated successful.", user.getUserID());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        LOG.traceEntry("Launched deleting user from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete User where userID = :id");
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
        LOG.traceExit("User with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public User getUser(Long id) {
        LOG.traceEntry("Launched find user by id = {}", id);
        User user;
        try {
            session = getSessionFactory().openSession();
            user = (User) session.get(User.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("User with id = {} found in database.", id);
        return user;
    }

    @Override
    public List<User> searchByLastName(String lastName) {
        LOG.traceEntry("Launched find users by last name {}", lastName);
        List<User> users;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from User where lastName = :lastName");
            query.setParameter("lastName", lastName);
            users = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(users.size() + " users found in database with last name " + lastName);
        return users;
    }

    @Override
    public User searchByEmail(String email) {
        LOG.traceEntry("Launched find user by email = {}", email);
        User user;
        try {
            session = getSessionFactory().openSession();
            user = (User) session.get(User.class, email);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("User with email = {} found in database.", email);
        return user;
    }


    @Override
    public List<User> getAllUsers() {
        LOG.traceEntry("Launched find all users.");
        List<User> users;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from User");
            users = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(users.size() + " users found in the database.");
        return users;
    }
}
