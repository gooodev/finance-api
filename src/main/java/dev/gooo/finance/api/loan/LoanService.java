package dev.gooo.finance.api.loan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.gooo.finance.api.loan.calculator.LoanCalculator;
import dev.gooo.finance.api.loan.model.LoanCalcParameter;
import dev.gooo.finance.api.loan.model.LoanCalcResult;

@Service
public class LoanService {
    Logger logger = LoggerFactory.getLogger(LoanService.class);

    public LoanCalcResult calcLoan(LoanCalcParameter parameter) {
        LoanCalculator calculator = parameter.getPaymentMethod().getCalculator();
        return calculator.compute(parameter);
    }
}
