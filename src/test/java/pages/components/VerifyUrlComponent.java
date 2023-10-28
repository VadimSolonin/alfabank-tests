package pages.components;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.*;

public class VerifyUrlComponent {


    public VerifyUrlComponent verifyPageUrl(String pageUrl){
        webdriver().shouldHave(url(pageUrl));
        return this;
    }
}
