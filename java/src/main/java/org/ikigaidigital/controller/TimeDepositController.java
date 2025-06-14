package org.ikigaidigital.controller;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.service.TimeDepositService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-deposits")
@RequiredArgsConstructor
public class TimeDepositController {

    private final TimeDepositService timeDepositService;

    @GetMapping
    public ResponseEntity<List<TimeDeposit>> getAllTimeDeposits() {
        List<TimeDeposit> deposits = timeDepositService.getAllDeposits();
        return ResponseEntity.ok(deposits);
    }

    @PutMapping("/balance")
    public ResponseEntity<Void> recalculateBalances() {
        timeDepositService.recalculateAllBalances();
        return ResponseEntity.noContent().build();
    }
}

