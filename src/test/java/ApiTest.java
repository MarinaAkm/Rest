import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiTest {
    @Test()
    public void testGet(){
        Response response =
                given()
            .log().all()
            .when()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .log().all()
            .statusCode(200)
            .extract()
            .response();
        response.getBody().print();
          }
    @Test()
    public void TestPost(){
                given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .log().everything()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .post("/api/users/")

                .then()
                .log().all()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("JsonSchema.json"));
    }
}
