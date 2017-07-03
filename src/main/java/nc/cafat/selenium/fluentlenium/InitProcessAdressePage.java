package nc.cafat.selenium.fluentlenium;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.filter.FilterConstructor;
import org.fluentlenium.core.hook.wait.WaitHook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static nc.cafat.selenium.simple.BonitaIHM.TIME_WAITING;

/**
 * Created by reboulleauj on 26/06/2017.
 */
public class InitProcessAdressePage extends FluentPage {

    @FindBy(xpath = "//button[@type='button' and contains(., 'Soumettre')]")
    private FluentWebElement boutonSubmit;

    public void submitForm() {

        switchTo($("iframe#bonitaframe"));
        $(By.xpath("//button[@type='button' and contains(., 'Soumettre')]")).withHook(WaitHook.class).click();
        switchToDefault();
    }
}
