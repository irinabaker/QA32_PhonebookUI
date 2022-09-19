package com.telran.phonebook.apiTests;

import api.dto.AuthRequestDto;
import api.dto.AuthResponseDto;
import api.dto.ErrorDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttp3Tests {

    private String email = "kroos@gm.com";
    private String password = "Kroos12345~";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginNegativeTestWithInvalidEmail() throws IOException {

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("kroosgm.com")
                .password(password)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        ErrorDto errorDto = gson.fromJson(responseJson, ErrorDto.class);
//        System.out.println(errorDto.getCode());
//        System.out.println(errorDto.getMessage());

        Assert.assertEquals(response.code(),400);
        Assert.assertEquals(errorDto.getMessage(),"Wrong email format! Example: name@mail.com");
        Assert.assertTrue(errorDto.getMessage().contains("Wrong email"));
    }

    @Test
    public void loginTest() throws IOException {
        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("kroosgm.com")
                .password(password)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
            System.out.println(responseDto.getToken());
            Assert.assertTrue(response.isSuccessful());
            Assert.assertEquals(response.code(),200);
        } else {
            ErrorDto errorDto = gson.fromJson(responseJson, ErrorDto.class);
            System.out.println(errorDto.getCode());
            System.out.println(errorDto.getMessage());
            Assert.assertEquals(response.code(),400);
        }

    }
}
