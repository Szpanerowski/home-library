package pl.put.swolarz.infrastructure.manager.session;

import org.springframework.stereotype.Component;
import pl.put.swolarz.domain.entity.user.UserAccount;

import java.util.Locale;

public interface UserSessionManager {

    Locale getCurrentLocale(UserSession userAccount);
}
