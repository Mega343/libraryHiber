package com.nixsolutions;

import com.nixsolutions.dao.DAOFactory;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;

import static com.nixsolutions.dao.DAOFactory.DataBase.H2;

public class DBUnitConfig extends DBTestCase {

    protected IDatabaseTester tester;
    protected IDataSet beforeData;
    protected SessionFactory sessionFactory;
    protected DAOFactory factory;



    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(
                "org.h2.Driver",
                "jdbc:h2:mem:test",
                "root",
                "root"
        );

        Configuration configuration = new Configuration().configure("test_hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("db-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();

        factory = DAOFactory.getDAOFactory(H2);
    }

    public DBUnitConfig(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:test");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");

    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }

}

