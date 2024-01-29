package com.quarkus.todo.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.EnumUtils;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class EnumValidator implements ConstraintValidator<IsValidEnum, String> {
    private IsValidEnum annotation;

    @Override
    public void initialize(IsValidEnum annotation) {
        ConstraintValidator.super.initialize(annotation);
        this.annotation=annotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || isNotBlank(value) &&
                (annotation.ignoreCase() ?
                        EnumUtils.isValidEnumIgnoreCase(annotation.value(), value) :
                        EnumUtils.isValidEnum(annotation.value(), value));
    }
}
