package name.eric.toctoctoe;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Endpoints to create a new or retrieve an existing game, and to place a game move")
@Slf4j
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @ApiOperation(value = "create an new game", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/game",
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE
    )
    public GameDto createGame() {
        return gameService.prepareNewGame();
    }

    @ApiOperation(value = "get a certain game", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/game/{id}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public GameDto getGame(@PathVariable("id") Integer gameId) {
        return gameService.retrieveGame(gameId);
    }

    @ApiOperation(value = "place the move of a player", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/game/{id}",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public GameDto putMove(@PathVariable("id") Integer gameId, @RequestBody MoveDto move) {
        log.info("received PUT request {}", move);
        return gameService.processMove(gameId, move);
    }
}
