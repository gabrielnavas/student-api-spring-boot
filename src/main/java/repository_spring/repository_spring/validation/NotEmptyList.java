package repository_spring.repository_spring.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import repository_spring.repository_spring.validation.constrainvalidation.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME) // ser verificar em tempo de execução
@Target(ElementType.FIELD) // onde sera usado essa anotation, num campo 
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
  
  // message default on trigger error
  String message() default "list cannot be empty";

  // obrigatório para funcionar.
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default{};
}
