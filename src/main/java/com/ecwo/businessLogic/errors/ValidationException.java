package com.ecwo.businessLogic.errors;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 11.04.12
 * Time: 18:08
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 */
@javax.ejb.ApplicationException(rollback = true)
public class ValidationException extends Exception {
    private Set<ConstraintViolation<?>> violations;

    public ValidationException(Set<ConstraintViolation<?>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<?>> getViolations() {
        return violations;
    }

    public void setViolations(Set<ConstraintViolation<?>> violations) {
        this.violations = violations;
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
