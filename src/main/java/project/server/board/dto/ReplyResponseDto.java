package project.server.board.dto;

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

    public static ReplyResponseDto FindFromReply(Reply reply) {
        return new ReplyResponseDto(
                reply.getId(),
                reply.getContent()
        );
    }
}
