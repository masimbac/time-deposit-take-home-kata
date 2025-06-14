package org.ikigaidigital.provider.impl;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.provider.InterestProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PremiumInterestProviderTest {

    private final InterestProvider provider = new PremiumInterestProvider();

    @ParameterizedTest(name = "days={0}, balance={1} → expected interest={2}")
    @CsvSource({
            "30,    1000,  0.00",
            "45,    1000,  0.00",
            "46,    1000,  4.17",
            "75,    1000,  4.17",
            "100,   1000,  4.17"
    })
    void computeInterest_variousScenarios(int days, String balance, String expected) {
        TimeDeposit td = new TimeDeposit(1, "Premium", new BigDecimal(balance),  days, null);
        BigDecimal interest = provider.computeInterest(td);
        assertEquals(new BigDecimal(expected), interest);
    }
}
