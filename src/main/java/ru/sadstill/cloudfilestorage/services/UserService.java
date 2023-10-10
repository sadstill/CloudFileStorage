package ru.sadstill.cloudfilestorage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sadstill.cloudfilestorage.DTOs.UserDTO;
import ru.sadstill.cloudfilestorage.models.Role;
import ru.sadstill.cloudfilestorage.models.User;
import ru.sadstill.cloudfilestorage.repositories.RoleRepository;
import ru.sadstill.cloudfilestorage.repositories.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Transactional
    public void register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(Set.of())
                .build();

        userRepository.save(user);
    }

}
