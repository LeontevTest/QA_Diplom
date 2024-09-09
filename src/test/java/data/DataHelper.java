
package data;

import com.github.javafaker.Faker;

import lombok.Value;

import java.time.Year;
import java.time.YearMonth;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker = new Faker(new Locale("en"));
    private static Faker fakerRus = new Faker(new Locale("ru"));

    @Value
    public static class CardInfo {
        String cardNumber;
        String cardStatus;
    }

    public static CardInfo getCardApproved() {
        return new CardInfo("4444 4444 4444 4441", "APPROVED");
    }

    public static CardInfo getCardDeclined() {
        return new CardInfo("4444 4444 4444 4442", "DECLINED");
    }

    public static String getValidExpiryMonth() {
        int currentYear = YearMonth.now().getYear();
        int currentMonth = YearMonth.now().getMonthValue();

        int year = faker.number().numberBetween(currentYear, currentYear + 10);
        int month;

        if (year == currentYear) {
            month = faker.number().numberBetween(currentMonth, 13);
        } else {
            month = faker.number().numberBetween(1, 13);
        }

        return String.format("%02d", month);
    }

    public static String getValidExpiryYear() {
        int currentYear = YearMonth.now().getYear();
        int year = faker.number().numberBetween(currentYear, currentYear + 10);
        int validYear = Math.min(year, currentYear + 10);
        return String.format("%02d", validYear % 100);
    }

    public static String getValidOwner() {
        return faker.name().fullName();
    }

    public static String getUppercaseValidOwner() {
        return getValidOwner().toUpperCase();
    }

    public static String getLowercaseValidOwner() {
        return getValidOwner().toLowerCase();
    }

    public static String getRandomCardNumber17Digits() {
        String randomCardNumber = faker.business().creditCardNumber();
        char randomDigit = Character.forDigit(faker.number().numberBetween(0, 10), 10);
        return randomCardNumber + randomDigit;
    }

    public static String getRandomCardNumber15Digits() {
        String randomCardNumber = faker.business().creditCardNumber();
        return randomCardNumber.substring(0, randomCardNumber.length() - 1);
    }

    public static String getRandomCardNumber40Digits() {
        String firstCardNumber = faker.business().creditCardNumber();
        return firstCardNumber + firstCardNumber + firstCardNumber.substring(0, firstCardNumber.length() / 2);
    }

    public static String getCardNumberWithSpace() {
        String cardNumber = faker.business().creditCardNumber();
        return cardNumber.substring(0, 8) + " " + cardNumber.substring(8);
    }

    public static String getRandom16SpecialCharacters() {
        return faker.regexify("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]{16}");
    }

    public static String getRandom16CyrillicString() {
        return fakerRus.regexify("[\u0410-\u044F]{" + 16 + "}");
    }

    public static String getRandom16LatinString() {
        return faker.regexify("[a-zA-Z]{" + 16 + "}");
    }

    public static String get16Spaces() {
        return "                ";
    }

    public static String getEmptyField() {
        return "";
    }

    public static String getRandom3Digits() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000));
    }

    public static String getRandom1Digits() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
    }

    public static String getRandom10Digits() {
        return String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L));
    }

    public static String getMonthWithSpaceInTheMiddle() {
        StringBuilder result = new StringBuilder();
        result.append(getValidExpiryMonth().charAt(0)).append(" ").append(getValidExpiryMonth().charAt(1));
        return result.toString();
    }

    public static String getYearWithSpaceInTheMiddle() {
        StringBuilder result = new StringBuilder();
        result.append(getValidExpiryYear().charAt(0)).append(" ").append(getValidExpiryYear().charAt(1));
        return result.toString();
    }

    public static String getRandom2SpecialCharacters() {
        return faker.regexify("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]{2}");
    }

    public static String getRandom2CyrillicString() {
        return fakerRus.regexify("[\u0410-\u044F]{" + 2 + "}");
    }

    public static String getRandom2LatinString() {
        return faker.regexify("[a-zA-Z]{" + 2 + "}");
    }

    public static String get2Spaces() {
        return "  ";
    }

    public static String getRandomValueGreater12() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(13, 100));
    }

    public static String getRandomValueLess23() {
        int randomValue = ThreadLocalRandom.current().nextInt(1, (Year.now().getValue() % 100));
        return String.format("%02d", randomValue);
    }

    public static String get00() {
        return "00";
    }

    public static String getInvalidOwner() {
        return fakerRus.name().fullName();
    }

    public static String getUppercaseInvalidOwner() {
        return getInvalidOwner().toUpperCase();
    }

    public static String getLowercaseInvalidOwner() {
        return getInvalidOwner().toLowerCase();
    }

    public static String getRandomText500Symbols() {
        return faker.lorem().characters(500);
    }

    public static String getRandom2Digits() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10, 100));
    }

    public static String getRandom4Digits() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10_000, 100_000));
    }

    public static String getRandom3DigitNumberWithSpace() {
        int randomThreeDigitNumber = ThreadLocalRandom.current().nextInt(100, 1000);
        return String.format("%d %02d",
                randomThreeDigitNumber / 100,
                randomThreeDigitNumber % 100);
    }

    public static String getRandom3SpecialCharacters() {
        return faker.regexify("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]{3}");
    }

    public static String getRandom3CyrillicString() {
        return fakerRus.regexify("[\u0410-\u044F]{" + 3 + "}");
    }

    public static String getRandom3LatinString() {
        return faker.regexify("[a-zA-Z]{" + 3 + "}");
    }

    public static String get3Spaces() {
        return "   ";
    }
}