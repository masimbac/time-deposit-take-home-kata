package org.ikigaidigital.service;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.provider.InterestProvider;
import org.ikigaidigital.provider.impl.BasicInterestProvider;
import org.ikigaidigital.provider.impl.PremiumInterestProvider;
import org.ikigaidigital.provider.impl.StudentInterestProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimeDepositCalculator {
    private static final Map<String, InterestProvider> INTEREST_PROVIDERS = new HashMap<>();
    static {
        INTEREST_PROVIDERS.put("basic", new BasicInterestProvider());
        INTEREST_PROVIDERS.put("student", new StudentInterestProvider());
        INTEREST_PROVIDERS.put("premium", new PremiumInterestProvider());
    }

    public void updateBalance(List<TimeDeposit> xs) {
        xs.forEach(x -> {
            InterestProvider provider = INTEREST_PROVIDERS.get(x.getPlanType().toLowerCase());
            BigDecimal interest = provider.computeInterest(x);
            x.setBalance(x.getBalance().add(interest));
        });
    }
}
