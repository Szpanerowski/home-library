package pl.put.swolarz.application.exception.type;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionType {
    // Todo translation
    REGISTRATION_NOT_FOUND("Given registration not found.");

    private String message;
}
