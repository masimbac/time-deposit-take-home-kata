package org.ikigaidigital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timeDeposits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String planType;
    private BigDecimal balance;
    private int days;
    @OneToMany(
            mappedBy = "timeDeposit",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Withdrawal> withdrawals = new ArrayList<>();
}
