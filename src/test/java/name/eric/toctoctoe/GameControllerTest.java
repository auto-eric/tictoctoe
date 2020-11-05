package name.eric.toctoctoe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;


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
            .put(url + "/1")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.OK.value());
        //@formatter:on

    }

    /**
     *  {'x', 'x', 'o'},
     *  {'o', null, null},
     *  {null, null, null}
     *
     */
    @Test
    public void get_mustReturnGame1() {
        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
        .when()
            .get(url + "/1")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("status", equalTo(Status.RUNNING.toString()))
            .body("field.size()", equalTo(3))

            .body("field[0].size()", equalTo(3))
            .body("field[0][0]", equalTo("x"))
            .body("field[0][1]", equalTo("x"))
            .body("field[0][2]", equalTo("o"))

            .body("field[1].size()", equalTo(3))
            .body("field[1][0]", equalTo("o"))
            .body("field[1][1]", nullValue())
            .body("field[1][2]", nullValue())

            .body("field[2].size()", equalTo(3))
            .body("field[2][0]", nullValue())
            .body("field[2][1]", nullValue())
            .body("field[2][2]", nullValue())
        ;
        //@formatter:on
    }

    /**
     * {'x', 'x', 'o'},
     * {'o', 'x', null},
     * {null, null, null}
     */
    @Test
    public void get_mustReturnGame2() {
        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
        .when()
            .get(url + "/2")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("status", equalTo(Status.RUNNING.toString()))
            .body("field.size()", equalTo(3))

            .body("field[0].size()", equalTo(3))
            .body("field[0][0]", equalTo("x"))
            .body("field[0][1]", equalTo("x"))
            .body("field[0][2]", equalTo("o"))

            .body("field[1].size()", equalTo(3))
            .body("field[1][0]", equalTo("o"))
            .body("field[1][1]", equalTo("x"))
            .body("field[1][2]", nullValue())

            .body("field[2].size()", equalTo(3))
            .body("field[2][0]", nullValue())
            .body("field[2][1]", nullValue())
            .body("field[2][2]", nullValue())
        ;
        //@formatter:on
    }
}
