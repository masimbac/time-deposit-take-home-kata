package org.ikigaidigital.provider;

import org.ikigaidigital.model.TimeDeposit;

import java.math.BigDecimal;

public interface InterestProvider {
    BigDecimal computeInterest(TimeDeposit deposit);
}
