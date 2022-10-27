package dev.gooo.finance.api.common;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class BigDecimalControllerAdvisor {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(BigDecimal.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                BigDecimal bigDecimal = new BigDecimal(text);
                setValue(bigDecimal);
            }
        });
    }
}
