package pl.sobczak.rebel.utils;

import com.github.javafaker.Faker;

public class DataFaker {

    private static final Faker faker = new Faker();

    public static String getFakeUsername() {
        return faker.name().username();
    }

    public static String getFakeEmail() {
        return faker.internet().safeEmailAddress("pl");
    }

    public static String getFakePassword() {
        return faker.internet().password();
    }
}
