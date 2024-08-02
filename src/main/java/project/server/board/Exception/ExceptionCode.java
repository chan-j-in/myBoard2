package project.server.board.Exception;

import lombok.Getter;

public enum ExceptionCode {

    BOARD_NOT_FOUND(400, "board not found"),
    REPLY_NOT_FOUND(400, "reply not found");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
