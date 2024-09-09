package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class PaymentGatePage {
    private SelenideElement heading = $(withText("Оплата по карте"));
    private SelenideElement cardNumberInput = $(by("placeholder", "0000 0000 0000 0000"));
    private SelenideElement expirationMonthInput = $(by("placeholder", "08"));
    private SelenideElement expirationYearInput = $(by("placeholder", "22"));
    private SelenideElement ownerInput = $$(".input__inner").findBy(text("Владелец")).$(".input__control");
    private SelenideElement cvcInput = $(by("placeholder", "999"));

    private ElementsCollection buttons = $$("button");
    private ElementsCollection inputSubElements = $$(".input__sub");
    private ElementsCollection notificationTitleElements = $$(".notification__title");
    private ElementsCollection notificationContentElements = $$(".notification__content");

    public void paymentGatePage() {
        heading.shouldBe(visible);
    }

    public void fillCardData(String cardNumber, String expiryMonth, String expiryYear, String ownerName, String cvc) {
        cardNumberInput.sendKeys(cardNumber);
        expirationMonthInput.sendKeys(expiryMonth);
        expirationYearInput.sendKeys(expiryYear);
        ownerInput.setValue(ownerName);
        cvcInput.sendKeys(cvc);
    }

    public void clickContinueButton() {
        buttons.find(exactText("Продолжить")).click();
    }

    public ElementsCollection getInputSubElements() {
        return inputSubElements.shouldHave(size(1));
    }

    public String getErrorFormatText() {
        return getInputSubElements().findBy(text("Неверный формат")).getText();
    }

    public String getEmptyFieldText() {
        return getInputSubElements().findBy(text("Поле обязательно для заполнения")).getText();
    }

    public String getTermValidityExpiredText() {
        return getInputSubElements().findBy(text("Истёк срок действия карты")).getText();
    }

    public String getErrorCardTermValidityText() {
        return getInputSubElements().findBy(text("Неверно указан срок действия карты")).getText();
    }

    public ElementsCollection getNotificationTitleElements() {
        return notificationTitleElements;
    }

    public String getBankDeclinedOperationTitleText() {
        getNotificationTitleElements().findBy(text("Успешно")).shouldBe(hidden);
        return getNotificationTitleElements().findBy(text("Ошибка")).shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    public String getBankApprovedOperationTitleText() {
        getNotificationTitleElements().findBy(text("Ошибка")).shouldBe(hidden);
        return getNotificationTitleElements().findBy(text("Успешно")).shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    public ElementsCollection getNotificationContentElements() {
        return notificationContentElements;
    }

    public String getBankDeclinedOperationContentText() {
        getNotificationContentElements().findBy(text("Операция одобрена Банком.")).shouldBe(hidden);
        return getNotificationContentElements().findBy(text("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    public String getBankApprovedOperationContentText() {
        getNotificationContentElements().findBy(text("Ошибка! Банк отказал в проведении операции.")).shouldBe(hidden);
        return getNotificationContentElements().findBy(text("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

}
