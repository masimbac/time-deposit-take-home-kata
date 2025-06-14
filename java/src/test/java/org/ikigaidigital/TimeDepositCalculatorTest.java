package org.ikigaidigital;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.service.TimeDepositCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeDepositCalculatorTest {

    private final TimeDepositCalculator calculator = new TimeDepositCalculator();

    @ParameterizedTest(name = "plan={0}, days={1}, initBalance={2} → expectedBalance={3}")
    @CsvSource({
            // planType, days,    initialBalance, expectedBalance
            "basic,      30,      1234567.00,     1234567.00",
            "student,    30,      5000.00,        5000.00",
            "premium,    45,      10000.00,       10000.00",
            // test cases with interest
            "basic,      60,      1000.00,        1000.83",
            "student,    60,      1000.00,        1002.50",
            "premium,    75,      1000.00,        1004.17"
    })
    void updateBalance_variousScenarios(String planType,
                                        int days,
                                        String initialBalance,
                                        String expectedBalance) {
        BigDecimal init = new BigDecimal(initialBalance);
        TimeDeposit td = new TimeDeposit(1, planType, init, days, null);

        calculator.updateBalance(List.of(td));

        assertEquals(new BigDecimal(expectedBalance), td.getBalance());
    }
}
