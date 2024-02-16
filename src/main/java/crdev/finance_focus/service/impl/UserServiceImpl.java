package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.IncomeDto;
import crdev.finance_focus.dto.UserDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Expense;
import crdev.finance_focus.entity.Income;
import crdev.finance_focus.entity.User;
import crdev.finance_focus.repo.UserRepo;
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
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public User save(UserDto model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        return userRepo.save(user);
    }
    @Override
    public UserDto register(UserDto model) {
        User user = userRepo.findByUsernameAndDeletedDateIsNull(model.getUsername()).orElse(null);
        if (user == null) {
            user = save(model);
            var response = new UserDto();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setPassword(user.getPassword());
            return response;
        } else {
            throw new IllegalArgumentException("Username has already been used!");
        }
    }
    @Override
    public UserDto logIn (UserDto model){
        User user = userRepo.findUserByUsernameAndPasswordAndDeletedDateIsNull(model.getUsername(), model.getPassword()).orElse(null);
            if (user != null){
                return userToDto(user);
            } else {
                throw new EntityNotFoundException("User not found");
            }
    }
    @Override
    public UserDto userToDto(User user){
        var response = new UserDto();
        List<Account> accounts = user.getAccounts();
        List<AccountDto> accountModels = new ArrayList<>();
        for (Account account : accounts) {
            var accountModel = new AccountDto();
            List<Expense> expenses = account.getExpenses();
            List<ExpenseDto> expensesModel = new ArrayList<>();
            for (Expense expense : expenses) {
                ExpenseDto expenseModel = new ExpenseDto();
                expenseModel.setId(expense.getId());
                expenseModel.setAmount(expense.getAmount());
                expenseModel.setCategoryId(expense.getCategory().getId());
                expenseModel.setDate(expense.getDate());
                expenseModel.setDescription(expense.getDescription());
                expenseModel.setAccountId(expense.getAccount().getId());
                expensesModel.add(expenseModel);
            }
            List<Income> incomes = account.getIncomes();
            List<IncomeDto> incomesModel = new ArrayList<>();
            for (Income income : incomes) {
                IncomeDto incomeModel = new IncomeDto();
                incomeModel.setId(income.getId());
                incomeModel.setAmount(income.getAmount());
                incomeModel.setCategoryId(income.getCategory().getId());
                incomeModel.setDate(income.getDate());
                incomeModel.setDescription(income.getDescription());
                incomeModel.setAccountId(income.getAccount().getId());
                incomesModel.add(incomeModel);
            }
            accountModel.setId(account.getId());
            accountModel.setName(account.getName());
            accountModel.setBalance(account.getBalance());
            accountModel.setExpenses(expensesModel);
            accountModel.setIncomes(incomesModel);
            accountModels.add(accountModel);
            accountModel.setUserId(account.getUser().getId());
        }
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setAccounts(accountModels);
        return response;
    }


    @Override
    public UserDto update (Long id, UserDto model){
        User user = userRepo.findByIdAndDeletedDateIsNull(id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        if (model.getUsername() != null){
                user.setUsername(model.getUsername());
        }
        if (model.getPassword() != null){
                user.setPassword(model.getPassword());
        }
        userRepo.save(user);
        var response = userToDto(user);
        return response;
    }

    @Override
    public String delete(Long id){
        User user = userRepo.findByIdAndDeletedDateIsNull(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setDeletedDate(new Date());
        for (Account account : user.getAccounts()) {
            account.setDeletedDate(new Date());
            for (Expense expense : account.getExpenses()) {
                expense.setDeletedDate(new Date());
            }
            for (Income income : account.getIncomes()) {
                income.setDeletedDate(new Date());
            }
        }
        userRepo.save(user);
        return "Deleted!";
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findByIdAndDeletedDateIsNull(id);
    }

}
