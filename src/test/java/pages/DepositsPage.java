package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DepositsPage {

    SelenideElement openDepositButton = $("[data-widget-name=ButtonV2]"),
            submitDepositApplication = $("[data-test-id=button]"),
            headerMainLogo = $("[data-test-id=Main-Header-Main-DesktopLogo]"),
            fullNameInput = $("[name=fullName]"),
            genderTags = $("[data-test-id=gender-tags]"),
            lastNameInput = $("[name=lastName]"),
            middleName = $("[name=middleName]"),
            passportBirthDateField = $("[name=passportBirthDateField]"),
            passportBirthDateFieldError = $("[data-test-id=captionError-passportBirthDateField]"),
            phoneInput = $("[data-test-id=phoneInput]"),
            phoneFieldError = $("[data-test-id=captionError-phone]"),
            emailInput = $("[data-test-id=email-input]"),
            emailError = $("[data-test-id=captionError-email]"),
            residentNotResident = $("[data-test-id=resident-notResident]"),
            textNotResidentInformation = $("[data-test-id=form]")
                    .$(withText("Для оформления заявки на получение карты, пожалуйста, обратитесь в ближайшее отделение Альфа-Банка.")),
            hasMiddleName = $("[name=hasMiddleName]").sibling(1);


    public DepositsPage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    public DepositsPage clickOpenDepositButton(String text) {
        openDepositButton.$(withText(text)).click();
        return this;
    }

    public DepositsPage submitDepositApplication(String text) {
        submitDepositApplication.find(byText(text)).click();
        return this;
    }

    public DepositsPage clickHeaderMainLogo() {
        headerMainLogo.click();
        return this;
    }

    public DepositsPage clearLastNameInputValue() {
        lastNameInput.setValue("").pressTab();
        return this;
    }

    public DepositsPage setSurnameField(String spanText) {
        fullNameInput.setValue(spanText).pressEnter();
        return this;
    }

    public DepositsPage checkRequiredFieldError(String fieldName, String text) {
        $("[data-test-id=captionError-" + fieldName + "]").shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(239, 49, 36, 0.9)"))
                .shouldHave(text(text));
        return this;
    }

    public DepositsPage checkGenderRequiredFieldError(String text) {
        genderTags.shouldBe(visible).$(withText(text))
                .shouldHave(cssValue("color", "rgba(239, 49, 36, 0.9)"));
        return this;
    }

    public DepositsPage clickHasMiddleNameCheckbox() {
        hasMiddleName.click();
        return this;
    }

    public DepositsPage checkmiddleNameDisableAttribute() {
        middleName.shouldHave(attribute("disabled"));
        return this;
    }

    public DepositsPage setPassportBirthDateField(String value) {
        passportBirthDateField.setValue(value);
        return this;
    }

    public DepositsPage verifyPassportBirthDateFieldError(String text) {
        passportBirthDateFieldError.shouldBe(visible)
                .shouldHave(text(text));
        return this;
    }
    public DepositsPage setPhoneInputField(String value) {
        phoneInput.setValue(value);
        return this;
    }
    public DepositsPage verifyPhoneFieldError(String text) {
        phoneFieldError.shouldBe(visible)
                .shouldHave(text(text));
        return this;
    }

    public DepositsPage setEmailInputValue(String value) {
        emailInput.setValue(value);
        return this;
    }
    public DepositsPage verifyEmailFieldError(String text) {
        emailError.shouldBe(visible)
                .shouldHave(text(text));
        return this;
    }
    public DepositsPage clickNotResidentButton() {
        residentNotResident.click();
        return this;
    }
    public DepositsPage verifyNotResidentTextInformation() {
        textNotResidentInformation.shouldBe(exist);
        return this;
    }

}
