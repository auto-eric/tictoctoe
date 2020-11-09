package name.eric.toctoctoe;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import name.eric.toctoctoe.dto.Constants;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.awt.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

    @Value("http://localhost:${local.server.port}/game")
    private String url;

    /**
     * {'x', 'x', 'o'},
     * {'o', null, null},
     * {null, null, null}
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

    @Test
    public void put_setValidForO() {
        MoveDto moveDto = new MoveDto();
        moveDto.setField(Constants.MOVE_OF_O);
        moveDto.setUser(Constants.PLAYER_O);
        moveDto.setPoint(new Point(2, 2));

        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
            .header(new Header("Accept", ContentType.JSON.toString()))
                .body(moveDto)
        .when()
            .put(url + "/2")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("field[2][2]", equalTo("o"))
        ;
        //@formatter:on
    }

    @Test
    public void put_setValidForX() {
        MoveDto moveDto = new MoveDto();
        moveDto.setField(Constants.MOVE_OF_X);
        moveDto.setUser(Constants.PLAYER_X);
        moveDto.setPoint(new Point(2, 2));

        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
            .header(new Header("Accept", ContentType.JSON.toString()))
                .body(moveDto)
        .when()
            .put(url + "/2")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("field[2][2]", equalTo("x"))
        ;
        //@formatter:on
    }

    @Test
    public void put_setOccupiedCell() {
        MoveDto moveDto = new MoveDto();
        moveDto.setField(Constants.MOVE_OF_X);
        moveDto.setUser(Constants.PLAYER_X);
        moveDto.setPoint(new Point(0, 0));

        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
            .header(new Header("Accept", ContentType.JSON.toString()))
            .body(moveDto)
            .log().body()
        .when()
            .put(url + "/2")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
        ;
        //@formatter:on
    }

    @Test
    public void put_setWrongPlayer() {
        MoveDto moveDto = new MoveDto();
        moveDto.setField(Constants.MOVE_OF_X);
        moveDto.setUser(Constants.PLAYER_O);
        moveDto.setPoint(new Point(0, 0));

        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
            .header(new Header("Accept", ContentType.JSON.toString()))
            .body(moveDto)
        .when()
            .put(url + "/2")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
        ;
        //@formatter:on
    }

    @Test
    public void post_createNewGame() {
        //@formatter:off
        given()
            .header(new Header("Content-Type", ContentType.JSON.toString()))
            .header(new Header("Accept", ContentType.JSON.toString()))
        .when()
            .post(url)
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("status", equalTo(Status.RUNNING.toString()))
            .body("field.size()", equalTo(3))
            .body("field[0].size()", equalTo(3))
            .body("field[0][0]", nullValue())
            .body("field[0][1]", nullValue())
            .body("field[0][2]", nullValue())

            .body("field[1].size()", equalTo(3))
            .body("field[1][0]", nullValue())
            .body("field[1][1]", nullValue())
            .body("field[1][2]", nullValue())

            .body("field[2].size()", equalTo(3))
            .body("field[2][0]", nullValue())
            .body("field[2][1]", nullValue())
            .body("field[2][2]", nullValue())
        ;
        //@formatter:on
    }

}
