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
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getApprovedCardOperation();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.operationApproved();
        assertEquals("APPROVED", SQLHelper.getDebitInfo());
    }

    @Test
    @DisplayName("Оплата по карте со статусом (DECLINED)")
    void shouldNotSuccesfillyPayment() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getDeclinedCardOperation();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.operationDeclined();
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
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getEmptyCardInfo();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным месяцем карты")
    void shouldPaymentWithInvalidMonth() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorMonth();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным месяцем карты равным 00")
    void shouldPaymentWithMonthNull() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getInvalidDateMonthNull();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным месяцем карты в один символ")
    void shouldPaymentWithMontOneDigits() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorMonthOneDigits();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой с истекшим сроком действия в поле Год")
    void shouldPaymentWithYearExpired() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorYear();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.dateExpired();
    }

    @Test
    @DisplayName("Оплата картой с невалидным годом равным нулю")
    void shouldPaymentWithYearNull() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getYearNull();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.invalidDate();
    }

    @Test
    @DisplayName("Оплата по карте с невалидным годом карты в один символ")
    void shouldPaymentWithYearOneDigits() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorYearOneDigits();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой со значением кириллицей в поле Владелец")
    void shouldPaymentWithOwnerCyrillic() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorOwnerCyrillic();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой со значением цифры в поле Владелец")
    void shouldPaymentWithOwnerNumbers() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorOwnerNumbers();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой со значением спецсимволы в поле Владелец (искл. - и пробел)")
    void shouldPaymentWithOwnerSymbols() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorOwnerSymbols();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой с невалидным значением в поле CVC/CVV 1 цифра")
    void shouldPaymentWithCVCOneDigits() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorCVVOneDigits();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата картой с невалидным значением в поле CVC/CVV 2 цифры")
    void shouldPaymentWithCVCTwoDigits() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorCVVTwoDigits();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Номер карты")
    void shouldPaymentWithEmptyNumber() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getCardNumberEmpty();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Месяц")
    void shouldPaymentWithEmptyMonth() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getMonthEmpty();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Год")
    void shouldPaymentWithEmptyYear() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getYearEmpty();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле Владелец")
    void shouldPaymentWithEmptyOwner() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getOwnerEmpty();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.emptyField();
    }

    @Test
    @DisplayName("Оплата по карте с пустым значением в поле CVC/CVV")
    void shouldPaymentWithEmptyCVCCVV() {
        var DebitCardPage = page.goDebitCardPage();
        var cardInfo = DataHelper.getErrorCVVEmpty();
        DebitCardPage.paymentForm(cardInfo);
        DebitCardPage.wrongFormat();
    }
}

