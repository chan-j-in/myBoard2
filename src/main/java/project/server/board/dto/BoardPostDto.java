package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostDto {
    @NotEmpty(message = "제목을 입력하세요")
    private String title;
    @NotEmpty(message = "내용을 입력하세요")
    private String content;
}
