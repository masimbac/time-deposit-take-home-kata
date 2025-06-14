package org.ikigaidigital.service.impl;

import org.ikigaidigital.service.TimeDepositCalculator;
import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.repository.TimeDepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimeDepositServiceImplTest {

    @Mock
    private TimeDepositRepository repository;

    @Mock
    private TimeDepositCalculator calculator;

    @InjectMocks
    private TimeDepositServiceImpl service;

    @Captor
    private ArgumentCaptor<List<TimeDeposit>> listCaptor;

    private TimeDeposit td1;
    private TimeDeposit td2;

    @BeforeEach
    void setUp() {
        td1 = new TimeDeposit();
        td1.setId(1);
        td1.setPlanType("basic");
        td1.setDays(60);
        td1.setBalance(new BigDecimal("1000.00"));

        td2 = new TimeDeposit();
        td2.setId(2);
        td2.setPlanType("student");
        td2.setDays(90);
        td2.setBalance(new BigDecimal("2000.00"));
    }

    @Test
    void getAllDeposits_returnsAllFromRepository() {
        List<TimeDeposit> deposits = List.of(td1, td2);
        when(repository.findAll()).thenReturn(deposits);

        List<TimeDeposit> result = service.getAllDeposits();

        assertThat(result).isSameAs(deposits);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository, calculator);
    }

    @Test
    void recalcAllBalances_appliesCalculatorAndSaves() {
        List<TimeDeposit> deposits = List.of(td1, td2);
        when(repository.findAll()).thenReturn(deposits);

        service.recalculateAllBalances();

        // verify that calculator.updateBalance was called with the list
        verify(calculator, times(1)).updateBalance(deposits);

        // verify that repository.saveAll was called with the same list
        verify(repository, times(1)).saveAll(listCaptor.capture());
        List<TimeDeposit> saved = listCaptor.getValue();
        assertThat(saved).containsExactly(td1, td2);

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository, calculator);
    }

    @Test
    void recalcAllBalances_emptyListStillProcessedAndSaved() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        service.recalculateAllBalances();

        // even for empty list, calculator should be invoked
        verify(calculator, times(1)).updateBalance(Collections.emptyList());
        verify(repository, times(1)).saveAll(listCaptor.capture());
        assertThat(listCaptor.getValue()).isEmpty();

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository, calculator);
    }
}

