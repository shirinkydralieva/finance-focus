package crdev.finance_focus.repo;

import crdev.finance_focus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserById(Long id);
    User findByUsername(String username);
}
