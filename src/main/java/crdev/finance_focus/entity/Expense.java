package crdev.finance_focus.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table
@Data
public class Expense {
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

    @Override
    public String toString() {
        return "{id: " + Id +
                ", category: " + category +
                ", amount: " + amount +
                ", message: " + message +
                ", date: " + date + "}";
    }
}
