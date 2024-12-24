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
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getApprovedCardOperation();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.operationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditInfo());
    }

    @Test
    @DisplayName("Оплата в кредит по карте со статусом (DECLINED)")
    void shouldNotSuccesfillyCredit() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getDeclinedCardOperation();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.operationDeclined();
        assertEquals("DECLINED", SQLHelper.getCreditInfo());
    }

    @Test
    @DisplayName("Оплата в кредит по карте невалидным номером карты")
    void shouldCreditInvalidCardNumber() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getInvalidCardNumber();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Отправка пустой формы")
    void shouldCreditWithEmptyFields() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getEmptyCardInfo();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным месяцем карты")
    void shouldCreditWithInvalidMonth() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorMonth();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным месяцем карты равным 00")
    void shouldCreditWithMonthNull() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getInvalidDateMonthNull();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным месяцем карты в один символ")
    void shouldCreditWithMontOneDigits() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorMonthOneDigits();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой с истекшим сроком действия в поле Год")
    void shouldCreditWithYearExpired() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorYear();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.dateExpired();
    }

    @Test
    @DisplayName("Оплата в кредит картой с невалидным годом равным нулю")
    void shouldCreditWithYearNull() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getYearNull();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с невалидным годом карты в один символ")
    void shouldCreditWithYearOneDigits() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorYearOneDigits();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой со значением кириллицей в поле Владелец")
    void shouldCreditWithOwnerCyrillic() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorOwnerCyrillic();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой со значением цифры в поле Владелец")
    void shouldCreditWithOwnerNumbers() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorOwnerNumbers();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой со значением спецсимволы в поле Владелец (искл. - и пробел)")
    void shouldCreditWithOwnerSymbols() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorOwnerSymbols();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой с невалидным значением в поле CVC/CVV 1 цифра")
    void shouldCreditWithCVCOneDigits() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorCVVOneDigits();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит картой с невалидным значением в поле CVC/CVV 2 цифры")
    void shouldCreditWithCVCTwoDigits() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorCVVTwoDigits();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Номер карты")
    void shouldCreditWithEmptyNumber() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getCardNumberEmpty();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Месяц")
    void shouldCreditWithEmptyMonth() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getMonthEmpty();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Год")
    void shouldCreditWithEmptyYear() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getYearEmpty();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле Владелец")
    void shouldCreditWithEmptyOwner() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getOwnerEmpty();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата в кредит по карте с пустым значением в поле CVC/CVV")
    void shouldCreditWithEmptyCVCCVV() {
        var creditCardPage = page.goCreditCardPage();
        var cardInfo = DataHelper.getErrorCVVEmpty();
        creditCardPage.paymentForm(cardInfo);
        creditCardPage.wrongFormat();
    }
}

