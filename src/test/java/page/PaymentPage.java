package page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.Card;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement heading = $$("h3").findBy(text("Оплата по карте"));
    private SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("input[placeholder='08']");
    private SelenideElement yearField = $("input[placeholder='22']");
    private SelenideElement holderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private SelenideElement cvcField = $("input[placeholder='999']");
    private SelenideElement continueButton = $$("button").findBy(text("Продолжить"));
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");
    private SelenideElement successButton = successNotification.$("button");
    private SelenideElement errorButton = errorNotification.$("button");
    private SelenideElement inputInvalid = $(".input__sub");


    public PaymentPage() {
        heading.shouldBe(Condition.visible);
    }

    public void fulfillData(Card card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolderName());
        cvcField.setValue(card.getCvc());
        continueButton.click();

    }


    public void checkSuccessNotification() {
        successNotification.should(visible, Duration.ofSeconds(15));
        successNotification.$("[class=notification__title]").should(text("Успешно"));
        successNotification.$("[class=notification__content]").should(text("Операция одобрена Банком."));
        successButton.click();
        successNotification.should(hidden);
    }

    public void checkDeclineNotification() {
        errorNotification.should(visible, Duration.ofSeconds(15));
        errorNotification.$("[class=notification__title]").should(text("Ошибка"));
        errorNotification.$("[class=notification__content]").should(text("Ошибка! Банк отказал в проведении операции."));
        errorButton.click();
        errorNotification.should(hidden);
    }

    public void checkInvalidFormat() {
        $(".input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkRequiredField() {
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void checkInvalidDate() {
        $(".input__sub").shouldHave(exactText("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void checkExpiredDate() {
        $(".input__sub").shouldHave(exactText("Истёк срок действия карты")).shouldBe(visible);
    }

    public void checkInvalidName() {
        $(".input__sub").shouldHave(exactText("Введите полное имя и фамилию")).shouldBe(visible);
    }

    public void checkLongName() {
        $(".input__sub").shouldHave(exactText("Значение поля не может содержать более 100 символов")).shouldBe(visible);
    }

    public void checkInvalidDataName() {
        $(".input__sub").shouldHave(exactText("Значение поля может содержать только буквы и дефис")).shouldBe(visible);
    }

    public void checkShortName() {
        $(".input__sub").shouldHave(exactText("Значение поля должно содержать больше одной буквы")).shouldBe(visible);
    }

    public void checkInvalidCvc() {
        $(".input__sub").shouldHave(exactText("Значение поля должно содержать 3 цифры")).shouldBe(visible);
    }

    public void checkAllFieldsAreRequired() {
        requiredFieldError.shouldBe(visible);
    }
}