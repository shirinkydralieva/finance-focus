package crdev.finance_focus.repo;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.entity.Expense;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> getAllByAccountIdAndDeletedDateIsNull(Long accountId);

    Optional<Expense> findByIdAndDeletedDateIsNull(Long id);
}
