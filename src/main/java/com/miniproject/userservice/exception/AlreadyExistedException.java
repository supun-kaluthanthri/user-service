package com.miniproject.userservice.exception;

import com.miniproject.userservice.utils.ValidationConst;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AlreadyExistedException extends Exception{
    private final ValidationConst validationValue;

    public AlreadyExistedException(final ValidationConst validationConst, final String message) {
        super(message);
        this.validationValue = validationConst;
    }
}
