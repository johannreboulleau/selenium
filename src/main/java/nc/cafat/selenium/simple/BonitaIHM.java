package nc.cafat.selenium.simple;

import org.apache.commons.lang3.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * Classe permettant de tester grace a Selenium le processus Modification d adresse sous Bonita.
 *
 * Created by reboulleauj on 23/06/2017.
 */
public class BonitaIHM {

    // https://github.com/mozilla/geckodriver/releases
    public static final String DRIVER_PATH = "C:\\geckodriver\\geckodriver.exe";
    public static final String URL = "http://bpm-int-01.intra.cafat.nc/bonita/";
    public static final String USERNAME = "bpm_int_gest";
    public static final String PASSWORD = "bpm_int_gest";
    public static final long TIME_WAITING = 10000l;

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", DRIVER_PATH);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        WebDriver driver = new FirefoxDriver(capabilities);
        WebDriverWait wait = new WebDriverWait(driver, TIME_WAITING);

        // saisie de l URL
        driver.get(URL);

        /////////////// on s authentifie /////////////
        authentification(driver);

        ///////////// Page principale ///////////
        // on recupere le nombre de tache [Confirmer Adr]
        String nombreResultsDebut = getNombreTacheInBottomBannette(wait);
        System.out.println("DEBUT - Nombre de taches " + nombreResultsDebut);

        // click sur le bouton Processus du menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[(@class = 'processlistinguser')]"))).click();

        /////////// Page des processus ////////////
        initProcessAdresse(driver, wait);

        /////////////// Page principale //////////////////
        // une tache a du se creer
        String nombreResultsEnCours = getNombreTacheInBottomBannette(wait);
        System.out.println("PROCESS INIT - Nombre de taches " + nombreResultsEnCours);

        // click sur le bouton FAIRE
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-perform"))).click();

        ///////////// Process Adresse ///////////
        doProcessAdresse(driver, wait);

        ////////////// Page principale //////////
        String nombreResultsFin = getNombreTacheInBottomBannette(wait);
        System.out.println("FIN - Nombre de taches " + nombreResultsFin);

        driver.quit();
    }

    /**
     * authentification a simple
     *
     * @param driver Driver Firefox
     */
    public static void authentification(WebDriver driver) {
        // authentification a BONITA
        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("LoginForm")).submit();
    }

    /**
     * Retourne le nombre de tâche dans la banette
     *
     * @param wait Driver Firefox en mode attente
     * @return String Le nombre de tâche dans la banette
     */
    public static String getNombreTacheInBottomBannette(WebDriverWait wait) {

        try {
            WebElement divPagerBottom = wait.until(ExpectedConditions.elementToBeClickable(By.className("pager_bottom")));
            WebElement divTotalTache = divPagerBottom.findElement(By.className("results_count"));

            return divTotalTache.getText();
        } catch(NoSuchElementException e) {
            return "";
        }
    }

    /**
     * Initialise le processus Adresse
     *
     * @param driver Driver Firefox
     * @param wait Driver Firefox en mode attente
     */
    public static void initProcessAdresse(WebDriver driver, WebDriverWait wait) {
        // click sur le processus Adresse
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='td td_displayname td_1 odd even td_displayName td_type_title' and contains(., 'DemandeAdresse')]"))).click();
        // click sur le bouton Démarrer
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-start"))).click();


        /////////// nouvelle page : Initialisation Demande Changement Adresse ////////////
        // click sur le bouton Submit qu se situe dans l IFrame
        WebDriver iframe = driver.switchTo().frame(wait.until(ExpectedConditions.elementToBeClickable(By.id("bonitaframe"))));
        WebDriverWait waitIframe = new WebDriverWait(iframe, TIME_WAITING);

        waitIframe.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(., 'Submit')]"))).click();

        /////////////// Page principale de  la banette //////////////////
        driver.switchTo().defaultContent();
    }

    /**
     * Effectue le processus Adresse
     *
     * @param driver Driver Firefox
     * @param wait Driver Firefox en mode attente
     */
    public static void doProcessAdresse(WebDriver driver, WebDriverWait wait) {
        //////////////// page de modification d adresse ///////////////////
        // click sur la checkbox "Adresse ancien fichiers"
        WebDriver iframe = driver.switchTo().frame(wait.until(ExpectedConditions.elementToBeClickable(By.id("bonitaframe"))));
        WebDriverWait waitIframe = new WebDriverWait(iframe, TIME_WAITING);

        waitIframe.until(ExpectedConditions.elementToBeClickable(By.name("pbCheckbox0"))).click();


        // modification de l adresse
        WebElement inputAdresse = waitIframe.until(ExpectedConditions.elementToBeClickable(By.name("pbInput3")));
        inputAdresse.clear();
        inputAdresse.sendKeys("Koutio 36 rue Jean Sebastien");

        // click bouton confirmer
        waitIframe.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(., 'Confirmer')]"))).click();

        /////////////// Page principale de  la banette //////////////////
        // on recherche le nombre de tâche [Confirmer Adr]
        driver.switchTo().defaultContent();
    }
}
