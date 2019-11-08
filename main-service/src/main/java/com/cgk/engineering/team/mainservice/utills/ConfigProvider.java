package com.cgk.engineering.team.mainservice.utills;

import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class ConfigProvider {
    private String DB_URL;

    public String getPropValues() {
        InputStream inputStream;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            DB_URL = prop.getProperty("DB_URL");

            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return DB_URL;
    }
}
