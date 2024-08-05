package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.board.Exception.BusinessLogicException;
import project.server.board.Exception.ExceptionCode;
import project.server.board.dto.BoardPatchDto;
import project.server.board.dto.BoardPostDto;
import project.server.board.dto.BoardResponseDto;
import project.server.board.entity.Board;
import project.server.board.entity.Member;
import project.server.board.repository.BoardRepository;
import project.server.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Long createBoard(BoardPostDto boardPostDto) {
        Board board = new Board();
        board.setTitle(boardPostDto.getTitle());
        board.setContent(boardPostDto.getContent());
        board.setMember(memberService.findByNickname(boardPostDto.getNickname()));

        return boardRepository.save(board).getId();
    }

    @Transactional
    public Long updateBoard(BoardPatchDto boardPatchDto, Long boardId) {
        Board board = findBoardId(boardId);
        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());
        board.setMember(memberService.findByNickname(boardPatchDto.getNickname()));

        return boardRepository.save(board).getId();
    }

    @Transactional
    public Board findBoardId(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public void deleteBoard(Long id) {
        findBoardId(id);
        boardRepository.deleteById(id);
    }

    public void isPermission(Member member, String email) {
        if(!member.getEmail().equals(email)) {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);
        }
    }

    @Transactional
    public BoardResponseDto findByBoardId(Long id) {
        Board board = findBoardId(id);
        board.setBoardCount(board.getBoardCount()+1);
        boardRepository.save(board);
        return BoardResponseDto.FindFromBoard(board);
    }

    @Transactional
    public Page<BoardResponseDto> findAllBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(BoardResponseDto::FindFromBoard);
    }
}
