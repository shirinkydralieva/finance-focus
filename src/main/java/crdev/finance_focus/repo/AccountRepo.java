package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findAccountByNameAndUserId(String name, Long userId);
}
