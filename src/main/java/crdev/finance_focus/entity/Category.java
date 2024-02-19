package crdev.finance_focus.entity;

import crdev.finance_focus.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

}
