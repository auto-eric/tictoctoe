package name.eric.toctoctoe.service;

import name.eric.toctoctoe.dto.Status;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

import static java.util.Arrays.stream;
import static name.eric.toctoctoe.dto.Constants.PLAYER_O;
import static name.eric.toctoctoe.dto.Constants.PLAYER_X;

@Component
public class Referee {

    public Status checkStatus(Character[][] field) {
        Optional<Character> winnerOptional = this.check(field);
        Status status = Status.RUNNING;

        if (winnerOptional.isPresent()) {
            Character winner = winnerOptional.get();
            status = winner.equals(PLAYER_X) ? Status.X_WON : Status.O_WON;
        }
        return status;
    }

    /**
     * @return either the winner character or null
     */
    public Optional<Character> check(Character[][] field) {
        Optional<Character> winner = check(field, PLAYER_X);
        if (winner.isPresent()) {
            return winner;
        }
        winner = check(field, PLAYER_O);
        if (winner.isPresent()) {
            return winner;
        }
        return Optional.empty();
    }

    private Optional<Character> check(Character[][] field, Character player) {
        Optional<Character> winner = findRowWinner(field, player);
        if (winner.isPresent()) {
            return winner;
        }
        winner = findColumnWinner(field, player, winner);
        if (winner.isPresent()) {
            return winner;
        }
        winner = findDiagonalOneWinner(field, player);
        if (winner.isPresent()) {
            return winner;
        }
        return findDiagonalTwoWinner(field, player);
    }

    private Optional<Character> findDiagonalTwoWinner(Character[][] field, Character player) {
        Character[] set = new Character[]{field[0][2], field[1][1], field[2][0]};
        return checkRowFieldSet(set, player);
    }

    private Optional<Character> findDiagonalOneWinner(Character[][] field, Character player) {
        Character[] set = new Character[]{field[0][0], field[1][1], field[2][2]};
        return checkRowFieldSet(set, player);
    }

    private Optional<Character> findRowWinner(Character[][] field, Character player) {
        return stream(field)
                .map(row -> checkRowFieldSet(row, player))
                .findFirst()
                .orElse(Optional.empty());
    }

    private Optional<Character> findColumnWinner(Character[][] field, Character player, Optional<Character> winner) {
        int i = 0;
        boolean found = false;
        do {
            Character[] set = new Character[]{field[i][0], field[i][1], field[i][2]};
            winner = checkRowFieldSet(set, player);
            found = winner.isPresent() ? true : false;
            i++;
        } while (i<2 && !found );
        return winner;
    }

    private Optional<Character> checkRowFieldSet(Character[] set, final Character player) {
        var count = stream(set)
                .filter(c -> player.equals(c))
                .count();
        if (count == 3) {
            return Optional.of(player);
        }
        return Optional.empty();
    }

    public void checkValueSetting(Character user, Character[][] field) {
        long countMovesPlayerX = countPlayerMoves(field, PLAYER_X);
        long countMovesPlayerO = countPlayerMoves(field, PLAYER_O);

        if (user.equals(PLAYER_X) && countMovesPlayerX == countMovesPlayerO) {
            return;
        } else if (user.equals(PLAYER_O) && countMovesPlayerX == countMovesPlayerO + 1) {
            return;
        } else throw new InvalidDataException("wrong player tried to place a move");
    }

    private long countPlayerMoves(Character[][] field, Character player) {
        return stream(field)
                .flatMap(r -> stream(r))
                .filter(c -> player.equals(c))
                .count();
    }

    public void setValue(Character[][] field, Point point, Character player) {
        if (field[point.x][point.y] != null) {
            throw new InvalidDataException("field is not free");
        }
        field[point.x][point.y] = player;
    }

}
