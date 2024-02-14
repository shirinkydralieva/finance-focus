package crdev.finance_focus.repo;

import crdev.finance_focus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameAndPasswordAndDeletedDateIsNull(String username, String password);
    Optional<User> findByUsernameAndDeletedDateIsNull(String username);
    Optional<User> findByIdAndDeletedDateIsNull(Long id);
}
