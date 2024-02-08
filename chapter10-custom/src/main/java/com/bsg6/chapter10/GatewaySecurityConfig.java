package com.bsg6.chapter10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// tag::declaration[]
@Configuration
public class GatewaySecurityConfig {
// end::declaration[]

    // tag::userDetailsService[]
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder encoder = passwordEncoder();

        UserDetails adminUser = User
            .withUsername("admin")
            .password(encoder.encode("admin123"))
            .roles("ADMIN", "USER")
            .build();
        UserDetails regularUser = User
            .withUsername("user")
            .password(encoder.encode("user123"))
            .roles("USER")
            .build();
        manager.createUser(adminUser);
        manager.createUser(regularUser);

        return manager;
    }
    // end::userDetailsService[]

    // tag::filterChain[]
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login")
                    .permitAll()
                .requestMatchers("/dashboard")
                    .hasRole("USER")
                .requestMatchers("/admin_dashboard")
                    .hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
        )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .permitAll()
        );
        return http.build();
    }
    // end::filterChain[]

    // tag::passwordEncoder[]
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // end::passwordEncoder[]

}
