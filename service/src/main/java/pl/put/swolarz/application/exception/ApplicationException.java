package pl.put.swolarz.application.exception;

import lombok.Getter;
import pl.put.swolarz.application.exception.type.ApplicationExceptionType;

public class ApplicationException extends Exception {

    @Getter
    private ApplicationExceptionType type;

    public ApplicationException(ApplicationExceptionType type) {
        super();

        this.type = type;
    }
}
