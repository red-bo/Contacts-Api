package com.red.bo.web.user;

import com.red.bo.core.user.UserService;
import com.red.bo.security.refreshtoken.RefreshTokenService;
import com.red.bo.security.request.RefreshTokenRequest;
import com.red.bo.security.response.AuthenticationResponse;
import com.red.bo.security.response.RefreshTokenResponse;
import com.red.bo.web.user.mapper.RegisteredUser;
import com.red.bo.web.user.mapper.RequestedRegisterUser;
import com.red.bo.web.user.mapper.UserMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirements()
@AllArgsConstructor
@Tag(name = "User Management System")
public class UserController {

    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper map;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RequestedRegisterUser user) {
        return ResponseEntity.ok(userService.register(map.requestedRegisterUsertoUser(user))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody RegisteredUser request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenService.generateNewToken(request));
    }

    @PostMapping("/info")
    public Authentication getAuthentication(@RequestBody RegisteredUser request){
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
    }
}

