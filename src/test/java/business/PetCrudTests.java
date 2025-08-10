package business;

import api.PetApiClient;
import entity.Pet;
import helper.JsonHelper;
import helper.TestDataGenerator;
import okhttp3.Response;
import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetCrudTests {
    private static PetApiClient apiClient;
    private static Pet testPet;

    @BeforeAll
    static void setup() {
        apiClient = new PetApiClient();
        testPet = TestDataGenerator.generatePet();
    }

    @Test
    @Order(1)
    @DisplayName("Создание питомца")
    @Tag("business")
    void createPet() throws IOException {
        Response response = apiClient.createPet(testPet);
        assertEquals(200, response.code());

        Pet createdPet = JsonHelper.fromJson(response.body().string(), Pet.class);
        assertEquals(testPet.getId(), createdPet.getId());
        assertEquals(testPet.getName(), createdPet.getName());
    }

    @Test
    @Order(2)
    @Tag("business")
    @DisplayName("Получение созданного питомца")
    void getCreatedPet() throws IOException {
        Response response = apiClient.getPetById(testPet.getId());
        assertEquals(200, response.code());

        Pet fetchedPet = JsonHelper.fromJson(response.body().string(), Pet.class);
        assertEquals(testPet.getName(), fetchedPet.getName());
    }

    @Test
    @Order(3)
    @Tag("business")
    @DisplayName("Обновление данных питомца")
    void updatePet() throws IOException {
        testPet.setName("The pet was sold");
        testPet.setStatus("sold");

        Response response = apiClient.updatePet(testPet);
        assertEquals(200, response.code());

        // Проверка обновления имени
        Response getResponse = apiClient.getPetById(testPet.getId());
        Pet updatedPet = JsonHelper.fromJson(getResponse.body().string(), Pet.class);
        assertEquals("The pet was sold", updatedPet.getName());
        assertEquals("sold", updatedPet.getStatus());
    }

    @Test
    @Tag("business")
    @DisplayName("Удаление питомца")
    void deletePet() throws IOException {
        Response deleteResponse = apiClient.deletePet(testPet.getId());
        assertEquals(200, deleteResponse.code());

        // Проверка, что питомец был удален
        Response getResponse = apiClient.getPetById(testPet.getId());
        assertEquals(404, getResponse.code());
    }
}