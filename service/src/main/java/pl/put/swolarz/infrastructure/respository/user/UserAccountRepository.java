package pl.put.swolarz.infrastructure.respository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.swolarz.domain.entity.user.UserAccount;


public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
