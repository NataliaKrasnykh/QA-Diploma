package ru.netology.javaqa.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import java.time.Duration;
import ru.netology.javaqa.data.DataHelper;

public class DebitCardPage {

    private final SelenideElement heading = $(byText("Оплата по карте"));
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement codeCVVField = $("[placeholder='999']");
    private final SelenideElement button = $$("button").find(exactText("Продолжить"));

    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement dateExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement operationDeclined = $(".notification_status_error");
    private final SelenideElement operationApproved = $(".notification_status_ok");

    public DebitCardPage() {
        heading.shouldBe(visible);
    }

    public void paymentForm(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getOwner());
        codeCVVField.setValue(cardInfo.getCvc());
        button.click();

    }

    public void operationApproved() {
        operationApproved.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void operationDeclined() {
        operationDeclined.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongFormat() {
        wrongFormat.shouldBe(visible);
    }

    public void emptyField() {
        emptyField.shouldBe(visible);
    }

    public void dateExpired() {
        dateExpired.shouldBe(visible);
    }

    public void invalidDate() {
        invalidDate.shouldBe(visible);
    }
}