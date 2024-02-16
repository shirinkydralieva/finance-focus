package crdev.finance_focus.service;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.entity.Income;

import java.util.Date;
import java.util.List;

public interface IncomeService {

    List<IncomeDto> getAll(Long accountId);

    Income save(IncomeDto model);
    IncomeDto create(IncomeDto model);
    String deleteById(Long id);
    IncomeDto getById(Long id);
    IncomeDto update(Long id, IncomeDto model);
    List<IncomeDto> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate)

}


