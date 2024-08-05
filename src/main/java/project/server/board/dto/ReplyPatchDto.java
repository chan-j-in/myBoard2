package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyPatchDto {

    @NotEmpty
    private String content;
    @NotEmpty
    private String nickname;
}
