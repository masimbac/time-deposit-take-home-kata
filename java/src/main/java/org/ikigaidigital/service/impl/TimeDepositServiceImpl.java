package org.ikigaidigital.service.impl;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ikigaidigital.model.TimeDeposit;
import org.springframework.stereotype.Service;
import org.ikigaidigital.service.TimeDepositCalculator;
import org.ikigaidigital.repository.TimeDepositRepository;
import org.ikigaidigital.service.TimeDepositService;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeDepositServiceImpl implements TimeDepositService {

    private final TimeDepositRepository repository;
    private final TimeDepositCalculator calculator;

    @Override
    public List<TimeDeposit> getAllDeposits() {
        return repository.findAll();
    }

    @Override
    public void recalculateAllBalances() {
        List<TimeDeposit> all = repository.findAll();
        calculator.updateBalance(all);
        repository.saveAll(all);
    }
}

