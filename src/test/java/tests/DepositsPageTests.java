package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.DepositsPage;
import pages.components.VerifyUrlComponent;

import static io.qameta.allure.Allure.step;

@Tag("deposit_opening_form")
public class DepositsPageTests extends TestBase {

    VerifyUrlComponent verifyUrl = new VerifyUrlComponent();
    DepositsPage depositsPage = new DepositsPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Переход к форме заполнения заявки на открытие вклада при нажатии `Открыть вклад`")
    void scrollToOpenDepositApplicationTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Нажатие на кнопку `Открыть вклад`", () -> {
            depositsPage.clickOpenDepositButton();
        });
        step("Проверка смены адреса ресурса на `alfa/#HowToGet`", () -> {
            verifyUrl.verifyPageUrl("https://alfabank.ru/make-money/deposits/alfa/#HowToGet");
        });
    }

    @Test
    @DisplayName("Проверка обязательности всех полей заявки на открытие вклада")
    void checkDepositApplicationRequiredFieldsTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Открытие полной формы заявки", () -> {
            depositsPage.setSurnameField(testData.randomSurname)
                    .submitDepositApplication()
                    .clearLastNameInputValue()
                    .submitDepositApplication();
        });
        step("Проверка каждого поля на наличие текста с пометкой об обязательности", () -> {
            depositsPage.checkRequiredFieldError("lastName")
                    .checkRequiredFieldError("firstName")
                    .checkRequiredFieldError("middleName")
                    .checkGenderRequiredFieldError()
                    .checkRequiredFieldError("passportBirthDateField")
                    .checkRequiredFieldError("phone");
        });
    }

    @Test
    @DisplayName("Проверка отключения поля заполнения отчества при нажатии `По паспорту без отчества`")
    void clickHasMiddleNameDisableCheckboxTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Открытие полной формы заявки", () -> {
            depositsPage.setSurnameField(testData.randomSurname)
                    .submitDepositApplication();
        });
        step("Нажатие на чекбокс `По паспорту без отчества", () -> {
            depositsPage.clickHasMiddleNameCheckbox();
        });
        step("Проверка отключения поля отчества", () -> {
            depositsPage.checkmiddleNameDisableAttribute();
        });
    }

    @Test
    @DisplayName("Заполнение поля даты рождения некорректным значением")
    void setPassportBirthDateFieldIncorrectValueTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Заполнение поля даты рождения единичным числовым значением", () -> {
            depositsPage.setPassportBirthDateField(testData.randomNumber)
                    .submitDepositApplication();
        });
        step("Проверка поля даты рождения на наличие текста с пометкой о вводе некорректного значения", () -> {
            depositsPage.verifyPassportBirthDateFieldError();
        });
    }

    @Test
    @DisplayName("Заполнение поля мобильного телефона неполным значением")
    void setPhoneInputFieldIncorrectValueTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Заполнение поля номера телефона коротким числовым значением", () -> {
            depositsPage.setPhoneInputField(testData.randomNumber)
                    .submitDepositApplication();
        });
        step("Проверка поля номера телефона на наличие текста с пометкой о вводе некорректного значения", () -> {
            depositsPage.verifyPhoneFieldError();
        });
    }

    @Test
    @DisplayName("Заполнение поля почты некорректным значением")
    void setEmailInputIncorrectValueTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Заполнение поля почты числовым значением", () -> {
            depositsPage.setEmailInputValue(testData.randomNumber)
                    .submitDepositApplication();
        });
        step("Проверка поля почты на наличие текста с пометкой о вводе некорректного значения", () -> {
            depositsPage.verifyEmailFieldError();
        });
    }

    @Test
    @DisplayName("Отображение информационного блока для клиентов без гражданства РФ")
    void viewNotResidentButtonModalTextTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Нажатие в области `У вас гражданство РФ?` кнопки `Нет`", () -> {
            depositsPage.clickNotResidentButton();
        });
        step("Проверка наличия информационного блока с текстом о возможности оформления заявки на карту", () -> {
            depositsPage.verifyNotResidentTextInformation();
        });
    }

}
