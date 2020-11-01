package name.eric.toctoctoe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameControllerTest {

    @Value("http://localhost:8080/game")
    private String url;

    @Test
    public void putMove() {

        //@formatter:off
//        given()
//            .body(new MoveDto())
//        .when()
//            .put(url)
//        .then()
//            .statusCode(HttpStatus.OK.value());
        //@formatter:on

    }
}