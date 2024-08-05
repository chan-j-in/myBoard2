package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.board.Exception.BusinessLogicException;
import project.server.board.Exception.ExceptionCode;
import project.server.board.dto.*;
import project.server.board.entity.Board;
import project.server.board.entity.Member;
import project.server.board.entity.Reply;
import project.server.board.repository.BoardRepository;
import project.server.board.repository.ReplyRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MemberService memberService;

    public Long createReply(ReplyPostDto replyPostDto, Long boardId) {
        Reply reply = new Reply();
        reply.setBoard(boardService.findBoardId(boardId));
        reply.setContent(replyPostDto.getContent());
        reply.setMember(memberService.findByNickname(replyPostDto.getNickname()));

        return replyRepository.save(reply).getId();
    }

    @Transactional
    public Long updateReply(ReplyPatchDto replyPatchDto, Long replyId, Long boardId) {
        Reply reply = findReplyId(replyId);
        reply.setBoard(boardService.findBoardId(boardId));
        reply.setContent(replyPatchDto.getContent());
        reply.setMember(memberService.findByNickname(replyPatchDto.getNickname()));

        return replyRepository.save(reply).getId();
    }

    @Transactional
    public void deleteReply(Long boardId, Long replyId) {
        findBoardId(boardId);
        findReplyId(replyId);
        replyRepository.deleteById(replyId);
    }

    @Transactional
    public ReplyResponseDto findByReplyId(Long id) {
        Reply reply = findReplyId(id);
        return ReplyResponseDto.FindFromReply(reply);
    }

    @Transactional
    public Page<ReplyResponseDto> findAllReplies(Pageable pageable, Long boardId) {
        Board board = boardService.findBoardId(boardId);
        Page<Reply> replies = replyRepository.findByBoard(board, pageable);
        return replies.map(ReplyResponseDto::FindFromReply);
    }

    @Transactional
    public Reply findReplyId(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.REPLY_NOT_FOUND));
    }

    @Transactional
    public Board findBoardId(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
}
