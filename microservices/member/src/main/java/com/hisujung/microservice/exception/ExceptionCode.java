package com.hisujung.microservice.exception;

import lombok.Getter;

public enum ExceptionCode {
    UNABLE_TO_SEND_EMAIL(404, "Unable To Send Email"),
    MEMBER_EXISTS(404, "MEMBER EXISTS"),
    NO_SUCH_ALGORITHM(404, "NO SUCH ALGORITHM");
    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
