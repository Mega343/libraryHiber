package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Role;
import com.nixsolutions.dao.RoleDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2RoleDAO implements RoleDAO {

    private static final Logger LOG = LogManager.getLogger(H2RoleDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Role role) {
        LOG.traceEntry("Launched adding role to the database {}", role.getUserRole());
        try {
            session = getSessionFactory().openSession();
            session.save(role);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Role {} add to the database.", role.getUserRole());
        return true;
    }

    @Override
    public boolean edit(Role role) {
        LOG.traceEntry("Launched editing role {}", role.getUserRole());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(role);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Role {} updated successful.", role.getUserRole());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting role from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Role where roleID = :id");
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
        LOG.traceExit("Role with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public Role getRole(Integer id) {
        LOG.traceEntry("Launched find role by id = {}", id);
        Role role;
        try {
            session = getSessionFactory().openSession();
            role = (Role) session.get(Role.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Role with id = {} found in database.", id);
        return role;
    }

    @Override
    public List<Role> getRoleByName(String name) {
        LOG.traceEntry("Launched find role by name = {}", name);
        List<Role> roles;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Role where userRole = :role");
            query.setParameter("role", name);
            roles = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Role with name = {} found in database.", name);
        return roles;
    }

    @Override
    public List<Role> getAllRoles() {
        LOG.traceEntry("Launched find all roles.");
        List<Role> roles;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Role");
            roles = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(roles.size() + " roles found in the database.");
        return roles;
    }
}
