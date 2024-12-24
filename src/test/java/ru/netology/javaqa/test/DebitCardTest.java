package ru.netology.javaqa.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import ru.netology.javaqa.data.DataHelper;
import ru.netology.javaqa.datahelp.SQLHelper;
import ru.netology.javaqa.page.DebitCardPage;
import ru.netology.javaqa.page.StartPage;
import io.qameta.allure.selenide.AllureSelenide;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {

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
    @DisplayName("Оплата по карте со статусом (APPROVED))")
    void shouldSuccesfillyPayment() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getApprovedCardOperation();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.operationApproved();
        assertEquals("APPROVED", SQLHelper.getDebitInfo());
    }

    @Test
    @DisplayName("Оплата по карте со статусом (DECLINED)")
    void shouldNotSuccesfillyPayment() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getDeclinedCardOperation();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.operationDeclined();
        assertEquals("DECLINED", SQLHelper.getDebitInfo());
    }

    @Test
    @DisplayName("Оплата по карте невалидным номером карты")
    void shouldPaymentInvalidCardNumber() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getInvalidCardNumber();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Отправка пустой формы")
    void shouldPaymentWithEmptyFields() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getEmptyCardInfo();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным месяцем карты")
    void shouldPaymentWithInvalidMonth() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorMonth();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным месяцем карты равным 00")
    void shouldPaymentWithMonthNull() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getInvalidDateMonthNull();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным месяцем карты в один символ")
    void shouldPaymentWithMontOneDigits() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorMonthOneDigits();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой с истекшим сроком действия в поле Год")
    void shouldPaymentWithYearExpired() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorYear();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.dateExpired();
    }

    @Test
    @DisplayName("Оплата картой с невалидным годом равным нулю")
    void shouldPaymentWithYearNull() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getYearNull();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным годом карты в один символ")
    void shouldPaymentWithYearOneDigits() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorYearOneDigits();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой со значением кириллицей в поле Владелец")
    void shouldPaymentWithOwnerCyrillic() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorOwnerCyrillic();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой со значением цифры в поле Владелец")
    void shouldPaymentWithOwnerNumbers() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorOwnerNumbers();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой со значением спецсимволы в поле Владелец (искл. - и пробел)")
    void shouldPaymentWithOwnerSymbols() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorOwnerSymbols();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой с невалидным значением в поле CVC/CVV 1 цифра")
    void shouldPaymentWithCVCOneDigits() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorCVVOneDigits();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой с невалидным значением в поле CVC/CVV 2 цифры")
    void shouldPaymentWithCVCTwoDigits() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorCVVTwoDigits();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Номер карты")
    void shouldPaymentWithEmptyNumber() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getCardNumberEmpty();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Месяц")
    void shouldPaymentWithEmptyMonth() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getMonthEmpty();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Год")
    void shouldPaymentWithEmptyYear() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getYearEmpty();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Владелец")
    void shouldPaymentWithEmptyOwner() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getOwnerEmpty();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле CVC/CVV")
    void shouldPaymentWithEmptyCVCCVV() {
        var debitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorCVVEmpty();
        debitCardPage.paymentForm(cardInfo);
        debitCardPage.wrongFormat();
    }
}

