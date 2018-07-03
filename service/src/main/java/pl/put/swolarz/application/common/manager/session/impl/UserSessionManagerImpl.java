package pl.put.swolarz.application.common.manager.session.impl;

import pl.put.swolarz.application.common.manager.session.UserSession;
import pl.put.swolarz.application.common.manager.session.UserSessionManager;

import java.util.Locale;

public class UserSessionManagerImpl implements UserSessionManager {

    @Override
    public Locale getCurrentLocale(UserSession session) {
        return Locale.US;
    }
}
