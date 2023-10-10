package ru.sadstill.cloudfilestorage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(max = 128)
    private String username;

    @Column(name = "password", nullable = false)
    @ToString.Exclude
    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    @ToString.Exclude
    private Set<Role> roles;

}
