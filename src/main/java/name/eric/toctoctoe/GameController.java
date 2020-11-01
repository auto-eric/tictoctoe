package name.eric.toctoctoe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @RequestMapping(value = "/game",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GameDto putMove(@RequestBody MoveDto move){
        log.info("received PUT request");
        return gameService.processMove();
    }
}
