package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class IncomeDto {
    private Long Id;
    private String category;
    private Double amount;
    private String message;
    private Timestamp date;
    private Long accountId;
}
