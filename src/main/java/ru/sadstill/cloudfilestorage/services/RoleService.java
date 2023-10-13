package ru.sadstill.cloudfilestorage.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sadstill.cloudfilestorage.models.Role;
import ru.sadstill.cloudfilestorage.models.User;
import ru.sadstill.cloudfilestorage.repositories.RoleRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    /* Используем этот метод только в этом слое бизнес-логики для создания роли.
       Это позволит создавать в дальнейшем роли для нужных нам методов. */
    private Role createNewRole(String roleName) {

        Role role = new Role(roleName);

        log.info("Role with name {} successfully registered", role.getName());

        return roleRepository.save(role);

    }

    /* Method for creating a role, assignedRole should be - "ROLE_*" */
    protected void registerUserToRole(User user, String assignedRole) {

        Role role = roleRepository.findRoleByName(assignedRole)
                .orElseGet(() -> createNewRole(assignedRole));

        user.setRoles(Set.of(role));

    }

}
