package com.indiatour.etour.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.indiatour.etour.dto.request.LoginRequest;
import com.indiatour.etour.dto.request.SignupRequest;
import com.indiatour.etour.dto.response.JwtResponse;
import com.indiatour.etour.dto.response.MessageResponse;
import com.indiatour.etour.entity.ERole;
import com.indiatour.etour.entity.Role;
import com.indiatour.etour.entity.User;
import com.indiatour.etour.repository.RoleRepository;
import com.indiatour.etour.repository.UserRepository;
import com.indiatour.etour.security.JwtUtils;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.indiatour.etour.security.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public MessageResponse registerUser(SignupRequest signupRequest) {

        // Check email (also doubles as the internal username)
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return new MessageResponse("Error: Email already exists!");
        }

        // Create User
        User user = new User();

        // Internally we key everything off email — this keeps the
        // existing username-based security plumbing working unchanged.
        user.setUsername(signupRequest.getEmail());
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setMobile(signupRequest.getMobile());

        // Encrypt Password
        user.setPassword(
                encoder.encode(signupRequest.getPassword())
        );

        // Public signup form always creates a CUSTOMER account
        Role customerRole = roleRepository
                .findByName(ERole.ROLE_CUSTOMER)
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(customerRole);
        user.setRoles(roles);

        userRepository.save(user);

        return new MessageResponse("User Registered Successfully!");

    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails =
                (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

}