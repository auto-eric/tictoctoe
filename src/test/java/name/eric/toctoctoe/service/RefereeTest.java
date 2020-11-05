package name.eric.toctoctoe.service;

import name.eric.toctoctoe.dto.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static name.eric.toctoctoe.dto.Constants.PLAYER_X;

class RefereeTest {

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

    private static Character[][] WINNER_X = new Character[][]{
            {'x', 'x', 'x'},
            {'o', 'o', 'o'},
            {'x', 'x', null}
    };

    private static Character[][] WINNER_O = new Character[][]{
            {'x', 'x', 'o'},
            {'o', 'o', 'o'},
            {'o', 'x', null}
    };

    Referee referee = new Referee();

    @Test
    void checkUndecided() {
        Assertions.assertEquals(Optional.empty(),referee.check(MOVE_OF_X));
        Assertions.assertEquals(Optional.empty(),referee.check(MOVE_OF_O));
    }

    @Test
    void checkWinner() {
        Assertions.assertEquals(Constants.PLAYER_X, referee.check(WINNER_X).get());
        Assertions.assertEquals(Constants.PLAYER_O, referee.check(WINNER_O).get());
    }
}