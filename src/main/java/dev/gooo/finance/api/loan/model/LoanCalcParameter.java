package dev.gooo.finance.api.loan.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class LoanCalcParameter {

    @NotNull(message = "Missing parameters(s): amount")
    private BigDecimal amount;

    @NotNull(message = "Missing parameters(s): term-year")
    private BigDecimal termYear;

    @NotNull(message = "Missing parameter(s): annual-interest")
    private BigDecimal annualInterest;

    @NotNull(message = "Missing parameter(s): payment-method (capital-equal or interest-equal)")
    private PaymentMethodEnum paymentMethod;

    // @NotNull(message = "Missing parameter(s): interest-type (capital-equal or
    // interest-equal)")
    // private InterestTypeEnum interestType;
}
