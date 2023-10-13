package ru.sadstill.cloudfilestorage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sadstill.cloudfilestorage.dto.UserDTO;
import ru.sadstill.cloudfilestorage.models.User;
import ru.sadstill.cloudfilestorage.repositories.RoleRepository;
import ru.sadstill.cloudfilestorage.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Transactional
    public void registerUser(UserDTO userDTO) {

        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();

        roleService.registerUserToRole(user, "ROLE_USER");

        userRepository.save(user);
    }

}
