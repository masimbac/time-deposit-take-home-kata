package org.ikigaidigital.provider.impl;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.provider.InterestProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentInterestProviderTest {

    private final InterestProvider provider = new StudentInterestProvider();

    @ParameterizedTest(name = "days={0}, balance={1} → expected interest={2}")
    @CsvSource({
            "30,    1000,  0.00",
            "31,    1000,  2.50",
            "365,   1000,  2.50",
            "366,   1000,  0.00",
            "390,   1000,  0.00"
    })
    void computeInterest_variousScenarios(int days, String balance, String expected) {
        TimeDeposit td = new TimeDeposit(1, "Student", new BigDecimal(balance), days, null);
        BigDecimal interest = provider.computeInterest(td);
        assertEquals(new BigDecimal(expected), interest);
    }
}

