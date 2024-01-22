//package com.annotations;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class SizeValidator implements ConstraintValidator<Size, String> {
//    private int minSize;
//    private int maxSize;
//    @Override
//    public void initialize(Size annotation) {
//        this.minSize = annotation.min();
//        this.maxSize = annotation.max();
//    }
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return value == null || (value.length() >= minSize && value.length() <= maxSize);
//    }
//}
