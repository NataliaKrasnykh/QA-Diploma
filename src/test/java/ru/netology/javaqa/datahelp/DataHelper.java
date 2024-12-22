package ru.netology.javaqa.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;


public class DataHelper {

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    private DataHelper() {
    }

    public static String getOwnerCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getOwnerLatin() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getRandomMonth() {
        String[] month = new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return month[new Random().nextInt(month.length)];
    }

    public static String getRandomYear() {
        String[] year = new String[]{
                "25", "26", "27", "28"};
        return year[new Random().nextInt(year.length)];
    }

    public static String getcodeCVV() {
        Faker faker = new Faker();
        return faker.numerify("###");
    }

    public static String getcodeCVVWithTwoDigits() {
        Faker faker = new Faker();
        return faker.numerify("##");
    }

    public static String getcodeCVVWithOneDigits() {
        Faker faker = new Faker();
        return faker.numerify("#");
    }

    public static String getNull() {
        return "00";
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getInvalidYear() {
        return "23";
    }

    public static String getOwnerNumbers() {
        return "123456789";
    }

    public static String getOwnerSymbols() {
        return "@#^&*&^%";
    }

    public static String getMonthOneDigits() {
        Faker faker = new Faker();
        return faker.numerify("#");
    }

    public static String getYearOneDigits() {
        Faker faker = new Faker();
        return faker.numerify("#");
    }

    public static String getCardNumber15Symbols() {
        Faker faker = new Faker();
        return faker.numerify("###############");
    }

    public static CardInfo getEmptyCardInfo() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getApprovedCardOperation() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getDeclinedCardOperation() {
        return new CardInfo(getDeclinedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    //Номер карты
    public static CardInfo getInvalidCardNumber() {
        return new CardInfo(getCardNumber15Symbols(), getRandomMonth(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getRandomMonth(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    //Месяц
    public static CardInfo getInvalidDateMonthNull() {
        return new CardInfo(getApprovedCardNumber(), getNull(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getErrorMonth() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonth(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getErrorMonthOneDigits() {
        return new CardInfo(getApprovedCardNumber(), getMonthOneDigits(), getRandomYear(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), "", getRandomYear(), getOwnerLatin(), getcodeCVV());
    }


    //Год
    public static CardInfo getYearNull() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getNull(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getErrorYearOneDigits() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getYearOneDigits(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getErrorYear() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getInvalidYear(), getOwnerLatin(), getcodeCVV());
    }

    public static CardInfo getYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), "", getOwnerLatin(), getcodeCVV());
    }


    //Владелец
    public static CardInfo getErrorOwnerCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerCyrillic(), getcodeCVV());
    }

    public static CardInfo getOwnerEmpty() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), "", getcodeCVV());
    }

    public static CardInfo getErrorOwnerNumbers() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerNumbers(), getcodeCVV());
    }

    public static CardInfo getErrorOwnerSymbols() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerSymbols(), getcodeCVV());
    }

    //CVV
    public static CardInfo getErrorCVVEmpty() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerSymbols(), "");
    }

    public static CardInfo getErrorCVVOneDigits() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerSymbols(), getcodeCVVWithOneDigits());
    }

    public static CardInfo getErrorCVVTwoDigits() {
        return new CardInfo(getApprovedCardNumber(), getRandomMonth(), getRandomYear(), getOwnerSymbols(), getcodeCVVWithTwoDigits());
    }
}
