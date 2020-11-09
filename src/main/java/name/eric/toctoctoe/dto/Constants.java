package name.eric.toctoctoe.dto;

public class Constants {
    public static Character PLAYER_X = 'x';
    public static Character PLAYER_O = 'o';

    public static Character[][] MOVE_OF_X = new Character[][]{
            {'x', 'x', 'o'},
            {'o', null, null},
            {null, null, null}
    };

    public static Character[][] MOVE_OF_O = new Character[][]{
            {'x', 'x', 'o'},
            {'o', 'x', null},
            {null, null, null}
    };



}
