package ru.netology.javaqa.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import ru.netology.javaqa.data.DataHelper;
import ru.netology.javaqa.datahelp.SQLHelper;
import ru.netology.javaqa.page.CreditCardPage;
import ru.netology.javaqa.page.StartPage;
import io.qameta.allure.selenide.AllureSelenide;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {

    StartPage page = open("http://localhost:8080", StartPage.class);


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @AfterEach
    public void cleanBD() {
        SQLHelper.cleanBase();
    }


    @Test
    @DisplayName("Оплата в кредит по карте со статусом (APPROVED))")
    void shouldSuccesfillyCredit() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getApprovedCardOperation();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.operationApproved();
        //assertEquals("APPROVED", SQLHelper.getCreditInfo());
    }

    @Test
    @DisplayName("Оплата в кредит по карте со статусом (DECLINED)")
    void shouldNotSuccesfillyCredit() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getDeclinedCardOperation();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.operationDeclined();
        //assertEquals("DECLINED", SQLHelper.getCreditInfo());
    }

    @Test
    @DisplayName("Оплата в кредит по карте невалидным номером карты")
    void shouldCreditInvalidCardNumber() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getInvalidCardNumber();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Отправка пустой формы")
    void shouldCreditWithEmptyFields() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getEmptyCardInfo();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным месяцем карты")
    void shouldCreditWithInvalidMonth() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorMonth();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным месяцем карты равным 00")
    void shouldCreditWithMonthNull() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getInvalidDateMonthNull();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным месяцем карты в один символ")
    void shouldCreditWithMontOneDigits() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorMonthOneDigits();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой с истекшим сроком действия в поле Год")
    void shouldCreditWithYearExpired() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorYear();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.dateExpired();
    }

    @Test
    @DisplayName("Оплата в кредит картой с невалидным годом равным нулю")
    void shouldCreditWithYearNull() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getYearNull();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным годом карты в один символ")
    void shouldCreditWithYearOneDigits() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorYearOneDigits();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой со значением кириллицей в поле Владелец")
    void shouldCreditWithOwnerCyrillic() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorOwnerCyrillic();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой со значением цифры в поле Владелец")
    void shouldCreditWithOwnerNumbers() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorOwnerNumbers();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой со значением спецсимволы в поле Владелец (искл. - и пробел)")
    void shouldCreditWithOwnerSymbols() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorOwnerSymbols();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой с невалидным значением в поле CVC/CVV 1 цифра")
    void shouldCreditWithCVCOneDigits() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorCVVOneDigits();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой с невалидным значением в поле CVC/CVV 2 цифры")
    void shouldCreditWithCVCTwoDigits() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorCVVTwoDigits();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Номер карты")
    void shouldCreditWithEmptyNumber() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getCardNumberEmpty();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Месяц")
    void shouldCreditWithEmptyMonth() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getMonthEmpty();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Год")
    void shouldCreditWithEmptyYear() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getYearEmpty();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Владелец")
    void shouldCreditWithEmptyOwner() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getOwnerEmpty();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле CVC/CVV")
    void shouldCreditWithEmptyCVCCVV() {
        var CreditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorCVVEmpty();
        CreditCardPage.paymentForm(cardInfo);
        CreditCardPage.wrongFormat();
    }
}

