package project.server.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.server.board.dto.ReplyPatchDto;
import project.server.board.dto.ReplyPostDto;
import project.server.board.dto.ReplyResponseDto;
import project.server.board.service.ReplyService;

@RestController
@Getter
@Setter
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping({"/{id}"})
    public ResponseEntity postReply(
            @PathVariable("id") Long boardId,
            @RequestBody @Validated ReplyPostDto replyPostDto) {

        Long id = replyService.createReply(replyPostDto, boardId);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/{boardId}/{replyId}")
    public ResponseEntity replyBoard(
            @PathVariable("boardId") Long boardId,
            @PathVariable("replyId") Long replyId,
            @RequestBody @Validated ReplyPatchDto replyPatchDto) {

        replyService.updateReply(replyPatchDto, replyId, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(replyId);
    }

    @DeleteMapping("/{boardId}/{replyId}")
    public ResponseEntity deleteReply(@PathVariable("boardId") Long boardId,
                                      @PathVariable("replyId") Long replyId) {

        replyService.deleteReply(boardId, replyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Page<ReplyResponseDto>> getReply(
            @PathVariable("boardId") Long boardId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page - 1,  size);
        Page<ReplyResponseDto> replies = replyService.findAllReplies(pageable, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(replies);
    }
}
