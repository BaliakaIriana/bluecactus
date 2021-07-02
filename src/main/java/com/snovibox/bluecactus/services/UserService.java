package com.snovibox.bluecactus.services;

import com.snovibox.bluecactus.configuration.utils.JwtTokenUtil;
import com.snovibox.bluecactus.dto.SuccessfulSigninResults;
import com.snovibox.bluecactus.entities.User;
import com.snovibox.bluecactus.exceptions.SignupException;
import com.snovibox.bluecactus.exceptions.UserAccountUnavailableException;
import com.snovibox.bluecactus.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public void signup(String username,
                       String password,
                       String confirmation,
                       Boolean locked,
                       Boolean enabled,
                       LocalDate expiration) throws SignupException {
        SignupException exception = new SignupException(new HashSet<>());
        if (!StringUtils.hasText(username)) {
            exception.getErrors().add(SignupException.SignupErrorType.EMAIL_INVALID);
        }
        if (!StringUtils.hasText(password) || !StringUtils.hasText(confirmation)) {
            exception.getErrors().add(SignupException.SignupErrorType.PASSWORD_CONFIRMATION_INVALID);
        } else {
            if (password.trim().compareTo(confirmation.trim()) != 0) {
                exception.getErrors().add(SignupException.SignupErrorType.UNMATCHING_PASSWORD_CONFIRMATION);
            }
        }
        if (userRepo.findByEmail(username).isPresent()) {
            exception.getErrors().add(SignupException.SignupErrorType.EMAIL_EXISTS);
        }
        if (!exception.getErrors().isEmpty()) {
            throw exception;
        }
        password = passwordEncoder.encode(password);
        User user = new User(null, username, password, expiration, locked, enabled);
        userRepo.save(user);
    }

    public SuccessfulSigninResults signin(String username, String password) throws BadCredentialsException, UserAccountUnavailableException {
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username, password
                        )
                );

        User user = (User) authenticate.getPrincipal();
        if (!user.isEnabled() || !user.isAccountNonLocked() || !user.isAccountNonExpired()) {
            throw new UserAccountUnavailableException();
        }
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        SuccessfulSigninResults results = new SuccessfulSigninResults();
        results.setUser(user);
        results.setAccessToken(accessToken);

        return results;
    }
}
