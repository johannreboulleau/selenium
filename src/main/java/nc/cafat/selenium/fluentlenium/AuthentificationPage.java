package nc.cafat.selenium.fluentlenium;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;

/**
 * Created by reboulleauj on 26/06/2017.
 */
@PageUrl("https://bpm-int-01.intra.cafat.nc/bonita/login.jsp?redirectUrl=%2Fbonita%2Fportal%2Fhomepage#_l=fr")
public class AuthentificationPage extends FluentPage {

    private FluentWebElement LoginForm;

    public void authenticate(String username, String password) {

        $("#username").fill().with(username);
        $("#password").fill().with(password);

        LoginForm.submit();

        await().explicitlyFor(2000);
    }
}
