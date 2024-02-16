package crdev.finance_focus.service;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.entity.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> getAll(Long accountId);

    Expense save(ExpenseDto model);
    ExpenseDto create(ExpenseDto model);
    String deleteById(Long id);
    ExpenseDto getById(Long id);
    ExpenseDto update(Long id, ExpenseDto model);
    List<ExpenseDto> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate)
}



