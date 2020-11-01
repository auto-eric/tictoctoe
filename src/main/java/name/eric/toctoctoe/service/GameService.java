package name.eric.toctoctoe.service;

import name.eric.toctoctoe.dto.GameDto;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public GameDto processMove(){
        return new GameDto();
    }
}
