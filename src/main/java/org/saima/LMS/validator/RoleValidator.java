package org.saima.LMS.validator;



import org.saima.LMS.annotation.ValidRole;
import org.saima.LMS.constants.Role;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RoleValidator implements ConstraintValidator<ValidRole, Role> {
    @Override
    public boolean isValid(Role role, ConstraintValidatorContext context) {
        if (role == null) {
            return false;
        }
        try {
            Role.valueOf(role.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
