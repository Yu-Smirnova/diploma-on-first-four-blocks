package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String id;
        private String number;
        private String status;
    }

    public static CardInfo getActiveCardInfo() {
        return new CardInfo("1", "4444 4444 4444 4441", "APPROVED");
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("2", "4444 4444 4444 4442", "DECLINED");
    }

    public static String generateFullName(String locale) {
        Faker faker = new Faker(Locale.forLanguageTag(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generateMonthForCard(){
        String [] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int randomIndex = new Random().nextInt(months.length);
        String monthForCard = months[randomIndex];
        return monthForCard;
    }

    public static String generateYearForCard(){
        return "22";
    }

    public static String generateCvvCode(){
        Faker faker = new Faker();
        String cvv = faker.numerify("###");
        return cvv;
    }

}
