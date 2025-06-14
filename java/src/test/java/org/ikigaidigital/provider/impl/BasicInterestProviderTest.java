package org.ikigaidigital.provider.impl;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.provider.InterestProvider;
import org.ikigaidigital.provider.impl.BasicInterestProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicInterestProviderTest {

    private final InterestProvider provider = new BasicInterestProvider();

    @ParameterizedTest(name = "days={0}, balance={1} → expected interest={2}")
    @CsvSource({
            // days,    balance,   expectedInterest
            "0,        1000,      0.00",
            "30,       1000,      0.00",
            "31,       1000,      0.83",
            "60,       1000,      0.83",
            "45,       500,       0.42",
            "367,       500,      0.42"
    })
    void computeInterest_variousScenarios(int days, String balance, String expected) {
        TimeDeposit td = new TimeDeposit(1, "Basic", new BigDecimal(balance), days, null);
        BigDecimal interest = provider.computeInterest(td);
        assertEquals(new BigDecimal(expected), interest);
    }
}
