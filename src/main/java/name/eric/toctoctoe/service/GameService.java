package name.eric.toctoctoe.service;

import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.Status;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public GameDto processMove(){
        GameDto gameDto = new GameDto();
        gameDto.setStatus(Status.RUNNING);

        gameDto.setField(new Character[3][3]);

        return gameDto;
    }
}
