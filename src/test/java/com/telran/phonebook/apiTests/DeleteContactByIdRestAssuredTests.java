package com.telran.phonebook.apiTests;

import api.dto.ContactDto;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIdRestAssuredTests {

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imtyb29zQGdtLmNvbSJ9.BbVSHfbAvgsFMmWD-6KDLIvdwxyw07MrYPMkdZW2Tmc";

    int id;

    @BeforeMethod
    public void ensurePreconditions() {
        System.err.close();
        System.setErr(System.out);

        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

        int i = (int) (System.currentTimeMillis() / 1000 % 3600);

        ContactDto contactDto = ContactDto.builder()
                .address("Dortmund")
                .description("Forward")
                .email("emre" + i + "@gmail.com")
                .lastName("Can")
                .name("Emre" + i)
                .phone("12345" + i)
                .build();

        id = given().header("Authorization", token)
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void deleteContactByIdPositiveTest() {
        String status = given().header("Authorization", token)
                .delete("contact/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);
    }
}
