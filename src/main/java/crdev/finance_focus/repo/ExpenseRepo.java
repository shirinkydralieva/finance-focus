package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Expense;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

}
