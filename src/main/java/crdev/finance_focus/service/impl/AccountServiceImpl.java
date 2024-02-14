package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.*;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Expense;
import crdev.finance_focus.entity.Income;
import crdev.finance_focus.entity.User;
import crdev.finance_focus.repo.AccountRepo;
import crdev.finance_focus.repo.UserRepo;
import crdev.finance_focus.service.AccountService;
import crdev.finance_focus.service.ExpenseService;
import crdev.finance_focus.service.IncomeService;
import crdev.finance_focus.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final UserService userService;
    public Account save(AccountDto model) {
        Account account = new Account();
        account.setName(model.getName());
        account.setBalance(model.getBalance());
        account.setUser(userService.findById(model.getUserId()).get());
        return accountRepo.save(account);
    }

    public AccountDto accountToDto(Account account){
        var response = new AccountDto();
        List<Expense> expenses = account.getExpenses();
        List<ExpenseDto> expensesModel = new ArrayList<>();
        for (Expense expense : expenses) {
            ExpenseDto expenseModel = new ExpenseDto();
            expenseModel.setId(expense.getId());
            expenseModel.setAmount(expense.getAmount());
            expenseModel.setCategory(expense.getCategory());
            expenseModel.setDate(expense.getDate());
            expenseModel.setDescription(expense.getDescription());
            expensesModel.add(expenseModel);
        }
        List<Income> incomes = account.getIncomes();
        List<IncomeDto> incomesModel = new ArrayList<>();
        for (Income income : incomes) {
            IncomeDto incomeModel = new IncomeDto();
            incomeModel.setId(income.getId());
            incomeModel.setAmount(income.getAmount());
            incomeModel.setCategory(income.getCategory());
            incomeModel.setDate(income.getDate());
            incomeModel.setDescription(income.getDescription());
            incomesModel.add(incomeModel);
        }
        response.setName(account.getName());
        response.setBalance(account.getBalance());
        response.setExpenses(expensesModel);
        response.setIncomes(incomesModel);
        response.setUserId(account.getUser().getId());
        return response;
    }

    public AccountDto create(AccountDto model){
        User user = userService.findById(model.getUserId()).orElse(null);
        if (user != null){
            Account account = save(model);
            return accountToDto(account);
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public AccountDto info(Long id){
        Account account = findById(id).orElse(null);
        if (account != null){
            return accountToDto(account);
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }


    public List<AccountDto> getAll(Long userId){
        List<Account> accounts = accountRepo.getAllByUserIdAndAndDeletedDateIsNull(userId);
        if (accounts.isEmpty()){
            throw new EntityNotFoundException("User not found");
        } else {
            List<AccountDto> models = new ArrayList<>();
            for (Account account : accounts) {
                var model = accountToDto(account);
                models.add(model);
            }
            return models;
        }
    }

    public AccountDto update (Long id, AccountDto model){
        Account account = findById(id).orElse(null);
        if (account != null){
            if (model.getName() != null){
                account.setName(model.getName());
            }
            if (model.getBalance() != null){
                account.setBalance(model.getBalance());
            }
            accountRepo.save(account);
            return accountToDto(account);
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

    public String delete(Long id){
        Account account = findById(id).orElse(null);
        if (account != null){
            account.setDeletedDate(new Date());
            accountRepo.save(account);
            User user = userService.findById(account.getUser().getId()).orElse(null);
            if (user != null){
                if (user.getAccounts().contains(account)) {
                    user.getAccounts().remove(account);
                }
                return "Account deleted!";
            } else {
                throw new EntityNotFoundException("User not found");
            }
        }
        else {
            throw new EntityNotFoundException("Account not found");
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepo.findAccountByIdAndDeletedDateIsNull(id);
    }

    @Override
    public void updateAccountBalanceByExpense(Long accountId, Long expenseId) {
        accountRepo.updateAccountBalanceByExpense(accountId, expenseId);
    }

    @Override
    public void updateAccountBalanceByIncome(Long accountId, Long incomeId) {
        accountRepo.updateAccountBalanceByIncome(accountId, incomeId);
    }

}
