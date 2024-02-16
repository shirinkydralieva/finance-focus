package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Category;
import crdev.finance_focus.entity.Expense;
import crdev.finance_focus.entity.Income;
import crdev.finance_focus.repo.IncomeRepo;
import crdev.finance_focus.service.AccountService;
import crdev.finance_focus.service.CategoryService;
import crdev.finance_focus.service.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepo repo;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public Income save(IncomeDto model) {
        Income income = new Income();
        income.setAmount(model.getAmount());
        income.setCategory(categoryService.findById(model.getCategoryId()).get());
        income.setDescription(model.getDescription());
        income.setDate(model.getDate());
        income.setAccount(accountService.findById(model.getAccountId()).get());
        accountService.updateBalanceByIncome(model.getAccountId(), income.getAmount());
        return repo.save(income);
    }

    public IncomeDto incomeToDto(Income income) {
        var model = new IncomeDto();
        model.setId(income.getId());
        model.setAmount(income.getAmount());
        model.setCategoryId(income.getCategory().getId());
        model.setDescription(income.getDescription());
        model.setDate(income.getDate());
        model.setAccountId(income.getAccount().getId());
        return model;
    }
    @Transactional
    @Override
    public IncomeDto create(IncomeDto model) {
        Account account = accountService.findById(model.getAccountId()).orElse(null);
        Category category = categoryService.findById(model.getCategoryId()).orElse(null);
        if (account != null) {
            if (category != null) {
                Income income = save(model);
                return incomeToDto(income);
            } else {
                throw new EntityNotFoundException("Category not found");
            }
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

    @Override
    public List<IncomeDto> getAll(Long accountId) {
        List<Income> incomes = repo.getAllByAccountIdAndDeletedDateIsNull(accountId);
        List<IncomeDto> models = new ArrayList<>();
        for (Income income : incomes) {
            IncomeDto model = incomeToDto(income);
            models.add(model);
        }
        return models;
    }
    @Transactional
    @Override
    public String deleteById(Long id) {
        Income income = repo.findByIdAndDeletedDateIsNull(id).orElse(null);
        if (income != null) {
            income.setDeletedDate(new Date());
            Account account = accountService.findById(income.getAccount().getId()).orElse(null);
            if (account != null) {
                if (account.getIncomes().contains(income)) {
                    account.getIncomes().remove(income);
                }
                Double newBalance = account.getBalance() - income.getAmount();
                account.setBalance(newBalance);
                income.setAccount(null);
                repo.save(income);
                accountService.changeSave(account);
                return "Income deleted!";
            } else {
                throw new EntityNotFoundException("Account not found");
            }
        } else {
            throw new EntityNotFoundException("Income not found");
        }
    }
    @Override
    public IncomeDto getById(Long id) {
        Income income = repo.findByIdAndDeletedDateIsNull(id).orElse(null);
        if (income != null) {
            var model = incomeToDto(income);
            return model;
        } else {
            throw new EntityNotFoundException("Income not found");
        }
    }

    @Transactional
    @Override
    public IncomeDto update(Long id, IncomeDto model) {
        Income income = repo.findByIdAndDeletedDateIsNull(id).orElse(null);
        if (income != null) {
            Account account = accountService.findById(income.getAccount().getId()).orElse(null);
            if (account != null){
                if (account.getExpenses().contains(income)) {
                    account.getExpenses().remove(income);
                }
                Double newBalance = account.getBalance() - income.getAmount();
                if (model.getAmount() != null) {
                    income.setAmount(model.getAmount());
                    newBalance += model.getAmount();
                }
                if (model.getCategoryId() != null) {
                    if (categoryService.findById(model.getCategoryId()).isPresent()) {
                        income.setCategory(categoryService.findById(model.getCategoryId()).get());
                    } else {
                        throw new EntityNotFoundException("Category not found");
                    }
                }
                if (model.getDescription() != null) {
                    income.setDescription(model.getDescription());
                }
                if (model.getDate() != null) {
                    income.setDate(model.getDate());
                }
                account.setBalance(newBalance);
                account.getIncomes().add(income);
                repo.save(income);
                return incomeToDto(income);
            } else {
                throw new EntityNotFoundException("Account not found");
            }
        } else {
            throw new EntityNotFoundException("Income not found");
        }
    }

    @Override
    public List<IncomeDto> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate) {
        Account account = accountService.findById(accountId).orElse(null);
        List<IncomeDto> incomesModel = new ArrayList<>();
        if (account != null){
            List<Income> incomes = repo.findByAccountIdAndDateBetween(accountId, startDate, endDate);
            for (Income income : incomes){
                IncomeDto model = incomeToDto(income);
                incomesModel.add(model);
            }
        } else {
            throw new EntityNotFoundException("Account not found");
        }
        return incomesModel;
    }

}



