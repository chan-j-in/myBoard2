package project.server.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.server.board.entity.Board;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;

    public static BoardResponseDto FindFromBoard(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember().getNickname()
        );
    }
}
