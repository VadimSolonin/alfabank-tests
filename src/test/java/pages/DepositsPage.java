package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DepositsPage {

    SelenideElement openDepositButton = $("[data-widget-name=ButtonV2]"),
            submitDepositApplication = $("[data-test-id=button]"),
            fullNameInput = $("[name=fullName]"),
            genderTags = $("[data-test-id=gender-tags]")
                    .$(withText("Поле обязательно для заполнения")),
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

    public DepositsPage clickOpenDepositButton() {
        openDepositButton.$(withText("Открыть вклад")).click();
        return this;
    }

    public DepositsPage submitDepositApplication() {
        submitDepositApplication.find(byText("Продолжить")).click();
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

    public DepositsPage checkRequiredFieldError(String fieldName) {
        $("[data-test-id=captionError-" + fieldName + "]").shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(239, 49, 36, 0.9)"))
                .shouldHave(text("Поле обязательно для заполнения"));
        return this;
    }

    public DepositsPage checkGenderRequiredFieldError() {
        genderTags.shouldBe(visible)
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

    public DepositsPage verifyPassportBirthDateFieldError() {
        passportBirthDateFieldError.shouldBe(visible)
                .shouldHave(text("Указана некорректная дата"));
        return this;
    }
    public DepositsPage setPhoneInputField(String value) {
        phoneInput.setValue(value);
        return this;
    }
    public DepositsPage verifyPhoneFieldError() {
        phoneFieldError.shouldBe(visible)
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67"));
        return this;
    }

    public DepositsPage setEmailInputValue(String value) {
        emailInput.setValue(value);
        return this;
    }
    public DepositsPage verifyEmailFieldError() {
        emailError.shouldBe(visible)
                .shouldHave(text("Email введен некорректно. Пример: example@domain.ru"));
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
