package com.she.file.exception;

public class ZipParsingException extends RuntimeException {
    public ZipParsingException(String reason, Exception inner) {
        super(reason, inner);
    }

}
