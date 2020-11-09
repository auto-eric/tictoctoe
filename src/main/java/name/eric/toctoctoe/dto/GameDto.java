package name.eric.toctoctoe.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("GameDto")
@Data
public class GameDto {

    @ApiModelProperty("the Id of the game")
    private Integer id;
    @ApiModelProperty("the current status of the game")
    private Status status;
    @ApiModelProperty("the game field")
    private Character[][] field;
}
