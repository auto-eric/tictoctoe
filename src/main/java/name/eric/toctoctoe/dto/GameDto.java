package name.eric.toctoctoe.dto;

import lombok.Data;

@Data
public class GameDto {

    private Status status;
    private Character[][] field;
}
