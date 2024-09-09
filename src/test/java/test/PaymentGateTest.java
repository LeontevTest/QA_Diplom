package test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.HomePage;
import page.PaymentGatePage;
import data.DataHelper;
import data.SQLHelper;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Payment Gate Tests")
public class PaymentGateTest {

    private PaymentGatePage paymentGatePage = new PaymentGatePage();
    private HomePage homePage = new HomePage();

    String approvedCardNumber = DataHelper.getCardApproved().getCardNumber();
    String approvedCardStatus = DataHelper.getCardApproved().getCardStatus();
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


    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        homePage.homePage();
        homePage.payment();
        paymentGatePage.paymentGatePage();
    }

    @AfterAll
    public static void shouldCleanBase() {
        SQLHelper.cleanBase();
    }

    @Test
    @DisplayName("Should be open the page for buying a tour on card payment")
    void shouldBeOpenThePageForBuyingATourOnCardPayment() {
        // No need for test steps here since it's just checking if the page is open
    }

    @Test
    @DisplayName("Should be displayed success notification when the bank approves the transaction with valid card details during card payment")
    void shouldDisplaySuccessNotificationWhenBankApprovesTransactionWithValidCardDetailsDuringCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Успешно", paymentGatePage.getBankApprovedOperationTitleText());
        assertEquals("Операция одобрена Банком.", paymentGatePage.getBankApprovedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered 17 random numeric characters when entering card number")
    void shouldBeEntered17RandomNumericCharactersWhenEnteringCardNumber() {
        paymentGatePage.fillCardData(randomCardNumber17Digits, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Ошибка", paymentGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", paymentGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered 15 random numeric characters when entering card number")
    void shouldBeEntered15RandomNumericCharactersWhenEnteringCardNumber() {
        paymentGatePage.fillCardData(randomCardNumber15Digits, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 40 random numeric characters when entering card number")
    void shouldBeEntered40RandomNumericCharactersWhenEnteringCardNumber() {
        paymentGatePage.fillCardData(randomCardNumber40Digits, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Ошибка", paymentGatePage.getBankDeclinedOperationTitleText());
        assertEquals("Ошибка! Банк отказал в проведении операции.", paymentGatePage.getBankDeclinedOperationContentText());
    }

    @Test
    @DisplayName("Should be empty input field when entering card number")
    void shouldBeEmptyInputFieldWhenEnteringCardNumber() {
        paymentGatePage.fillCardData(emptyField, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", paymentGatePage.getEmptyFieldText());
    }


    @Test
    @DisplayName("Should be entered 3 numeric characters in month field when making card payment")
    void shouldBeEntered3NumericCharactersInMonthFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, random3Digits, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", paymentGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be entered 1 numeric character in month field when making card payment")
    void shouldBeEntered1NumericCharacterInMonthFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, random1Digits, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 10 numeric characters in month field when making card payment")
    void shouldBeEntered10NumericCharactersInMonthFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, random10Digits, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", paymentGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be empty input field in month field when making card payment")
    void shouldBeEmptyInputFieldInMonthFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, emptyField, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", paymentGatePage.getEmptyFieldText());
    }

    @Test
    @DisplayName("Should be entered 3 numeric characters in year field when making card payment")
    void shouldBeEntered3NumericCharactersInYearFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, random3Digits, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", paymentGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be entered 1 numeric character in year field when making card payment")
    void shouldBeEntered1NumericCharacterInYearFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, random1Digits, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 10 numeric characters in year field when making card payment")
    void shouldBeEntered10NumericCharactersInYearFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, random10Digits, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверно указан срок действия карты", paymentGatePage.getErrorCardTermValidityText());
    }

    @Test
    @DisplayName("Should be empty input field in year field when making card payment")
    void shouldBeEmptyInputFieldInYearFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, emptyField, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", paymentGatePage.getEmptyFieldText());
    }


    @Test
    @DisplayName("Should be entered Cyrillic name in owner field when making card payment")
    void shouldBeEnteredCyrillicNameInOwnerFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, invalidOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered uppercase Cyrillic name in owner field when making card payment")
    void shouldBeEnteredUppercaseCyrillicNameInOwnerFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, uppercaseInvalidOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered lowercase Cyrillic name in owner field when making card payment")
    void shouldBeEnteredLowercaseCyrillicNameInOwnerFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, lowercaseInvalidOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered uppercase Latin name in owner field when making card payment")
    void shouldBeEnteredUppercaseLatinNameInOwnerFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, uppercaseValidOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Успешно", paymentGatePage.getBankApprovedOperationTitleText());
        assertEquals("Операция одобрена Банком.", paymentGatePage.getBankApprovedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered lowercase Latin name in owner field when making card payment")
    void shouldBeEnteredLowercaseLatinNameInOwnerFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, lowercaseValidOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Успешно", paymentGatePage.getBankApprovedOperationTitleText());
        assertEquals("Операция одобрена Банком.", paymentGatePage.getBankApprovedOperationContentText());
    }

    @Test
    @DisplayName("Should be empty input field in owner field when making card payment")
    void shouldBeEmptyInputFieldInOwnerFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, emptyField, random3Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", paymentGatePage.getEmptyFieldText());
    }

    @Test
    @DisplayName("Should be entered 4 numeric characters in CVC field when making card payment")
    void shouldBeEntered4NumericCharactersInCVCFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random4Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Успешно", paymentGatePage.getBankApprovedOperationTitleText());
        assertEquals("Операция одобрена Банком.", paymentGatePage.getBankApprovedOperationContentText());
    }

    @Test
    @DisplayName("Should be entered 2 numeric characters in CVC field when making card payment")
    void shouldBeEntered2NumericCharactersInCVCFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random2Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Неверный формат", paymentGatePage.getErrorFormatText());
    }

    @Test
    @DisplayName("Should be entered 10 numeric characters in CVC field when making card payment")
    void shouldBeEntered10NumericCharactersInCVCFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random10Digits);
        paymentGatePage.clickContinueButton();
        assertEquals("Успешно", paymentGatePage.getBankApprovedOperationTitleText());
        assertEquals("Операция одобрена Банком.", paymentGatePage.getBankApprovedOperationContentText());
    }

    @Test
    @DisplayName("Should be empty input field in CVC field when making card payment")
    void shouldBeEmptyInputFieldInCVCFieldWhenMakingCardPayment() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, validOwner, emptyField);
        paymentGatePage.clickContinueButton();
        assertEquals("Поле обязательно для заполнения", paymentGatePage.getEmptyFieldText());
    }


    @Test
    @DisplayName("Should be displayed the card approved status in the database")
    void shouldBeDisplayedTheCardApprovedStatusInTheDatabase() {
        paymentGatePage.fillCardData(approvedCardNumber, validExpiryMonth, validExpiryYear, validOwner, random3Digits);
        paymentGatePage.clickContinueButton();
        paymentGatePage.getBankApprovedOperationTitleText();
        paymentGatePage.getBankApprovedOperationContentText();
        assertEquals(approvedCardStatus, SQLHelper.getCardStatus("payment_entity"));
    }


}