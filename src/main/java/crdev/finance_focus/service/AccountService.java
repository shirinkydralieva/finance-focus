package crdev.finance_focus.service;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.entity.Account;

public interface AccountService {
     Long save(AccountDto accountModel, Long userId);
     String create(AccountDto model, Long userId);
     String info(AccountDto model, Long userId);
     String updateName(Long userId, String newName,AccountDto model);
     String updateType(Long userId, String newType,AccountDto model);
     String updateBalance(Long userId, Double newBalance,AccountDto model);
     String delete(Long userId, AccountDto model);

     void updateAccountBalanceByExpense(Long accountId, Long expenseId);
     void updateAccountBalanceByIncome(Long accountId, Long incomeId);

     Account getById(Long id);
}
