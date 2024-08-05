package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String nickname;
}
