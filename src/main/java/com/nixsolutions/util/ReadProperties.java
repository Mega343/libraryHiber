package com.nixsolutions.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadProperties {

    private static final Logger LOG = LogManager.getLogger(ReadProperties.class.getName());


    public String[] getProperties(String filePropertiesName) throws IOException, URISyntaxException {
        LOG.info("Start read properties from file " + filePropertiesName);
        String[] properties = new String[5];
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(filePropertiesName)) {
            property.load(fis);


            properties[0] = property.getProperty("h2.url");
            properties[1] = property.getProperty("h2.login");
            properties[2] = property.getProperty("h2.password");
            properties[3] = property.getProperty("h2.driver");
            properties[4] = property.getProperty("h2.name");
            properties[0] = property.getProperty("h2.url") + dbURL(properties[4]);

        } catch (FileNotFoundException e) {
            LOG.catching(e);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            property.load(classLoader.getResourceAsStream("/h2db.properties"));
            properties[1] = property.getProperty("h2.login");
            properties[2] = property.getProperty("h2.password");
            properties[3] = property.getProperty("h2.driver");
            properties[4] = property.getProperty("h2.name");
            properties[0] = (property.getProperty("h2.url") + dbURL(properties[4]));
        }
        LOG.info("All properties were read.");
        return properties;
    }

    private String dbURL(String dbName) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        URL resource = ReadProperties.class.getResource(dbName);
        String url = Paths.get(resource.toURI()).toFile().toString();
        sb.append(url);
        sb.delete(sb.length()-7, sb.length()-1);
        return sb.toString();
    }
}
