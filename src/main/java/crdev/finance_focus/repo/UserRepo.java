package crdev.finance_focus.repo;

import crdev.finance_focus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameAndPasswordAndDeletedDateIsNull(String username, String password);
    Optional<User> findByUsernameAndDeletedDateIsNull(String username);
    Optional<User> findByIdAndDeletedDateIsNull(Long id);
}
