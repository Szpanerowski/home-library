package pl.put.swolarz.infrastructure.respository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.swolarz.domain.entity.user.AccountRegistration;


public interface AccountRegistrationRepository extends JpaRepository<AccountRegistration, Long> {

    AccountRegistration findByToken(String token);
}
