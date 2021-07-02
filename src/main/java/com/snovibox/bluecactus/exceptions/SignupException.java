package com.snovibox.bluecactus.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.Set;

@Data
@Builder
public class SignupException extends Exception{
    @Singular
    private Set<SignupErrorType> errors;

    public SignupException(Set<SignupErrorType> errors) {
        this.errors = errors;
    }

    public enum SignupErrorType {
        EMAIL_INVALID,
        EMAIL_EXISTS,
        PASSWORD_CONFIRMATION_INVALID,
        UNMATCHING_PASSWORD_CONFIRMATION,
    }
}
