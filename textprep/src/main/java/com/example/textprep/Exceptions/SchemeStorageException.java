package com.example.textprep.Exceptions;

public abstract class SchemeStorageException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;

    public SchemeStorageException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
