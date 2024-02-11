package crdev.finance_focus.repo;

import crdev.finance_focus.entity.Account;
import crdev.finance_focus.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findAccountByNameAndUserId(String name, Long userId);
    @Modifying
    @Query(value = "update account a set balance = balance - (select amount from expense e join account a on e.account_id = a.id where e.id = ?2) where a.id = ?1", nativeQuery = true)
    void updateAccountBalanceByExpense(Long accountId, Long expenseId); //возвращает обновленный баланс, скажем для этого вам понадобится только id-шки account и expense
    @Modifying
    @Query(value = "update account a set balance = balance + (select amount from income i join account a  on i.account_id = a.id where i.id = ?2) where a.id = ?1", nativeQuery = true)
    void updateAccountBalanceByIncome(Long accountId, Long incomeId);//почти такой же метод для income

    Account findAccountById (Long id);
}
