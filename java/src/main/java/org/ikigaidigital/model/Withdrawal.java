package org.ikigaidigital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "timeDepositId", nullable = false)
    private TimeDeposit timeDeposit;
    private Double amount;
    private Instant dateTime;
}
