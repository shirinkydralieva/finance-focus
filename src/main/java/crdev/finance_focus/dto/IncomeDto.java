package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class IncomeDto {
    private Long Id;
    private Long categoryId;
    private Double amount;
    private String description;
    private Date date;
    private Date deletedDate;
    private Long accountId;

}
