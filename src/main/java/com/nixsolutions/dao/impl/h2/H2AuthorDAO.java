package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Author;
import com.nixsolutions.dao.AuthorDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2AuthorDAO implements AuthorDAO {

    private static final Logger LOG = LogManager.getLogger(H2AuthorDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Author author) {
        LOG.traceEntry("Launched adding author to the database {}", author.getLastName());
        try {
            session = getSessionFactory().openSession();
            session.saveOrUpdate(author);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Author add to the database = {}", author.getLastName());
        return true;
    }

    @Override
    public boolean edit(Author author) {
        LOG.traceEntry("Launched editing author to the database {}", author.getLastName());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(author);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Author edited to the database = {}", author.getLastName());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting author from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Author where authorID = :id");
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
        LOG.traceExit("Author with id = {} deleted from database.", id);
        return true;
    }

    @Override
    public Author getAuthor(Integer id) {
        LOG.traceEntry("Launched find author by id = {}", id);
        Author author;
        try {
            session = getSessionFactory().openSession();
            author = (Author) session.get(Author.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Author with id = {} found in database.", id);
        return author;
    }

    @Override
    public List<Author> searchByName(String lastName) {
        LOG.traceEntry("Launched find author by last name = {}", lastName);
        List<Author> authors = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Author where lastName = :lastName");
            query.setParameter("lastName", lastName);
            authors = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(authors.size() + " authors found in database with last name " + lastName);
        return authors;
    }

    @Override
    public List<Author> searchByName(String firstName, String lastName) {
        LOG.traceEntry("Launched find author by first and last name = {} {}", lastName, firstName);
        List<Author> authors = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Author where lastName = :lastName and firstName = :firstName");
            query.setParameter("lastName", lastName);
            query.setParameter("firstName", firstName);
            authors = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(authors.size() + " authors found in database with name " + firstName + " " + lastName);
        return authors;
    }

    @Override
    public List<Author> searchByName(String firstName, String lastName, String patronymic) {
        LOG.traceEntry("Launched find author by first and last name and patronymic = {} {} {}", lastName, firstName, patronymic);
        List<Author> authors = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery(
                    "from Author where lastName = :lastName and firstName = :firstName and patronymic = :patronymic");
            query.setParameter("lastName", lastName);
            query.setParameter("firstName", firstName);
            query.setParameter("patronymic", patronymic);
            authors = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(authors.size() + " authors found in database with name " + firstName + " " + lastName + " " + patronymic);
        return authors;
    }

    @Override
    public List<Author> getAllAuthors() {
        LOG.traceEntry("Launched find all authors");
        List<Author> authors = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Author");
            authors = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(authors.size() + " authors found in database.");
        return authors;
    }
}
