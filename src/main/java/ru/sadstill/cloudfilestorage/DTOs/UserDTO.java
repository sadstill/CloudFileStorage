package ru.sadstill.cloudfilestorage.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 5, message = "Password must be longer than 5 symbols!")
    private String password;

}
