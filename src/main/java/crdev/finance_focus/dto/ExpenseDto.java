package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class ExpenseDto {
    private Long Id;
    private Double amount;
    private Long categoryId;
    private String description;
    private Date date;
    private Date deletedDate;
    private Long accountId;
}
