package com.telran.phonebook.apiTests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientTests {

    private String email = "kroos@gm.com";
    private String password = "Kroos12345~";

    @Test
    public void loginHttpClientTest() throws IOException {

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();
        System.out.println(response);

        String responseJson = response.returnContent().asString();
        System.out.println( responseJson);

        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement token = element.getAsJsonObject().get("token");
        System.out.println(token.getAsString());

        // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imtyb29zQGdtLmNvbSJ9.BbVSHfbAvgsFMmWD-6KDLIvdwxyw07MrYPMkdZW2Tmc
    }
}
