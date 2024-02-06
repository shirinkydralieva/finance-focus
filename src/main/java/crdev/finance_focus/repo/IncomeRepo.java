package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income, Long> {
}
