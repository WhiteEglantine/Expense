package com.example.expense.service;

import com.example.expense.dto.ChangePasswordDto;
import com.example.expense.exception.RoleNotFoundException;
import com.example.expense.exception.UserNotFoundException;
import com.example.expense.constant.UserRole;
import com.example.expense.dto.UpdateUserDto;
import com.example.expense.entity.Role;
import com.example.expense.entity.User;
import com.example.expense.security.CustomUserDetails;
import com.example.expense.repository.RoleRepository;
import com.example.expense.repository.UserRepository;
import com.example.expense.util.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateUserByAdmin(UpdateUserDto updateUserDto) {
        User user = userRepository.findByUsername(updateUserDto.getUsername())
                .orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findByName(UserRole.valueOf(updateUserDto.getRole()))
                .orElseThrow(RoleNotFoundException::new);
        user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        user.setRole(role);
        userRepository.save(user);
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        CustomUserDetails customUserDetails = SecurityContextUtils.getCurrentUserDetails();
        User user = userRepository.findByUsername(customUserDetails.getUsername())
                .orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Incorrect password");
        }
    }
}
