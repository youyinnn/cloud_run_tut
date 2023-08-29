package com.example.dsitributed_system_a1_server;

import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class APITest {

    @Test
    public void getSkierDayVerticalTest() throws IOException {

        Integer resortID = 123;
        String seasonID = "null";
        String dayID = "null";
        Integer skierID = 456;

        String url  = "http://localhost:8080/skiers/" + resortID + "/seasons/" + seasonID + "/days/" + 1233 + "/skiers/" + skierID;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();






        

        final Response response = client.newCall(request).execute();
        assert response.body() != null;
        System.out.println(response.body().string());
    }

    @Test
    public void writeNewLiftRideTest() throws IOException {

        Integer resortID = 123;
        String seasonID = "aaa";
        String dayID = "bbb";
        Integer skierID = 567;

        String url  = "http://localhost:8080/skiers/" + resortID + "/seasons/" + seasonID + "/days/" + 1233 + "/skiers/" + skierID;


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"time\": 217,\n  \"liftID\": 21\n}");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        final Response response = client.newCall(request).execute();
        assert response.body() != null;
        System.out.println(response.code());
    }


}
