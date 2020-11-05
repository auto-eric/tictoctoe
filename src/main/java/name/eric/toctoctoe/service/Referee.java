package name.eric.toctoctoe.service;

import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Arrays.stream;
import static name.eric.toctoctoe.dto.Constants.PLAYER_O;
import static name.eric.toctoctoe.dto.Constants.PLAYER_X;

@Component
public class Referee {

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
        for (int i : new int[]{0, 1, 2}) {
            for (int j : new int[]{0, 1, 2}) {
                Character[] set = new Character[]{field[i][j], field[i][j], field[i][j]};
                winner = checkRowFieldSet(set, player);
            }
        }
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
}
