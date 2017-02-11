package com.nixsolutions;

import com.nixsolutions.dao.DAOFactory;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.SessionFactory;
import org.junit.After;

import java.io.FileInputStream;

import static com.nixsolutions.dao.DAOFactory.DataBase.H2;

public class DBUnitConfig extends DBTestCase {

    protected DAOFactory factory;
    protected SessionFactory sessionFactory;

    public DBUnitConfig(String name) throws Exception {
        super(name);

        factory = DAOFactory.getDAOFactory(H2);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:test");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/db-data.xml"));
    }



    @After
    public void tearDown() throws Exception {
        if(sessionFactory != null) {
            sessionFactory.close();
        }
        super.tearDown();
    }
}

