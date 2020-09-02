package com.example.topjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Voiting time is over")
public class VoitingTimeIsOverException extends RuntimeException {
    public VoitingTimeIsOverException() {
    }
}
