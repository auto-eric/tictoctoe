package name.eric.toctoctoe.dto;

import lombok.Data;

@Data
public class GameDto {

    private Integer id;
    private Status status;
    private Character starter;
    private Character[][] field;
}
