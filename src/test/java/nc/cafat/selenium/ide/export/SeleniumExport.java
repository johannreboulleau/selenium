package nc.cafat.selenium.ide.export;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class SeleniumExport {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public static final String DRIVER_PATH = "C:\\geckodriver.exe";

    @Before
    public void setUp() throws Exception {

        System.setProperty("webdriver.gecko.driver", DRIVER_PATH);

        driver = new FirefoxDriver();
        //TODO corriger l URL
        baseUrl = "http://bpm-int-01.intra.cafat.nc/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    @Ignore
    public void testSeleniumExport() throws Exception {

        driver.get(baseUrl + "simple/login.jsp?_l=fr&redirectUrl=portal%2Fhomepage");

        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bpm_int_gest");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("bpm_int_gest");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.linkText("Processus"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Processus")).click();
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath("//div[@id='body']/div/div[2]/div[2]/div[2]/div/div[2]/div/div[4]/div[2]/div/div")))
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.xpath("//div[@id='body']/div/div[2]/div[2]/div[2]/div/div[2]/div/div[4]/div[2]/div/div")).click();
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.id("btn-start"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.id("btn-start")).click();
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath("(//button[@type='button'])[4]"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.id("btn-perform"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.id("btn-perform")).click();
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.name("pbCheckbox0"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if ("Koutio 36 rue Jean Sebastien BACH".equals(driver.findElement(By.name("pbInput3")).getAttribute("value")))
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.name("pbCheckbox0")).click();
        driver.findElement(By.name("pbInput3")).clear();
        driver.findElement(By.name("pbInput3")).sendKeys("Koutio 36 rue Jean Sebastien");
        driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
