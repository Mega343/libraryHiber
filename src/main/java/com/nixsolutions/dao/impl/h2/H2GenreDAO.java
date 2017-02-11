package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.bean.Genre;
import com.nixsolutions.dao.GenreDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static com.nixsolutions.util.HibernateUtil.getSessionFactory;

public class H2GenreDAO implements GenreDAO {

    private static final Logger LOG = LogManager.getLogger(H2GenreDAO.class.getName());
    private Session session;

    @Override
    public boolean add(Genre genre) {
        LOG.traceEntry("Launched adding genre to the database {}", genre.getGenre());
        try {
            session = getSessionFactory().openSession();
            session.saveOrUpdate(genre);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Genre {} add to the database.", genre.getGenre());
        return true;
    }

    @Override
    public boolean edit(Genre genre) {
        LOG.traceEntry("Launched editing genre {}", genre.getGenre());
        try{
            session = getSessionFactory().openSession();
            session.saveOrUpdate(genre);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Genre {} updated successful.", genre.getGenre());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        LOG.traceEntry("Launched deleting genre from the database with id = {}", id);
        try{
            session = getSessionFactory().openSession();
            Query query = session.createQuery("delete Genre where genreID = :id");
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
        LOG.traceExit("Genre with id = {} deleted from the database.", id);
        return true;
    }

    @Override
    public Genre getGenre(Integer id) {
        LOG.traceEntry("Launched find genre by id = {}", id);
        Genre genre;
        try {
            session = getSessionFactory().openSession();
            genre = (Genre) session.get(Genre.class, id);
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Genre with id = {} found in the database.", id);
        return genre;
    }

    @Override
    public List<Genre> searchGenreByName(String name) {
        LOG.traceEntry("Launched find genre by name = {}", name);
        List<Genre> genres;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Genre where genre = :genre");
            query.setParameter("genre", name);
            genres = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit("Genre with name = {} found in database.", name);
        return genres;
    }

    @Override
    public List<Genre> getAllGenres() {
        LOG.traceEntry("Launched find all languages.");
        List<Genre> genreList;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Genre");
            genreList = query.list();
        } catch (Exception e){
            LOG.catching(e);
            throw LOG.throwing(new RuntimeException(e));
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        LOG.traceExit(genreList.size() + " genres found in the database.");
        return genreList;
    }
}
