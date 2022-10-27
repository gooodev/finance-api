package dev.gooo.finance.api.common;

import java.beans.PropertyEditorSupport;

import com.google.common.base.CaseFormat;

public class EnumPropertyEditorSupport<T extends Enum<T>> extends PropertyEditorSupport {
    private final Class<T> enumClazz;

    public EnumPropertyEditorSupport(Class<T> enumClazz) {
        this.enumClazz = enumClazz;
    }

    @Override
    public void setAsText(String text) {
        String value = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, text);
        Enum<T> e = Enum.valueOf(this.enumClazz, value);
        setValue(e);
    }
}
