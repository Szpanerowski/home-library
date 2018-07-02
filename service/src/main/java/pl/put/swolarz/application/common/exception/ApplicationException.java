package pl.put.swolarz.application.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ApplicationException extends Exception {

    @Getter
    private ApplicationError errorType;
}
