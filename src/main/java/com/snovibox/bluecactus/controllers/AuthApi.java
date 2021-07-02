package com.snovibox.bluecactus.controllers;


import com.snovibox.bluecactus.configuration.utils.JwtTokenUtil;
import com.snovibox.bluecactus.dto.AuthRequest;
import com.snovibox.bluecactus.dto.SignupRequest;
import com.snovibox.bluecactus.dto.SuccessfulSigninResults;
import com.snovibox.bluecactus.dto.mappers.ToDtoMapper;
import com.snovibox.bluecactus.exceptions.SignupException;
import com.snovibox.bluecactus.exceptions.UserAccountUnavailableException;
import com.snovibox.bluecactus.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ToDtoMapper mapper;
    private final UserService userService;

    @PostMapping("signin")
    public ResponseEntity<Object> signin(@RequestBody AuthRequest request) {
        try {
            SuccessfulSigninResults signin = userService.signin(request.getUsername(), request.getPassword());
            String accessToken = signin.getAccessToken();
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            accessToken
                    )
                    .body(mapper.toUserDTO(signin.getUser()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials or not authorized");
        } catch (UserAccountUnavailableException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account expired or locked");
        }
    }

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest request) {
        try {
            userService.signup(request.getUsername(),
                    request.getPassword(),
                    request.getConfirmation(),false,true,null);
            return  ResponseEntity.ok(200);
        } catch (SignupException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getErrors());
        }
    }
}
