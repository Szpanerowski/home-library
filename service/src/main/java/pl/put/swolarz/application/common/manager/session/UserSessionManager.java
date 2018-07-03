package pl.put.swolarz.application.common.manager.session;

import java.util.Locale;

public interface UserSessionManager {

    Locale getCurrentLocale(UserSession userAccount);
}
