package nc.cafat.selenium.fluentlenium;

import nc.cafat.selenium.configuration.Configuration.Configuration;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by reboulleauj on 26/06/2017.
 */
@Wait
@FluentConfiguration(capabilities = "customCapabilitiesFactory")
public class BonitaFluentLeniumTest extends FluentTest {

    @Inject
    Configuration config;

    @Page
    private AuthentificationPage authentificationPage;

    @Page
    private AccueilPage accueilPage;

    @Page
    private ProcessusPage processusPage;

    @Page
    private InitProcessAdressePage initProcessAdressePage;

    @Page
    private ProcessAdressePage processAdressePage;

    @Before
    public void setUp() throws Exception {
    }

    public BonitaFluentLeniumTest() {
        setWebDriver("remote");
        setRemoteUrl(config.getSeleniumRemoteUrl());
    }


    @Test
//    @Ignore
    public void testBonitaProcessAdresse() throws InterruptedException {

        goTo(authentificationPage);
        authentificationPage.authenticate("bpm_int_gest", "bpm_int_gest");

        goTo(processusPage);
        processusPage.initProcessusAdresse();

//        initProcessAdressePage.wait();
        initProcessAdressePage.submitForm();

        accueilPage.submitFaireLastTacheAdresse();

        processAdressePage.correctAndSubmmitForm();

        assertEquals("", "");
        // assert
    }

}