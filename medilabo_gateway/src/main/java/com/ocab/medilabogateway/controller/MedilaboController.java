package com.ocab.medilabogateway.controller;

import com.ocab.medilabogateway.model.LoginModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller used to manage authentication.
 */
@RestController
public class MedilaboController {

    /**
     * Authentication manager used to authenticate users.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Repository used to store security context in session.
     */
    private final HttpSessionSecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    /**
     * Constructor for MedilaboController.
     *
     * @param authenticationManager authentication manager
     */
    public MedilaboController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Authenticate a user and create a session.
     *
     * @param login login credentials
     * @param httpRequest HTTP request
     * @param httpResponse HTTP response
     * @return authentication result
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginModel login,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {

        try {

            // Authenticate user with email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword()
                    )
            );

            // Create empty security context
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            // Set authenticated user
            context.setAuthentication(authentication);

            // Save security context in HTTP session
            securityContextRepository.saveContext(context, httpRequest, httpResponse);

            // Set current security context
            SecurityContextHolder.setContext(context);

            return ResponseEntity.ok("LOGIN_OK");

        } catch (BadCredentialsException e) {

            // Return unauthorized response if credentials are invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("BAD_CREDENTIALS");
        }
    }

    /**
     * Get information about the authenticated user.
     *
     * @return authentication status and username
     */
    @GetMapping("/auth/me")
    public ResponseEntity<?> me() {

        // Get current authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Check if user is authenticated
        boolean authenticated =
                auth != null &&
                        auth.isAuthenticated() &&
                        !(auth instanceof
                                org.springframework.security.authentication.AnonymousAuthenticationToken);

        if (!authenticated) {

            // Return unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("authenticated", false));
        }

        // Return authenticated user information
        return ResponseEntity.ok(Map.of(
                "authenticated", true,
                "username", auth.getName()
        ));
    }

    /**
     * Logout the current user.
     *
     * @param request HTTP request
     * @return logout result
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        // Clear security context
        SecurityContextHolder.clearContext();

        // Invalidate HTTP session
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("LOGOUT_OK");
    }
}