package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Category;
import crdev.finance_focus.entity.Expense;
import crdev.finance_focus.repo.ExpenseRepo;
import crdev.finance_focus.service.AccountService;
import crdev.finance_focus.service.CategoryService;
import crdev.finance_focus.service.ExpenseService;
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
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo repo;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public Expense save(ExpenseDto model) {
        Expense expense = new Expense();
        expense.setAmount(model.getAmount());
        expense.setCategory(categoryService.findById(model.getCategoryId()).get());
        expense.setDescription(model.getDescription());
        expense.setDate(model.getDate());
        expense.setAccount(accountService.findById(model.getAccountId()).get());
        accountService.updateBalanceByExpense(model.getAccountId(), expense.getAmount());
        return repo.save(expense);
    }

    public ExpenseDto expenseToDto(Expense expense) {
        var model = new ExpenseDto();
        model.setId(expense.getId());
        model.setAmount(expense.getAmount());
        model.setCategoryId(expense.getCategory().getId());
        model.setDescription(expense.getDescription());
        model.setDate(expense.getDate());
        model.setAccountId(expense.getAccount().getId());
        return model;
    }

    @Transactional
    public ExpenseDto create(ExpenseDto model) {
        Account account = accountService.findById(model.getAccountId()).orElse(null);
        Category category = categoryService.findById(model.getCategoryId()).orElse(null);
        if (account != null) {
            if (category != null) {
                Expense expense = save(model);
                return expenseToDto(expense);
            } else {
                throw new EntityNotFoundException("Category not found");
            }
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

    @Override
    public List<ExpenseDto> getAll(Long accountId) {
        Account account = accountService.findById(accountId).orElse(null);
        if (account != null) {
            List<Expense> expenses = repo.getAllByAccountIdAndDeletedDateIsNull(accountId);//получить все расходы по id счета, иначе мне кажется что все записи из бд будут возвращены
            List<ExpenseDto> models = new ArrayList<>();
            for (Expense expense : expenses) {
                ExpenseDto model = expenseToDto(expense);
                models.add(model);
            }
            return models;
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

    @Transactional
    @Override
    public String deleteById(Long id) {
        Expense expense = repo.findByIdAndDeletedDateIsNull(id).orElse(null);
        if (expense != null) {
            expense.setDeletedDate(new Date());
            Account account = accountService.findById(expense.getAccount().getId()).orElse(null);
            if (account != null){
                if (account.getExpenses().contains(expense)) {
                    account.getExpenses().remove(expense);
                }
                Double newBalance = account.getBalance() + expense.getAmount();
                account.setBalance(newBalance);
                return "Expense deleted!";
            } else {
                throw new EntityNotFoundException("Account not found");
            }
        } else {
            throw new EntityNotFoundException("Expense not found");
        }
    }

    @Override
    public ExpenseDto getById(Long id) {
        Expense expense = repo.findByIdAndDeletedDateIsNull(id).orElse(null);
        if (expense != null) {
            var model = expenseToDto(expense);
            return model;
        } else {
            throw new EntityNotFoundException("Expense not found");
        }
    }

    @Transactional
    @Override
    public ExpenseDto update(Long id, ExpenseDto model) {
        Expense expense = repo.findByIdAndDeletedDateIsNull(id).orElse(null);
        if (expense != null) {
            Account account = accountService.findById(expense.getAccount().getId()).orElse(null);
            if (account != null){
                if (account.getExpenses().contains(expense)) {
                    account.getExpenses().remove(expense);
                }
                Double newBalance = account.getBalance() + expense.getAmount();
                if (model.getAmount() != null) {
                    expense.setAmount(model.getAmount());
                    newBalance -= model.getAmount();
                }
                if (model.getCategoryId() != null) {
                    if (categoryService.findById(model.getCategoryId()).isPresent()) {
                        expense.setCategory(categoryService.findById(model.getCategoryId()).get());
                    } else{
                        throw new EntityNotFoundException("Category not found");
                    }
                }
                if (model.getDescription() != null) {
                    expense.setDescription(model.getDescription());
                }
                if (model.getDate() != null) {
                    expense.setDate(model.getDate());
                }
                account.setBalance(newBalance);
                account.getExpenses().add(expense);
                repo.save(expense);
                return expenseToDto(expense);
            } else {
                throw new EntityNotFoundException("Account not found");
            }
        } else {
            throw new EntityNotFoundException("Expense not found");
        }
    }
}



