package nc.cafat.selenium.fluentlenium;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.hook.wait.WaitHook;
import org.openqa.selenium.support.FindBy;

/**
 * Created by reboulleauj on 26/06/2017.
 */
@PageUrl("https://bpm-int-01.intra.cafat.nc/bonita/portal/homepage#?_p=processlistinguser&_pf=1")
public class ProcessusPage extends FluentPage {

    @FindBy(xpath = "//div[@class='td td_displayname td_1 odd even td_displayName td_type_title' and contains(., 'DemandeAdresse')]")
    private FluentWebElement adresseProcessus;

    @FindBy(id = "btn-start")
    private FluentWebElement btnStart;

    public void initProcessusAdresse() {
        adresseProcessus.click();
        btnStart.click();
    }
}
