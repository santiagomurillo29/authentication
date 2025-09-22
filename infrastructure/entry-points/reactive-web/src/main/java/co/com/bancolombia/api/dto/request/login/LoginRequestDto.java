package co.com.bancolombia.api.dto.request.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @Schema(description = "email of the user", example = "user@gmail.com")
    @NotBlank(message = "The email must not be empty or null")
    @Email(message = "The email must be a valid email address")
    private String email;

    @Schema(description = "password of the user", example = "*******")
    @NotBlank(message = "The password must not be empty or null")
    private String password;
}
