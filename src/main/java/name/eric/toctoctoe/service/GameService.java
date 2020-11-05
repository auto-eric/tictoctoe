package name.eric.toctoctoe.service;

import lombok.RequiredArgsConstructor;
import name.eric.toctoctoe.dto.GameDto;
import name.eric.toctoctoe.dto.MoveDto;
import name.eric.toctoctoe.dto.Status;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class GameService {

    private static Character[] CHARACTER_SET = {'x', 'o', null};
    private static Random RANDOM = new Random();

    private static Character[][] MOVE_OF_X = new Character[][]{
            {'x', 'x', 'o'},
            {'o', null, null},
            {null, null, null}
    };

    private static Character[][] MOVE_OF_O = new Character[][]{
            {'x', 'x', 'o'},
            {'o', 'x', null},
            {null, null, null}
    };

    private Referee decisionService;

    public GameDto retrieveGame(Integer gameId) {
        var gameDto = new GameDto();
        gameDto.setStatus(Status.RUNNING);
        gameDto.setId(gameId);
        if (gameId == 1) {
            gameDto.setField(MOVE_OF_X);
        } else if(gameId == 2) {
            gameDto.setField(MOVE_OF_O);
        } else {
            gameDto.setField(new Character[3][3]);
        }
        return gameDto;
    }

    public GameDto processMove(Integer gameId, MoveDto move) {
        //decisionService.check(move);
        GameDto gameDto = new GameDto();
        gameDto.setStatus(Status.RUNNING);
        gameDto.setField(new Character[3][3]);
        return gameDto;
    }

    // TODO fill random valid field
    private Character[][] fillRandomField(){
        int index = RANDOM.nextInt(CHARACTER_SET.length);
        var value = CHARACTER_SET[index];
        return new Character[3][3];
    }
}
