package nc.cafat.selenium.fluentlenium;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by reboulleauj on 26/06/2017.
 */
public class ProcessAdressePage extends FluentPage {

    @FindBy(name = "pbCheckbox0")
    private FluentWebElement checkboxAdresse;

    @FindBy(name = "pbInput3")
    private FluentWebElement inputAdresse;

    @FindBy(xpath = "//button[@type='button' and contains(., 'Confirmer')]")
    private FluentWebElement buttonSubmit;

    @FindBy(name = "$form")
    private FluentWebElement form;

    public void correctAndSubmmitForm() {
        switchTo($("iframe#bonitaframe"));
        checkboxAdresse.click();
        inputAdresse.clear();
        inputAdresse.fill().with("Koutio 36 rue Jean Sebastien");
        buttonSubmit.click();
    }
}
