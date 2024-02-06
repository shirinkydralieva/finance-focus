package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
public class ExpenseDto {
    private Long Id;
    private String category;
    private Double amount;
    private String message;
    private Timestamp date;
    private Long accountId;

    @Override
    public String toString() {
        return "id: " + Id +
                ", category: " + category +
                ", amount: " + amount +
                ", message: " + message +
                ", date: " + date;
    }
}
