package crdev.finance_focus.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String category;
    private Double amount;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "deleted_date")
    private Date deletedDate;

    @PrePersist
    protected void onCreate() {
        if (date == null) {
            date = new Date(); // Если пользователь не указал дату вручную, то будет установлена текущая дата
        }
    }

    @Override
    public String toString() {
        return "{id: " + Id +
                ", category: " + category +
                ", amount: " + amount +
                ", description: " + description +
                ", date: " + date + "}";
    }

}
