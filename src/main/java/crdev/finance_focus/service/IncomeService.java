package crdev.finance_focus.service;

import crdev.finance_focus.dto.IncomeDto;

import java.util.List;

public interface IncomeService {

    List<IncomeDto> getAll();

    void save(IncomeDto model);

    void deleteById(Long id);
    IncomeDto getById(Long id);

    void updateAmount(Long id, Double newAmount);
    void updateCategory(Long id, String newCategory);


}


