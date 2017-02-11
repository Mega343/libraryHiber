package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.dao.*;

import java.sql.Connection;

public class H2DAOFactory extends DAOFactory {

    public static Connection getConnection() throws Exception {
        return H2ConnectionManager.getConnection();
    }

    @Override
    public AddressDAO getAddressDAO() {
        return new H2AddressDAO();
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return new H2AuthorDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new H2BookDAO();
    }


    @Override
    public DocumentDAO getDocumentDAO() {
        return new H2DocumentDAO();
    }

    @Override
    public DocumentTypeDAO getDocumentTypeDAO() {
        return new H2DocumentTypeDAO();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return new H2GenreDAO();
    }

    @Override
    public LanguageDAO getLanguageDAO() {
        return new H2LanguageDAO();
    }

    @Override
    public OrderTypeDAO getOrderTypeDAO() {
        return new H2OrderTypeDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new H2OrderDAO();
    }

    @Override
    public PublishingHouseDAO getPublishingHouseDAO() {
        return new H2PublishingHouseDAO();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new H2RoleDAO();
    }

    @Override
    public ShelfDAO getShelfDAO() {
        return new H2ShelfDAO();
    }


    @Override
    public UserDAO getUserDAO() {
        return new H2UserDAO();
    }
}
