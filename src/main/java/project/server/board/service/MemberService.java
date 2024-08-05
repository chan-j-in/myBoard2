package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.board.Exception.BusinessLogicException;
import project.server.board.Exception.ExceptionCode;
import project.server.board.dto.MemberPatchDto;
import project.server.board.dto.MemberPostDto;
import project.server.board.dto.MemberResponseDto;
import project.server.board.entity.Board;
import project.server.board.entity.Member;
import project.server.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final CustomAuthorityUtils customAuthorityUtils;

    @Transactional
    public Member findMemberId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public Long createMember(MemberPostDto memberPostDto) {
        Member member = new Member();
        member.setEmail(memberPostDto.getEmail());
        member.setPassword(memberPostDto.getPassword());
        member.setNickname(memberPostDto.getNickname());

        return memberRepository.save(member).getId();
    }

    public Long updateMember(MemberPatchDto memberPatchDto, Long id) {
        Member member = findMemberId(id);
        member.setNickname(memberPatchDto.getNickname());
        member.setEmail(memberPatchDto.getNickname());
        member.setPassword(memberPatchDto.getPassword());


        return memberRepository.save(member).getId();
    }

    public MemberResponseDto findByMemberId(Long id) {
        Member member = findMemberId(id);

        return MemberResponseDto.FindFromMember(member);
    }

    public void deleteMember(Long id) {
        findMemberId(id);
        memberRepository.deleteById(id);
    }

}
