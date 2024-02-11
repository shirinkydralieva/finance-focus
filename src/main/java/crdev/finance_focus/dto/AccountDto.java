package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long Id;
    private String name;
    private String type;
    private Double balance;
    private Long userId;
    private List<ExpenseDto> expenses;
    private List<IncomeDto> incomes;

    @Override
    public String toString() {
        return "account{" +
                "name: " + name +
                ", type: " + type +
                ", balance: " + balance +
                ", expenses:" + expenses +
                ", incomes: " + incomes +
                '}';
    }
}
