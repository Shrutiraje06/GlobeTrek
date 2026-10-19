package com.indiatour.etour.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.indiatour.etour.security.AuthEntryPointJwt;
import com.indiatour.etour.security.JwtAuthenticationFilter;
import com.indiatour.etour.security.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication Provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

    	http

        // Enable CORS (uses the CorsConfigurationSource bean above)
        .cors(Customizer.withDefaults())

        // Disable CSRF
        .csrf(csrf -> csrf.disable())

            // Exception Handler
            .exceptionHandling(exception ->
                    exception.authenticationEntryPoint(unauthorizedHandler))

            // JWT -> Stateless
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            // Authorization
            .authorizeHttpRequests(auth -> auth

                // ===========================
                // PUBLIC
                // ===========================
            		.requestMatchers("/auth/**")
            		.permitAll()

            		.requestMatchers("/ai/**")
            		.permitAll()
                // ===========================
                // ADMIN
                // ===========================

                // Dashboard
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                // Email Module
                .requestMatchers("/email/**")
                .hasRole("ADMIN")

                // Customers
                .requestMatchers("/customers/**")
                .hasRole("ADMIN")

                // Tour Packages
             // Tour Packages
                .requestMatchers(HttpMethod.POST, "/packages/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/packages/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/packages/**")
                .hasRole("ADMIN")
                // Sectors
                .requestMatchers(HttpMethod.POST,
                        "/sectors/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,
                        "/sectors/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE,
                        "/sectors/**")
                .hasRole("ADMIN")

                // Costs
                .requestMatchers(HttpMethod.POST,
                        "/costs/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,
                        "/costs/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE,
                        "/costs/**")
                .hasRole("ADMIN")

                // Itinerary
                .requestMatchers(HttpMethod.POST,
                        "/itineraries/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,
                        "/itineraries/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE,
                        "/itineraries/**")
                .hasRole("ADMIN")

                // ===========================
                // CUSTOMER
                // ===========================

                .requestMatchers(HttpMethod.POST, "/bookings/**")
                .hasRole("CUSTOMER")

                .requestMatchers(HttpMethod.GET, "/bookings/**")
                .hasAnyRole("ADMIN", "CUSTOMER")

                .requestMatchers(HttpMethod.PUT, "/bookings/**")
                .hasAnyRole("ADMIN", "CUSTOMER")

                .requestMatchers(HttpMethod.DELETE, "/bookings/**")
                .hasRole("ADMIN")

                .requestMatchers("/payments/**")
                .hasRole("CUSTOMER")

                .requestMatchers("/passengers/**")
                .hasRole("CUSTOMER")

                // ===========================
                // BOTH ADMIN & CUSTOMER
                // ===========================

                .requestMatchers(HttpMethod.GET, "/packages/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/sectors/**")
                .hasAnyRole("ADMIN", "CUSTOMER")

                .requestMatchers(HttpMethod.GET,
                        "/costs/**")
                .hasAnyRole("ADMIN", "CUSTOMER")

                .requestMatchers(HttpMethod.GET,
                        "/itineraries/**")
                .hasAnyRole("ADMIN", "CUSTOMER")

                // Any Other API
                .anyRequest().authenticated()

            )

            // Provider
            .authenticationProvider(authenticationProvider())

            // JWT Filter
            .addFilterBefore(jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}