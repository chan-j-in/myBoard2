package project.server.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.server.board.Exception.BusinessLogicException;
import project.server.board.Exception.ExceptionCode;
import project.server.board.dto.BoardPatchDto;
import project.server.board.dto.BoardPostDto;
import project.server.board.dto.BoardResponseDto;
import project.server.board.entity.Board;
import project.server.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long createBoard(BoardPostDto boardPostDto) {
        Board board = new Board();
        board.setTitle(boardPostDto.getTitle());
        board.setContent(boardPostDto.getContent());

        return boardRepository.save(board).getId();
    }

    public Long updateBoard(BoardPatchDto boardPatchDto, Long id) {
        Board board = findBoardId(id);
        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());

        return boardRepository.save(board).getId();
    }

    public Board findBoardId(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public void deleteBoard(Long id) {
        findBoardId(id);
        boardRepository.deleteById(id);
    }

    public BoardResponseDto findByBoardId(Long id) {
        Board board = findBoardId(id);
        return BoardResponseDto.FindFromBoard(board);
    }
}
