package name.eric.toctoctoe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import name.eric.toctoctoe.dto.MoveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

    @Value("http://localhost:${local.server.port}/game")
    private String url;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void putMove() {

        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
            .body(new MoveDto())
        .when()
            .put(url)
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.OK.value());
        //@formatter:on

    }
}