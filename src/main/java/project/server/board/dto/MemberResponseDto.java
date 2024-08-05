package project.server.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.server.board.entity.Member;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String password;

    public static MemberResponseDto FindFromMember(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getPassword()
        );
    }
}
