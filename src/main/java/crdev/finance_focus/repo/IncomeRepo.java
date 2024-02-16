package crdev.finance_focus.repo;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> getAllByAccountIdAndDeletedDateIsNull(Long accountId);

    Optional<Income> findByIdAndDeletedDateIsNull(Long id);
    List<Income> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate);
}
