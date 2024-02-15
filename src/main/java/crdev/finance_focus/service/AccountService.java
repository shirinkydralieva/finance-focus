package crdev.finance_focus.service;

import crdev.finance_focus.dto.AccountDto;
import crdev.finance_focus.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
     Account save(AccountDto model);
     AccountDto create(AccountDto model);
     AccountDto info(Long id);
     AccountDto update(Long id, AccountDto model);
     String delete(Long id);
     Optional<Account> findById(Long id);
     List<AccountDto> getAll(Long userId);
     AccountDto accountToDto(Account account);
     void updateBalanceByExpense(Long accountId, Double amount);
     void updateBalanceByIncome(Long accountId, Double amount);
}
