package pl.put.swolarz.application.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationError {
    REGISTRATION_NOT_FOUND("registrationNotFound");

    private String messageKey;
}
