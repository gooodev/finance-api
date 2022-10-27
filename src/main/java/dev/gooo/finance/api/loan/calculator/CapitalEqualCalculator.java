package dev.gooo.finance.api.loan.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import dev.gooo.finance.api.loan.model.LoanCalcParameter;
import dev.gooo.finance.api.loan.model.LoanCalcResult;
import dev.gooo.finance.api.loan.model.MonthlyCalcResult;

public final class CapitalEqualCalculator implements LoanCalculator {
    @Override
    public LoanCalcResult compute(LoanCalcParameter parameter) {
        BigDecimal termMonth = parameter.getTermYear().multiply(LoanCalculator.MONTH_OF_YEAR);
        BigDecimal monthlyInterest = parameter.getAnnualInterest().divide(LoanCalculator.MONTH_OF_YEAR);
        BigDecimal capitalPayment = parameter.getAmount().divide(termMonth, 0, RoundingMode.HALF_EVEN);

        BigDecimal totalPayment = BigDecimal.ZERO;
        BigDecimal remainCapital = parameter.getAmount();

        MonthlyCalcResult[] monthlyResults = new MonthlyCalcResult[termMonth.intValue()];
        for (int i = 0; i < termMonth.intValue(); i++) {
            BigDecimal interestPayment = remainCapital.multiply(monthlyInterest)
                    .setScale(0, RoundingMode.HALF_EVEN);
            totalPayment = totalPayment.add(interestPayment.add(capitalPayment));
            remainCapital = remainCapital.subtract(capitalPayment);
            BigDecimal monthlyPayment = interestPayment.add(capitalPayment);
            if (i < termMonth.intValue() - 1) {
                monthlyResults[i] = new MonthlyCalcResult(i + 1, remainCapital, interestPayment,
                        capitalPayment, monthlyPayment);
            } else {

                monthlyResults[i] = new MonthlyCalcResult(i + 1, BigDecimal.ZERO, interestPayment,
                        capitalPayment.add(remainCapital), monthlyPayment);
            }

        }
        totalPayment = totalPayment.add(remainCapital);
        LoanCalcResult result = new LoanCalcResult(totalPayment, monthlyResults);
        return result;
    }
}
