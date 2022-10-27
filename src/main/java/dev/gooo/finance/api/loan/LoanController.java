package dev.gooo.finance.api.loan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gooo.finance.api.common.EnumPropertyEditorSupport;
import dev.gooo.finance.api.loan.model.InterestTypeEnum;
import dev.gooo.finance.api.loan.model.LoanCalcParameter;
import dev.gooo.finance.api.loan.model.LoanCalcResult;
import dev.gooo.finance.api.loan.model.PaymentMethodEnum;

@RestController
@RequestMapping("loan")
public class LoanController {
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    /**
     * @param dataBinder
     */
    @InitBinder
    private void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(PaymentMethodEnum.class,
                new EnumPropertyEditorSupport<PaymentMethodEnum>(PaymentMethodEnum.class));
        dataBinder.registerCustomEditor(InterestTypeEnum.class,
                new EnumPropertyEditorSupport<InterestTypeEnum>(InterestTypeEnum.class));
    }

    @Autowired
    private LoanService loanService;

    /**
     * @param parameter
     * @param bindingResult
     * @return int
     */
    @GetMapping
    private LoanCalcResult getLoan(@Validated LoanCalcParameter parameter, BindingResult bindingResult) {
        logger.info("Start calculation: {}", parameter);
        return loanService.calcLoan(parameter);
    }
}
