package crdev.finance_focus.service;

import crdev.finance_focus.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> getAll();

    void save(ExpenseDto model);

    void deleteById(Long id);
    ExpenseDto getById(Long id);

    void updateAmount(Long id, Double newAmount);
    void updateCategory(Long id, String newCategory);
}



