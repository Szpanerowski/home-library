package pl.put.swolarz.application.common.exception;

import org.springframework.stereotype.Component;


@Component
public class ApplicationExceptionRenderer {


    public String renderException(ApplicationException exception) {

        String messageKey = exception.getErrorType().getMessageKey();
        // Todo localization
        return messageKey;
    }
}
