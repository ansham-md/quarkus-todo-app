package com.quarkus.todo.annotations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.PARAMETER,ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
@Documented
public @interface IsValidEnum {

    boolean ignoreCase() default true;

    boolean required() default true;

    String message() default "Invalid enum value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum> value();
}
