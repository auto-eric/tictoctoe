package name.eric.toctoctoe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @RequestMapping(value = "/game/{id}",
    method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
 )
    public GameDto getGame(@PathVariable("id") Integer gameId) {
        return gameService.retrieveGame(gameId);
    }

    @RequestMapping(value = "/game/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GameDto putMove(@PathVariable("id") Integer gameId, @RequestBody MoveDto move){
        log.info("received PUT request {}", move);
        return gameService.processMove(gameId, move);
    }
}
