package org.ikigaidigital.service;

import org.ikigaidigital.model.TimeDeposit;

import java.util.List;

public interface TimeDepositService {

    /**
     * Retrieve all time deposits.
     */
    List<TimeDeposit> getAllDeposits();

    /**
     * Recalculate and persist all balances by applying interest rules.
     */
    void recalculateAllBalances();
}

