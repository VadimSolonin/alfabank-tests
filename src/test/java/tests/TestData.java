package tests;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    Faker faker = new Faker(new Locale("ru"));
    public String randomNumber = faker.number().digits(1);
    public String randomSurname = faker.name().lastName();
}
