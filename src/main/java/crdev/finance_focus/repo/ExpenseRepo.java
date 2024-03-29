package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> getAllByAccountIdAndDeletedDateIsNull(Long accountId);

    Optional<Expense> findByIdAndDeletedDateIsNull(Long id);
    List<Expense> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate);
}
