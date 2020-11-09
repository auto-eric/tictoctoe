package name.eric.toctoctoe.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.*;

@ApiModel("MoveDto: the move of a game")

@Data
public class MoveDto {

    @ApiModelProperty("the user doing the move")
    Character user;
    @ApiModelProperty("the chosen field")
    Point point;
    @ApiModelProperty("the status of the field before the move")
    Character[][] field;
}
