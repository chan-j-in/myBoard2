package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ReplyPostDto {

    @NotEmpty(message = "댓글을 입력하세요")
    private String content;
}
