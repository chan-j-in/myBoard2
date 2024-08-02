package project.server.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.server.board.dto.BoardPatchDto;
import project.server.board.dto.BoardPostDto;
import project.server.board.dto.BoardResponseDto;
import project.server.board.service.BoardService;

@RestController
@Getter
@Setter
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity postBoard(@RequestBody @Validated BoardPostDto boardPostDto) {

        Long id = boardService.createBoard(boardPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchBoard(@PathVariable("id") Long id, @RequestBody @Validated BoardPatchDto boardPatchDto) {

        boardService.updateBoard(boardPatchDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") Long id) {

        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getBoard(@PathVariable("id") Long id) {

        BoardResponseDto boardResponseDto = boardService.findByBoardId(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
}
