package dev.gooo.finance.api.loan.model;

import dev.gooo.finance.api.loan.calculator.CapitalEqualCalculator;
import dev.gooo.finance.api.loan.calculator.InterestEqualCalculator;
import dev.gooo.finance.api.loan.calculator.LoanCalculator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Payment method for loan
 */
@AllArgsConstructor
@Getter
public enum PaymentMethodEnum {
    /**
     * capital-equal: Principal equal monthly payment(元金均等)
     */
    CAPITAL_EQUAL(new CapitalEqualCalculator()),
    /**
     * interest-equal: Level payment(元利均等)
     */
    INTEREST_EQUAL(new InterestEqualCalculator());

    private final LoanCalculator calculator;
}
