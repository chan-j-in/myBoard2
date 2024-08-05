package project.server.board.Exception;

import lombok.Getter;

public enum ExceptionCode {

    BOARD_NOT_FOUND(400, "board not found"),
    REPLY_NOT_FOUND(400, "reply not found"),
    MEMBER_NOT_FOUND(400, "member not found"),
    NO_PERMISSION(400, "no permission");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
