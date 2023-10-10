package ru.sadstill.cloudfilestorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sadstill.cloudfilestorage.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
