package pl.put.swolarz.infrastructure.manager.session.impl;

import pl.put.swolarz.infrastructure.manager.session.UserSession;
import pl.put.swolarz.infrastructure.manager.session.UserSessionManager;

import java.util.Locale;

public class UserSessionManagerImpl implements UserSessionManager {

    @Override
    public Locale getCurrentLocale(UserSession session) {
        return Locale.US;
    }
}
