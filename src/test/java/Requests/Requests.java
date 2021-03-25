package Requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {

    public Response GET(String url) {
        return given()
                .when()
                .get("/" + url);
    }
}
