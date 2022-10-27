package dev.gooo.finance.api.loan.model;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class MonthlyCalcResult {
    private int currentMonth;
    private BigDecimal remainCapital;
    private BigDecimal interestPayment;
    private BigDecimal capitalPayment;
    private BigDecimal monthlyPayment;
}
