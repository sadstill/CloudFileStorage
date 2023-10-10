package ru.sadstill.cloudfilestorage.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sadstill.cloudfilestorage.models.Role;
import ru.sadstill.cloudfilestorage.models.User;
import ru.sadstill.cloudfilestorage.repositories.UserRepository;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: ' + username + ' not found"));

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                getAuthorities(user.getRoles())
        );
    }

    private static Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

}
