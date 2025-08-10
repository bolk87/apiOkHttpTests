package api;

import entity.Pet;
import helper.JsonHelper;
import okhttp3.*;

import java.io.IOException;

public class PetApiClient {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json");

    public Response createPet(Pet pet) throws IOException {
        String json = JsonHelper.toJson(pet);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/pet")
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    public Response getPetById(long petId) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/pet/" + petId)
                .get()
                .build();

        return client.newCall(request).execute();
    }

    public Response updatePet(Pet pet) throws IOException {
        String json = JsonHelper.toJson(pet);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/pet")
                .put(body)
                .build();

        return client.newCall(request).execute();
    }

    public Response deletePet(long petId) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/pet/" + petId)
                .delete()
                .build();

        return client.newCall(request).execute();
    }
}