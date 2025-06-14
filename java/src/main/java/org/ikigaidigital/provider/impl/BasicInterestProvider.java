package org.ikigaidigital.provider.impl;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.provider.InterestProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BasicInterestProvider implements InterestProvider {

    private static final BigDecimal RATE = new BigDecimal("0.01");
    private static final int NO_INTEREST_DAYS = 30;
    private static final int NUM_OF_MONTHS_IN_A_YEAR = 12;

    @Override
    public BigDecimal computeInterest(TimeDeposit td) {
        int days = td.getDays();
        BigDecimal interest = BigDecimal.ZERO;
        if (days > NO_INTEREST_DAYS) {
            interest = td.getBalance().multiply(RATE)
                    .divide(BigDecimal.valueOf(NUM_OF_MONTHS_IN_A_YEAR), 10,                                  // scale: number of decimal places to keep
                            RoundingMode.DOWN);
        }
        return interest.setScale(2, RoundingMode.HALF_UP);
    }
}
