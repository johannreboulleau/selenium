package nc.cafat.selenium.configuration.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by reboulleauj on 03/07/2017.
 */
public final class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    private static final String pathProperties = "configuration.properties";

    private final Properties properties = new Properties();

    public final static String KEY_SELENIUM_TIMEOUT = "selenium.timeout";
    public final static String KEY_SELENIUM_REMOTE_URL = "selenium.remote.url";

    public final static String KEY_PATH_GECKO_DRIVER = "path.gecko.driver";

    public final static String KEY_BONITA_URL = "bonita.url";
    public final static String KEY_BONITA_USERNAME = "bonita.username";
    public final static String KEY_BONITA_PASSWORD = "bonita.password";

    public Configuration() {
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(pathProperties));
        } catch (IOException e) {
            LOGGER.error("Erreur au chargement des properties", e);
        }
    }

    public Long getSeleniumTimeOut() {
        return new Long(properties.getProperty(KEY_SELENIUM_TIMEOUT));
    }

    public String getSeleniumRemoteUrl() {
        return properties.getProperty(KEY_SELENIUM_REMOTE_URL);
    }

    public String getPathGeckoDriver() {
        return properties.getProperty(KEY_PATH_GECKO_DRIVER);
    }

    public String getBonitaUrl() {
        return properties.getProperty(KEY_BONITA_URL);
    }

    public String getBonitaUsername() {
        return properties.getProperty(KEY_BONITA_USERNAME);
    }

    public String getBonitaPassword() {
        return properties.getProperty(KEY_BONITA_PASSWORD);
    }
}
