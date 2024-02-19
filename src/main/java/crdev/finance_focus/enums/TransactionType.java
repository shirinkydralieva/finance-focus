package crdev.finance_focus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    EXPENSE("Расходы"),
    INCOME("Доходы"),
    ;

    String description;
}
