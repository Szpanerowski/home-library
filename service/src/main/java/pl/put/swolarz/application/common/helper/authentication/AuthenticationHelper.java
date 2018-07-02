package pl.put.swolarz.application.common.helper.authentication;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationHelper {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean passwordMatches(String password, String accountPasswordHash) {
        return BCrypt.checkpw(password, accountPasswordHash);
    }
}
