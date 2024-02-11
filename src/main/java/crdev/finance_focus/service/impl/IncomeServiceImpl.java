package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Income;
import crdev.finance_focus.repo.IncomeRepo;
import crdev.finance_focus.service.AccountService;
import crdev.finance_focus.service.IncomeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepo repo;
    private final AccountService accountService;

    @Override
    public List<IncomeDto> getAll() {
        List<Income> incomes = repo.findAll();
        List<IncomeDto> models = new ArrayList<>();
        for (Income income : incomes) {
            IncomeDto model = new IncomeDto();
            model.setAmount(income.getAmount());
            model.setCategory(income.getCategory());
            model.setDescription(income.getDescription());
            model.setDate(income.getDate());
            model.setAccountId(income.getAccount().getId());

            models.add(model);
        }
        return models;
    }
    @Transactional
    @Override
    public void save(IncomeDto model) {
        Income income = new Income();
        income.setAmount(model.getAmount());
        income.setCategory(model.getCategory());
        income.setDescription(model.getDescription());
        income.setDate(new Date());
        income.setAccount(accountService.getById(model.getAccountId()));
        repo.save(income);
        accountService.updateAccountBalanceByIncome(model.getAccountId(), income.getId());
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Income income = repo.findById(id).orElse(null);
        if (income != null) {
            income.setDeletedDate(new Date());

            Account account = accountService.getById(income.getAccount().getId());
            if (account.getIncomes().contains(income)) {
                account.getIncomes().remove(income);
            }
            Double newBalance = account.getBalance() - income.getAmount();
            account.setBalance(newBalance);
        }
    }
    @Override
    public IncomeDto getById(Long id) {
        Income income = repo.findById(id).get();
        var model = new IncomeDto();
        model.setAmount(income.getAmount());
        model.setCategory(income.getCategory());
        model.setDescription(income.getDescription());
        model.setDate(income.getDate());
        return model;
//       return repo.findById(id).orElseThrow(() -> new RuntimeException("Income not found with id: " + id));
    }

    @Transactional
    @Override
    public void updateAmount(Long id, Double newAmount) {
        Income income = repo.findById(id).orElse(null);
        if (income != null) {
            Double oldAmount = income.getAmount();
            income.setAmount(newAmount);
            repo.save(income);

            Account account = accountService.getById(income.getAccount().getId());
            if (account != null) {
                Double difference = newAmount - oldAmount;
                Double newBalance = account.getBalance() - difference;
                account.setBalance(newBalance);
            }
        }
    }

    @Transactional
    @Override
    public void updateCategory(Long id, String newCategory) {
        Income income = repo.findById(id).orElse(null);
        if (income != null) {
            income.setCategory(newCategory);
            repo.save(income);
        }
    }
}



