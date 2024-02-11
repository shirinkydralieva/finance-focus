package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class ExpenseDto {
    private Long Id;
    private String category;
    private Double amount;
    private String description;
    private Date date;
    private Long accountId;

    @Override
    public String toString() {
        return "id: " + Id +
                ", category: " + category +
                ", amount: " + amount +
                ", description: " + description +
                ", date: " + date;
    }
}
