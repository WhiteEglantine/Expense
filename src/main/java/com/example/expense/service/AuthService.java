package com.example.expense.service;

import com.example.expense.exception.RoleNotFoundException;
import com.example.expense.exception.UserAlreadyExistsException;
import com.example.expense.constant.UserRole;
import com.example.expense.dto.LoginRegisterDto;
import com.example.expense.dto.LoginResponse;
import com.example.expense.entity.Role;
import com.example.expense.entity.User;
import com.example.expense.security.CustomUserDetails;
import com.example.expense.security.JwtUtils;
import com.example.expense.repository.RoleRepository;
import com.example.expense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public void registerUser(LoginRegisterDto loginRegisterDto) {
        Optional<User> userOptional = userRepository.findByUsername(loginRegisterDto.getUsername());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(loginRegisterDto.getUsername(),
                passwordEncoder.encode(loginRegisterDto.getPassword()));

        // Adding a user with ADMIN role is not allowed and all users will be created by USER role
        Role role = roleRepository.findByName(UserRole.USER).orElseThrow(RoleNotFoundException::new);
        user.setRole(role);
        userRepository.save(user);
    }

    public LoginResponse loginUser(LoginRegisterDto loginRegisterDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRegisterDto.getUsername(), loginRegisterDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtUtils.generateJwtToken(authentication);

        Set<String> privileges = new HashSet<>();
        List<String> strRoles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        strRoles.forEach(strRole -> {
            Role role = roleRepository.findByName(UserRole.valueOf(strRole)).orElseThrow(RoleNotFoundException::new);
            privileges.addAll(role.getPrivileges());
        });

        return new LoginResponse(token, jwtUtils.getJwtExpirationMs(), privileges);
    }

}
