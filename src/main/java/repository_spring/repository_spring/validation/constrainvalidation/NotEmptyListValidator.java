package repository_spring.repository_spring.validation.constrainvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.List;

import repository_spring.repository_spring.validation.NotEmptyList;


public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

  @Override
  public boolean isValid(List list, ConstraintValidatorContext context) {
    return list != null && !list.isEmpty();
  }

  @Override
  public void initialize(NotEmptyList constraintAnnotation) { }
}
