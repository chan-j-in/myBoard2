package project.server.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.server.board.entity.Reply;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDto {

    private Long id;
    private String content;
    private String nickname;
    private Long boardId;

    public static ReplyResponseDto FindFromReply(Reply reply) {
        return new ReplyResponseDto(
                reply.getId(),
                reply.getContent(),
                reply.getMember().getNickname(),
                reply.getBoard().getId()
        );
    }
}
