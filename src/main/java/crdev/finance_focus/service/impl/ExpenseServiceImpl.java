package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Expense;
import crdev.finance_focus.repo.ExpenseRepo;
import crdev.finance_focus.service.AccountService;
import crdev.finance_focus.service.ExpenseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo repo;
    private final AccountService accountService;

    @Transactional
    @Override
    public void save(ExpenseDto model) {
        Expense expense = new Expense();
        expense.setAmount(model.getAmount());
        expense.setCategory(model.getCategory());
        expense.setDescription(model.getDescription());
        expense.setDate(new Date());
        expense.setAccount(accountService.getById(model.getAccountId()));
        repo.save(expense);
        accountService.updateAccountBalanceByExpense(model.getAccountId(), expense.getId());
    }

    @Override
    public List<ExpenseDto> getAll() {
        List<Expense> expenses = repo.findAll();
        List<ExpenseDto> models = new ArrayList<>();
        for (Expense expense : expenses) {
            ExpenseDto model = new ExpenseDto();
            model.setAmount(expense.getAmount());
            model.setCategory(expense.getCategory());
            model.setDescription(expense.getDescription());
            model.setDate(expense.getDate());
            models.add(model);
        }
        return models;
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Expense expense = repo.findById(id).orElse(null);
        if (expense != null) {
            expense.setDeletedDate(new Date());

            Account account = accountService.getById(expense.getAccount().getId());
            if (account.getExpenses().contains(expense)) {
                account.getExpenses().remove(expense);
            }
            Double newBalance = account.getBalance() + expense.getAmount();
            account.setBalance(newBalance);
        }
    }
    @Override
    public ExpenseDto getById(Long id) {
        Expense expense = repo.findById(id).get();
        var model = new ExpenseDto();
        model.setAmount(expense.getAmount());
        model.setCategory(expense.getCategory());
        model.setDescription(expense.getDescription());
        model.setDate(expense.getDate());
        return model;
    }
    @Transactional
    @Override
    public void updateAmount(Long id, Double newAmount) {
        Expense expense = repo.findById(id).orElse(null);
        if (expense != null) {
            Double oldAmount = expense.getAmount();
            expense.setAmount(newAmount);
            repo.save(expense);

            Account account = accountService.getById(expense.getAccount().getId());
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
        Expense expense = repo.findById(id).orElse(null);
        if (expense != null) {
            expense.setCategory(newCategory);
            repo.save(expense);
        }
    }
}


/*
    public void saveExpense(Expense expense) {
        accountService.updateAccountBalance(expense.getAccount().getId(), -expense.getAmount());
        expenseRepository.save(expense);
    }

    public void updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        accountService.updateAccountBalance(existingExpense.getAccount().getId(), existingExpense.getAmount());
        accountService.updateAccountBalance(expense.getAccount().getId(), -expense.getAmount());
        expense.setId(id);
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        accountService.updateAccountBalance(expense.getAccount().getId(), expense.getAmount());
        expenseRepository.deleteById(id);
    }*/


