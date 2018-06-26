package pl.put.swolarz.application.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class AccountPasswordHelper {


    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean passwordMatches(String password, String accountPasswordHash) {
        return BCrypt.checkpw(password, accountPasswordHash);
    }
}
