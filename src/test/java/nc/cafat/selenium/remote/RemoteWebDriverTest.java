package nc.cafat.selenium.remote;

import nc.cafat.selenium.simple.BonitaIHM;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe permettant de tester grace a Selenium le processus Modification d adresse sous Bonita.
 * <p>
 * Created by reboulleauj on 23/06/2017.
 */
public class RemoteWebDriverTest {

    private BonitaIHM bonitaIHM;

    @Test
    public void testProcessAdresse() throws InterruptedException, MalformedURLException {

        bonitaIHM = new BonitaIHM();

        System.setProperty("webdriver.gecko.driver", BonitaIHM.DRIVER_PATH);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("marionette", true);
        capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());

        WebDriver driver = new RemoteWebDriver(new URL("http://172.17.44.213:9090/wd/hub"), capabilities);


//        WebDriver driver = new FirefoxDriver(capabilities);
        WebDriverWait wait = new WebDriverWait(driver, BonitaIHM.TIME_WAITING);

        // saisie de l URL
        driver.get(BonitaIHM.URL);

        /////////////// on s authentifie /////////////
        BonitaIHM.authentification(driver);

        ///////////// Page principale ///////////
        // on recupere le nombre de tache [Confirmer Adr]
        String nombreResultsDebut = BonitaIHM.getNombreTacheInBottomBannette(wait);

        // click sur le bouton Processus du menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[(@class = 'processlistinguser')]"))).click();

        /////////// Page des processus ////////////
        BonitaIHM.initProcessAdresse(driver, wait);

        /////////////// Page principale //////////////////
        // une tache a du se creer
        String nombreResultsEnCours = BonitaIHM.getNombreTacheInBottomBannette(wait);

        // click sur le bouton FAIRE
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-perform"))).click();

        ///////////// Process Adresse ///////////
        BonitaIHM.doProcessAdresse(driver, wait);

        ////////////// Page principale //////////
        String nombreResultsFin = BonitaIHM.getNombreTacheInBottomBannette(wait);


        // ASSERT
//        Assert.assertEquals(nombreResultsDebut, "1 of 1");
//        Assert.assertEquals(nombreResultsEnCours, "1 - 2 de 2");
//        Assert.assertEquals(nombreResultsFin, "1 de 1");

        driver.quit();
    }

}
