package nc.cafat.selenium.fluentlenium;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by reboulleauj on 26/06/2017.
 */
@PageUrl("https://bpm-int-01.intra.cafat.nc/bonita/portal/homepage#?_p=tasklistinguser&_pf=1")
public class AccueilPage extends FluentPage {

    @FindBy(id = "btn-perform")
    private FluentWebElement buttonFaire;

    @FindBy(className = "search")
    private FluentWebElement formSearch;

    @FindBy(id = "gwt-uid-10")
    private FluentWebElement inputSearch;

    public void submitFaireLastTacheAdresse() {
        inputSearch.clear();
        inputSearch.fill().with("adr]");
        await().atMost(5000);
        formSearch.submit();
        buttonFaire.click();
    }

}
