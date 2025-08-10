package helper;

import entity.Pet;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGenerator {
    public static Pet generatePet() {
        long id = ThreadLocalRandom.current().nextLong(1000, 10000);
        return new Pet(id, "Pet_" + id, "available");
    }
}