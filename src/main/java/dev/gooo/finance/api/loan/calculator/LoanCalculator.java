package dev.gooo.finance.api.loan.calculator;

import java.math.BigDecimal;

import dev.gooo.finance.api.loan.model.LoanCalcParameter;
import dev.gooo.finance.api.loan.model.LoanCalcResult;

public interface LoanCalculator {
    static final BigDecimal MONTH_OF_YEAR = BigDecimal.valueOf(12);

    LoanCalcResult compute(LoanCalcParameter parameter);
}
