package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByIdAndDeletedDateIsNull(Long id);

    List<Account> getAllByUserIdAndAndDeletedDateIsNull(Long userId);
}
