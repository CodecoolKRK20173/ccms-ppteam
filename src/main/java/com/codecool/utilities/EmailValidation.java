package com.codecool.utilities;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidation {
        public static boolean isValidEmail(String email) {
            EmailValidator validator = EmailValidator.getInstance();
            return validator.isValid(email);
        }
}
