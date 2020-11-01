package name.eric.toctoctoe;

import lombok.RequiredArgsConstructor;
import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @RequestMapping(value = "/game/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GameDto putMove(@PathVariable("id") UUID gameId, @RequestBody MoveDto move){
        return gameService.processMove();
    }
}
