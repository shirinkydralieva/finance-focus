package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> getAllByAccountIdAndDeletedDateIsNull(Long accountId);

    Optional<Income> findByIdAndDeletedDateIsNull(Long id);
}
