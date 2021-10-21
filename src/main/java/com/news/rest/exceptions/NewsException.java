package com.news.rest.exceptions;

public class NewsException extends RuntimeException {
    public NewsException(String exMessage) {
        super(exMessage);
    }
}
