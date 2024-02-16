package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByIdAndDeletedDateIsNull(Long id);

    List<Account> getAllByUserIdAndAndDeletedDateIsNull(Long userId);
}
