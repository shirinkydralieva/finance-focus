package crdev.finance_focus.service;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.entity.Account;

public interface AccountService {
     Long save(AccountDto accountModel, Long userId);
     String createAccount(AccountDto model, Long userId);
     String accountInfo(AccountDto model, Long userId);
     String updateBalance(Long userId, Double newBalance,AccountDto model);
     String updateName(Long userId, String newName,AccountDto model);
     String updateType(Long userId, String newType,AccountDto model);
     String deleteAccount(Long userId, AccountDto model);
}
