package world.raketa.utils;

import com.github.javafaker.Faker;
import java.util.Locale;


public class DataGenerationUtils {
    Faker faker = new Faker(Locale.ENGLISH);
    public String getFirstName() {
        return faker.name().firstName();
    }
    public String getUserNumber() {
        return faker.numerify("##########");
    }

}