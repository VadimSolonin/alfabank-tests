package tests;

import org.junit.jupiter.api.Test;
import pages.DepositsPage;
import pages.components.VerifyUrlComponent;

public class DepositsPageTests extends TestBase {

    VerifyUrlComponent verifyUrl = new VerifyUrlComponent();
    DepositsPage depositsPage = new DepositsPage();

    @Test
    void scrollToOpenDepositApplicationTest() {
        depositsPage.openPage("/make-money/deposits/alfa");
        depositsPage.clickOpenDepositButton();
        verifyUrl.verifyPageUrl("https://alfabank.ru/make-money/deposits/alfa/#HowToGet");
    }

    @Test
    void checkDepositApplicationRequiredFieldsTest() {
        depositsPage.openPage("/make-money/deposits/alfa")
                .setSurnameField("П")
                .submitDepositApplication()
                .clearLastNameInputValue()
                .submitDepositApplication()
                .checkRequiredFieldError("lastName")
                .checkRequiredFieldError("firstName")
                .checkRequiredFieldError("middleName")
                .checkGenderRequiredFieldError()
                .checkRequiredFieldError("passportBirthDateField")
                .checkRequiredFieldError("phone");
    }

    @Test
    void clickHasMiddleNameDisableCheckboxTest() {
        depositsPage.openPage("/make-money/deposits/alfa")
                .setSurnameField("П")
                .submitDepositApplication()
                .clickHasMiddleNameCheckbox()
                .checkmiddleNameDisableAttribute();
    }

    @Test
    void setPassportBirthDateFieldIncorrectValueTest() {
        depositsPage.openPage("/make-money/deposits/alfa")
                .setPassportBirthDateField("1")
                .submitDepositApplication()
                .verifyPassportBirthDateFieldError();
    }

    @Test
    void setPhoneInputFieldIncorrectValueTest() {
        depositsPage.openPage("/make-money/deposits/alfa")
                .setPhoneInputField("1")
                .submitDepositApplication()
                .verifyPhoneFieldError();
    }

    @Test
    void setEmailInputIncorrectValueTest() {
        depositsPage.openPage("/make-money/deposits/alfa")
                .setEmailInputValue("1")
                .submitDepositApplication()
                .verifyEmailFieldError();
    }

}
