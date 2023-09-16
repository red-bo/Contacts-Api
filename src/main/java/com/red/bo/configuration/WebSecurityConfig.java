package com.red.bo.configuration;/*package com.red.bo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                path("/"),
                                path("swagger-ui.html"),
                                path("/swagger-ui/**"),
                                path("/v3/api-docs/**"),
                                path("/swagger-resources/**"),
                                path("/webjars/**"),
                                path("h2-console**"))
                        .permitAll()
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
    private AntPathRequestMatcher path(String pattern){
        return new AntPathRequestMatcher(pattern);
    }
}

 */




/*
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(objectPostProcessor)
                .authenticationProvider(new AuthenticationProvider() {

                    @Override
                    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                        String username = authentication.getName();
                        String password = authentication.getCredentials().toString();

                        User user = userRepository.findByUsername(username);
                        if (user == null) {
                            throw new UsernameNotFoundException("User not found with username: " + username);
                        }

                        if (!passwordEncoder().matches(password, user.getPassword())) {
                            throw new BadCredentialsException("Wrong password");
                        }

                        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                                user.getAuthorities());
                    }

                    @Override
                    public boolean supports(Class<?> authentication) {
                        return authentication.equals(UsernamePasswordAuthenticationToken.class);
                    }
                })
                .build();
    }
}*/