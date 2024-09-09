package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement heading = $(withText("Путешествие дня"));
    private SelenideElement buyButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public void homePage() {
        heading.shouldBe(visible);
    }

    public PaymentGatePage payment() {
        buyButton.click();
        return new PaymentGatePage();
    }

    public CreditGatePage credit() {
        creditButton.click();
        return new CreditGatePage();
    }

}