package crdev.finance_focus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String category;
    private Double amount;
    private String message;
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
