package com.ocab.medilabogateway.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Security configuration class.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Create in-memory users for authentication.
     *
     * @param passwordEncoder password encoder
     * @return user details service
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // Create organization user
        UserDetails orga = User.withUsername("orga")
                .password(passwordEncoder().encode("orga"))
                .roles("ORGA")
                .build();

        // Create admin user
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(orga, admin);
    }

    /**
     * Configure HTTP security rules.
     *
     * @param http HTTP security configuration
     * @return security filter chain
     * @throws Exception if security configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF protection
                .csrf(AbstractHttpConfigurer::disable)

                // Enable CORS configuration
                .cors(cors -> {
                })

                // Configure endpoint authorization
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(
                                "/login",
                                "/error",
                                "/signUp",
                                "/signUp/**",
                                "/patient/add"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // Disable HTTP Basic authentication
                .httpBasic(AbstractHttpConfigurer::disable)

                // Disable default form login
                .formLogin(AbstractHttpConfigurer::disable)

                // Configure logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    /**
     * Configure CORS mappings.
     *
     * @return web MVC configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            /**
             * Add CORS configuration for all routes.
             *
             * @param registry CORS registry
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost",
                                "http://localhost:80",
                                "http://127.0.0.1",
                                "http://127.0.0.1:80"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Content-Type", "Authorization")
                        .allowCredentials(true);
            }
        };
    }

    /**
     * Create password encoder bean.
     *
     * @return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        // BCrypt encoder with strength level 12
        return new BCryptPasswordEncoder(12);
    }

    /**
     * Create authentication manager bean.
     *
     * @param configuration authentication configuration
     * @return authentication manager
     * @throws Exception if authentication manager creation fails
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
}