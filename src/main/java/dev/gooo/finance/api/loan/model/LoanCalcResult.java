package dev.gooo.finance.api.loan.model;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class LoanCalcResult {
    private BigDecimal totalPayment;

    private MonthlyCalcResult[] monthlyResults;
}
