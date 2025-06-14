package org.ikigaidigital;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timeDeposits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDeposit {
    @Id
    private int id;
    private String planType;
    private Double balance;
    private int days;
}
