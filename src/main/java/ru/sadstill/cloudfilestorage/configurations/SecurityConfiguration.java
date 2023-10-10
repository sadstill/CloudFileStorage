package ru.sadstill.cloudfilestorage.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.sadstill.cloudfilestorage.services.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/registration", "/login", "/static/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/process-login")
                        .defaultSuccessUrl("/files")
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

}
