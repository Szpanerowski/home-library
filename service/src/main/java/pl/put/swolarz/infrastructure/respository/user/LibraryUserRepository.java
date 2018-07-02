package pl.put.swolarz.infrastructure.respository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.swolarz.domain.entity.user.LibraryUser;


public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
}
