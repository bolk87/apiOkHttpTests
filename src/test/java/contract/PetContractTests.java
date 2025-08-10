package contract;

import api.PetApiClient;
import entity.Pet;
import helper.JsonHelper;
import helper.TestDataGenerator;
import okhttp3.Response;
import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class PetContractTests {
    private PetApiClient apiClient;
    private Pet testPet;

    @BeforeEach
    void setup() {
        apiClient = new PetApiClient();
        testPet = TestDataGenerator.generatePet();
    }

    @Test
    @DisplayName("Создание питомца: контракт ответа")
    @Tag("contract")
    void createPet_contractValidation() throws IOException {
        Response response = apiClient.createPet(testPet);

        // Проверка статус-кода и заголовков
        assertEquals(200, response.code());
        assertEquals("application/json", response.header("Content-Type"));

        // Проверка структуры ответа
        String responseBody = response.body().string();
        Pet createdPet = JsonHelper.fromJson(responseBody, Pet.class);
        assertNotNull(createdPet.getId());
        assertNotNull(createdPet.getName());
        assertNotNull(createdPet.getStatus());
    }

    @Test
    @DisplayName("Получение питомца: контракт ответа")
    @Tag("contract")
    void getPetById_contractValidation() throws IOException {
        apiClient.createPet(testPet);
        Response response = apiClient.getPetById(testPet.getId());

        assertEquals(200, response.code());
        String responseBody = response.body().string();

        // Проверка обязательных полей
        assertTrue(responseBody.contains("id"));
        assertTrue(responseBody.contains("name"));
        assertTrue(responseBody.contains("status"));
    }

    @Test
    @DisplayName("Удаление питомца: контракт ответа")
    @Tag("contract")
    void deletePet_contractValidation() throws IOException {
        apiClient.createPet(testPet);
        Response response = apiClient.deletePet(testPet.getId());

        assertEquals(200, response.code());
        String responseBody = response.body().string();

        // Проверка структуры ответа при удалении
        assertTrue(responseBody.contains("code"));
        assertTrue(responseBody.contains("message"));
    }
}