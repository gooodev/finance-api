package dev.gooo.finance.api.loan.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import dev.gooo.finance.api.loan.model.LoanCalcParameter;
import dev.gooo.finance.api.loan.model.LoanCalcResult;
import dev.gooo.finance.api.loan.model.MonthlyCalcResult;

public final class InterestEqualCalculator implements LoanCalculator {

    @Override
    public LoanCalcResult compute(LoanCalcParameter parameter) {
        BigDecimal termMonth = parameter.getTermYear().multiply(LoanCalculator.MONTH_OF_YEAR);
        BigDecimal monthlyInterest = parameter.getAnnualInterest().divide(LoanCalculator.MONTH_OF_YEAR);

        BigDecimal factor = (BigDecimal.ONE.add(monthlyInterest))
                .pow(termMonth.intValue(), MathContext.DECIMAL32);
        BigDecimal monthlyPayment = (parameter.getAmount().multiply(monthlyInterest).multiply(factor))
                .divide(factor.subtract(BigDecimal.ONE), 0, RoundingMode.HALF_EVEN);

        BigDecimal totalPayment = monthlyPayment.multiply(termMonth);
        BigDecimal remainCapital = parameter.getAmount();

        MonthlyCalcResult[] monthlyResults = new MonthlyCalcResult[termMonth.intValue()];
        for (int i = 0; i < termMonth.intValue(); i++) {
            BigDecimal interestPayment = remainCapital.multiply(monthlyInterest).setScale(0,
                    RoundingMode.HALF_EVEN);
            BigDecimal capitalPayment = monthlyPayment.subtract(interestPayment);
            remainCapital = remainCapital.subtract(capitalPayment);
            if (i < termMonth.intValue() - 1) {
                monthlyResults[i] = new MonthlyCalcResult(i + 1, remainCapital, interestPayment, capitalPayment,
                        monthlyPayment);
            } else {
                monthlyResults[i] = new MonthlyCalcResult(i + 1, BigDecimal.ZERO, interestPayment,
                        capitalPayment.add(remainCapital),
                        monthlyPayment);
            }
        }
        LoanCalcResult result = new LoanCalcResult(totalPayment, monthlyResults);
        return result;
    }
}
