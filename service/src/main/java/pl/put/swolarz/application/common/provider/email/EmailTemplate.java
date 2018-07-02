package pl.put.swolarz.application.common.provider.email;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum EmailTemplate {
    REGISTRATION_CONFIRMATION("registration-confirmation.html"),
    USER_INVITATION("user-invitation.html");

    private static final String TEMPLATE_RESOURCES_PATH = "/mail/template/";

    private String templateFile;

    public String getTemplatePath() {
        return TEMPLATE_RESOURCES_PATH + templateFile;
    }
}
