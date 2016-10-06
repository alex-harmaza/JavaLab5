package by.training.notebook.source;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class ConfigProvider {

    private static ConfigProvider instance = new ConfigProvider();

    private Properties properties;


    public static ConfigProvider getInstance(){
        return instance;
    }


    private ConfigProvider(){
        properties = new Properties();
        try (InputStream s = this.getClass().getClassLoader()
                .getResourceAsStream("configuration.properties")) {
            properties.load(s);
        }
        catch (IOException ex){
            throw new UncheckedIOException(ex);
        }
    }


    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
