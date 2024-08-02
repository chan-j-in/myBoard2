package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyPatchDto {

    @NotEmpty(message = "댓글을 입력하세요")
    private String content;
}
