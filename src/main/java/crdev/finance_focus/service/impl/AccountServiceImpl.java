package crdev.finance_focus.service.impl;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.entity.Account;
import crdev.finance_focus.repo.AccountRepo;
import crdev.finance_focus.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final UserServiceImpl userService;
    public Long save(AccountDto accountModel, Long userId) {
        Account account = new Account();
        account.setName(accountModel.getName());
        account.setType(accountModel.getType());
        account.setBalance(accountModel.getBalance());
        try {
            account.setUser(userService.findUserById(userId));
            accountRepo.save(account);
            return account.getId();
        } catch (NullPointerException e){
            return -1L; //Error
        }
    }

    public String create(AccountDto model, Long userId){
        if (save(model, userId) != -1L){
            return "Account created!";
        } else {
            return "User is not found!";
        }
    }

    public String info(AccountDto model, Long userId){
        try {
            Account account = accountRepo.findAccountByNameAndUserId(model.getName(), userId);
            return "- Account information -" +
                    "\nname: " + account.getName() +
                    "\ntype: " + account.getType() +
                    "\nbalance: " + account.getBalance() +
                    "\nincomes: " + account.getIncomes() +
                    "\nexpenses: " + account.getExpenses();
        } catch (NullPointerException e){
            return "Account not found!";
        }
    }

    public String updateName(Long userId, String newName,AccountDto model){
        try{
            Account account = accountRepo.findAccountByNameAndUserId(model.getName(), userId);
            account.setName(newName);
            accountRepo.save(account);
            return "Name updated!" + "\nAccount's new name is: " + account.getName();
        } catch (NullPointerException e){
            return "Account is not found!";
        }
    }

    public String updateType(Long userId, String newType,AccountDto model){
        try{
            Account account = accountRepo.findAccountByNameAndUserId(model.getName(), userId);
            account.setType(newType);
            accountRepo.save(account);
            return "Name updated!" + "\nAccount's new type is: " + account.getType();
        } catch (NullPointerException e){
            return "Account is not found!";
        }
    }

    public String updateBalance(Long userId, Double newBalance,AccountDto model){
        try{
            Account account = accountRepo.findAccountByNameAndUserId(model.getName(), userId);
            account.setBalance(newBalance);
            accountRepo.save(account);
            return "Balance updated!" + "\nYour new balance is: " + account.getBalance();
        } catch (NullPointerException e){
            return "Account is not found!";
        }
    }

    public String delete(Long userId, AccountDto model){
        try {
            Account account = accountRepo.findAccountByNameAndUserId(model.getName(), userId);
            accountRepo.delete(account);
            return "Account deleted!";
        } catch (NullPointerException e){
            return "Account is not found!";
        }
    }

    @Override
    public void updateAccountBalanceByExpense(Long accountId, Long expenseId) {
        accountRepo.updateAccountBalanceByExpense(accountId, expenseId);
    }

    @Override
    public void updateAccountBalanceByIncome(Long accountId, Long incomeId) {
        accountRepo.updateAccountBalanceByIncome(accountId, incomeId);
    }

    @Override
    public Account getById(Long id) {
        return accountRepo.findById(id).get();
    }
}
