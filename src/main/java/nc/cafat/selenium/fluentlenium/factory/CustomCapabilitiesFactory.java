package nc.cafat.selenium.fluentlenium.factory;

import org.fluentlenium.configuration.CapabilitiesFactory;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FactoryName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by reboulleauj on 26/06/2017.
 */
@FactoryName("customCapabilitiesFactory")
public class CustomCapabilitiesFactory implements CapabilitiesFactory {

    @Override
    public Capabilities newCapabilities(ConfigurationProperties configuration) {

        DesiredCapabilities caps =  new DesiredCapabilities("firefox", "", Platform.ANY);
        caps.setAcceptInsecureCerts(true);

        return caps;
    }
}
