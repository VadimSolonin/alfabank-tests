package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.DepositsPage;
import utils.Helpers;

import static io.qameta.allure.Allure.step;
@Owner("Vadim Solonin")
@Tags({@Tag("ui"), @Tag("deposit")})
public class DepositsPageTests extends TestBase {

    DepositsPage depositsPage = new DepositsPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Возвращение на главную страницу при нажатии на логотип в заголовке")
    void returnToMainPage() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Нажатие на логотип главной страницы", () -> {
            depositsPage.clickHeaderMainLogo();
        });
        step("Проверка нахождения на главной странице", () -> {
            Helpers.verifyPageUrl("https://alfabank.ru/");
            Helpers.verifyPageTitle("Альфа-Банк - кредитные и дебетовые карты, " +
                    "кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк");
        });
    }
    @Test
    @DisplayName("Переход к форме заполнения заявки на открытие вклада при нажатии `Открыть вклад`")
    void scrollToOpenDepositApplicationTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Нажатие на кнопку `Открыть вклад`", () -> {
            depositsPage.clickOpenDepositButton("Открыть вклад");
        });
        step("Проверка смены адреса ресурса на `alfa/#HowToGet`", () -> {
            Helpers.verifyPageUrl("https://alfabank.ru/make-money/deposits/alfa/#HowToGet");
        });
    }

    @Test
    @Tag("applicationFields")
    @DisplayName("Проверка обязательности всех полей заявки на открытие вклада")
    void checkDepositApplicationRequiredFieldsTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Открытие полной формы заявки", () -> {
            depositsPage.setSurnameField(testData.randomSurname)
                    .submitDepositApplication("Продолжить")
                    .clearLastNameInputValue()
                    .submitDepositApplication("Продолжить");
        });
        step("Проверка каждого поля на наличие текста с пометкой об обязательности", () -> {
            depositsPage.checkRequiredFieldError("lastName", "Поле обязательно для заполнения")
                    .checkRequiredFieldError("firstName", "Поле обязательно для заполнения")
                    .checkRequiredFieldError("middleName", "Поле обязательно для заполнения")
                    .checkGenderRequiredFieldError("Поле обязательно для заполнения")
                    .checkRequiredFieldError("passportBirthDateField", "Поле обязательно для заполнения")
                    .checkRequiredFieldError("phone", "Поле обязательно для заполнения");
        });
    }

    @Test
    @Tag("applicationFields")
    @DisplayName("Проверка отключения поля заполнения отчества при нажатии `По паспорту без отчества`")
    void clickHasMiddleNameDisableCheckboxTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Открытие полной формы заявки", () -> {
            depositsPage.setSurnameField(testData.randomSurname)
                    .submitDepositApplication("Продолжить");
        });
        step("Нажатие на чекбокс `По паспорту без отчества", () -> {
            depositsPage.clickHasMiddleNameCheckbox();
        });
        step("Проверка отключения поля отчества", () -> {
            depositsPage.checkmiddleNameDisableAttribute();
        });
    }

    @Test
    @Tag("applicationFields")
    @DisplayName("Заполнение поля даты рождения некорректным значением")
    void setPassportBirthDateFieldIncorrectValueTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Заполнение поля даты рождения единичным числовым значением", () -> {
            depositsPage.setPassportBirthDateField(testData.randomNumber)
                    .submitDepositApplication("Продолжить");
        });
        step("Проверка поля даты рождения на наличие текста с пометкой о вводе некорректного значения", () -> {
            depositsPage.verifyPassportBirthDateFieldError("Указана некорректная дата");
        });
    }

    @Test
    @Tag("applicationFields")
    @DisplayName("Заполнение поля мобильного телефона неполным значением")
    void setPhoneInputFieldIncorrectValueTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Заполнение поля номера телефона коротким числовым значением", () -> {
            depositsPage.setPhoneInputField(testData.randomNumber)
                    .submitDepositApplication("Продолжить");
        });
        step("Проверка поля номера телефона на наличие текста с пометкой о вводе некорректного значения", () -> {
            depositsPage.verifyPhoneFieldError("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67");
        });
    }

    @Test
    @Tag("applicationFields")
    @DisplayName("Заполнение поля почты некорректным значением")
    void setEmailInputIncorrectValueTest() {
        step("Открытие страницы вклада", () -> {
            depositsPage.openPage("/make-money/deposits/alfa");
        });
        step("Заполнение поля почты числовым значением", () -> {
            depositsPage.setEmailInputValue(testData.randomNumber)
                    .submitDepositApplication("Продолжить");
        });
        step("Проверка поля почты на наличие текста с пометкой о вводе некорректного значения", () -> {
            depositsPage.verifyEmailFieldError("Email введен некорректно. Пример: example@domain.ru");
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
