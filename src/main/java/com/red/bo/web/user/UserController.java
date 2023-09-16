package com.red.bo.web.user;

import com.red.bo.core.user.User;
import com.red.bo.core.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(name = "User Management System")
public class UserController {

    private final UserService userService;

   /* private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt).build();
    } */

    @GetMapping("/current")
    @Operation(description = "Get current user data")
    public ResponseEntity<User> getCurrentUser() {
        var user = userService.getAllUsers().get(0);
        return ResponseEntity.ok().body(user);
    }
}

