package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.DataHelper;
import data.SQLHelper;
import page.CreditGatePage;
import page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Credit Gate Tests")
public class CreditGateTest {

    private CreditGatePage creditGatePage = new CreditGatePage();
    private HomePage homePage = new HomePage();

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        homePage.homePage();
        homePage.credit();
        creditGatePage.creditGatePage();
    }

    @AfterAll
    public static void shouldCleanBase() {
        SQLHelper.cleanBase();
    }

    String declinedCardNumber = DataHelper.getCardDeclined().getCardNumber();
    String declinedCardStatus = DataHelper.getCardDeclined().getCardStatus();
    String randomCardNumber17Digits = DataHelper.getRandomCardNumber17Digits();
    String randomCardNumber15Digits = DataHelper.getRandomCardNumber15Digits();
    String randomCardNumber40Digits = DataHelper.getRandomCardNumber40Digits();
    String emptyField = DataHelper.getEmptyField();
    String validExpiryMonth = DataHelper.getValidExpiryMonth();
    String validExpiryYear = DataHelper.getValidExpiryYear();
    String random3Digits = DataHelper.getRandom3Digits();
    String random1Digits = DataHelper.getRandom1Digits();
    String random10Digits = DataHelper.getRandom10Digits();
    String validOwner = DataHelper.getValidOwner();
    String uppercaseValidOwner = DataHelper.getUppercaseValidOwner();
    String lowercaseValidOwner = DataHelper.getLowercaseValidOwner();
    String invalidOwner = DataHelper.getInvalidOwner();
    String uppercaseInvalidOwner = DataHelper.getUppercaseInvalidOwner();
    String lowercaseInvalidOwner = DataHelper.getLowercaseInvalidOwner();
    String random2Digits = DataHelper.getRandom2Digits();
    String random4Digits = DataHelper.getRandom4Digits();


    @Test
    @DisplayName("Should be open the page for buying a tour on credit")
    void shouldBeOpenThePageForBuyingATourOnCredit() {
        // No need for test steps here since it's just checking if the page is open
    }

    @Test
    @DisplayName("Should be displayed error notification when the bank declines the transaction with invalid card details")
    void shouldDisplayErrorNotificationWhenBankDeclinesTransactionWithInvalidCardDetails() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered 17 random numeric characters when entering credit card number")
    void shouldBeEntered17RandomNumericCharactersWhenEnteringCreditCardNumber() {
        creditGatePage.fillCardData(randomCardNumber17Digits, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered 15 random numeric characters when entering credit card number")
    void shouldBeEntered15RandomNumericCharactersWhenEnteringCreditCardNumber() {
        creditGatePage.fillCardData(randomCardNumber15Digits, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 40 random numeric characters when entering credit card number")
    void shouldBeEntered40RandomNumericCharactersWhenEnteringCreditCardNumber() {
        creditGatePage.fillCardData(randomCardNumber40Digits, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be empty input field when entering credit card number")
    void shouldBeEmptyInputFieldWhenEnteringCreditCardNumber() {
        creditGatePage.fillCardData(emptyField, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", creditGatePage.getEmptyFieldText());
    }


    @Test
    @DisplayName("Should be entered 3 numeric characters in month field when making credit purchase")
    void shouldBeEntered3NumericCharactersInMonthFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, random3Digits, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", creditGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be entered 1 numeric character in month field when making credit purchase")
    void shouldBeEntered1NumericCharacterInMonthFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, random1Digits, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 10 numeric characters in month field when making credit purchase")
    void shouldBeEntered10NumericCharactersInMonthFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, random10Digits, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", creditGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be empty input field in month field when making credit purchase")
    void shouldBeEmptyInputFieldInMonthFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, emptyField, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", creditGatePage.getEmptyFieldText());
    }


    @Test
    @DisplayName("Should be entered 3 numeric characters in Year field when making credit purchase")
    void shouldBeEntered3NumericCharactersInYearFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, random3Digits, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", creditGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be entered 1 numeric character in Year field when making credit purchase")
    void shouldBeEntered1NumericCharacterInYearFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, random1Digits, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 10 numeric characters in Year field when making credit purchase")
    void shouldBeEntered10NumericCharactersInYearFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, random10Digits, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", creditGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be empty input field in Year field when making credit purchase")
    void shouldBeEmptyInputFieldInYearFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, emptyField, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", creditGatePage.getEmptyFieldText());
    }

    @Test
    @DisplayName("Should be entered Cyrillic name in the owner field when making a credit purchase")
    void shouldBeEnteredCyrillicNameInOwnerFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, invalidOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered uppercase Cyrillic name in the owner field when making a credit purchase")
    void shouldBeEnteredUppercaseCyrillicNameInOwnerFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, uppercaseInvalidOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered lowercase Cyrillic name in the owner field when making a credit purchase")
    void shouldBeEnteredLowercaseCyrillicNameInOwnerFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, lowercaseInvalidOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered uppercase Latin name in the owner field when making a credit purchase")
    void shouldBeEnteredUppercaseLatinNameInOwnerFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, uppercaseValidOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered lowercase Latin name in the owner field when making a credit purchase")
    void shouldBeEnteredLowercaseLatinNameInOwnerFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, lowercaseValidOwner, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be an empty input field in the owner field when making a credit purchase")
    void shouldBeEmptyInputFieldInOwnerFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, emptyField, random3Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", creditGatePage.getEmptyFieldText());
    }

    @Test
    @DisplayName("Should be entered 4 numeric characters in CVC field when making a credit purchase")
    void shouldBeEntered4NumericCharactersInCVCFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random4Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered 2 numeric characters in CVC field when making a credit purchase")
    void shouldBeEntered2NumericCharactersInCVCFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random2Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Неверный формат", creditGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 10 numeric characters in CVC field when making a credit purchase")
    void shouldBeEntered10NumericCharactersInCVCFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random10Digits);
        creditGatePage.clickContinueButton();
        assertEquals("Ошибка", creditGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", creditGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be an empty input field in CVC field when making a credit purchase")
    void shouldBeEmptyInputFieldInCVCFieldWhenMakingCreditPurchase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, validOwner, emptyField);
        creditGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", creditGatePage.getEmptyFieldText());
    }

        @Test
    @DisplayName("Should be displayed the card declined status in the database")
    void shouldBeDisplayedTheCardDeclinedStatusInTheDatabase() {
        creditGatePage.fillCardData(declinedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        creditGatePage.clickContinueButton();
        creditGatePage.getBankDeclinedOperationTitleText();
        creditGatePage.getBankDeclinedOperationContentText();
        assertEquals(declinedCardStatus, SQLHelper.getCardStatus("credit_request_entity"));
    }

}