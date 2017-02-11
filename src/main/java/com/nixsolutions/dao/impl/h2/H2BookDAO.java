package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Book;
import com.nixsolutions.dao.BookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2BookDAO implements BookDAO {

    private static final Logger LOG = LogManager.getLogger(H2BookDAO.class.getName());
    private Session session;
    private Transaction transaction;

    @Override
    public boolean add(Book book) {
        LOG.traceEntry("Launched adding book to the database {}", book.getBookName());
        try {
            session = getSessionFactory().openSession();
            session.save(book);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Book add to the database = {}", book.getBookName());
        return true;
    }

    @Override
    public boolean edit(Book book) {
        LOG.traceEntry("Launched editing book {}", book.getBookID());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(book);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Book with id = {} updated successful.", book.getBookID());
        return true;
    }

    @Override
    public boolean delete(Long id) {
        LOG.traceEntry("Launched deleting book from database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Book WHERE bookID = :id");
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
        LOG.traceExit("Book with id = {} deleted from the database.", id);
        return true;
    }

    @Override
    public Book getBook(Long id) {
        LOG.traceEntry("Launched find book by id = {}", id);
        Book book;
        try {
            session = getSessionFactory().openSession();
            book = (Book) session.get(Book.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Book with id = {} found in the database.", id);
        return book;
    }


    @Override
    public List<Book> searchByBookName(String bookName) {
        LOG.traceEntry("Launched find books by book name {}", bookName);
        List<Book> books = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Book where bookName = :bookName");
            query.setParameter("bookName", bookName);
            books = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(books.size() + " books found in the database with book name " + bookName);
        return books;
    }


    @Override
    public List<Book> searchByRateGreaterThan(Integer rate) {
        LOG.traceEntry("Launched find books with rate greater than {}", rate);
        List<Book> books = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Book where bookRate > :bookRate");
            query.setParameter("bookRate", rate);
            books = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(books.size() + " books found in the database rate grater than " + rate);
        return books;
    }

    @Override
    public List<Book> searchByReadingsGreaterThan(Integer readings) {
        LOG.traceEntry("Launched find books with number of readings greater than {}", readings);
        List<Book> books = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Book where numberOfReadings > :numberOfReadings");
            query.setParameter("numberOfReadings", readings);
            books = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(books.size() + " books found in the database  number of readings grater than " + readings);
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        LOG.traceEntry("Launched find all books.");
        List<Book> books;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Book");
            books = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(books.size() + " books found in the database.");
        return books;
    }
}
