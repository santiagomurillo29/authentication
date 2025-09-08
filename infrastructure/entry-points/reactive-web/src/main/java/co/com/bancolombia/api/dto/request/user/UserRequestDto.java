package co.com.bancolombia.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "UserRequest", description = "Model represent a user on database")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @Schema(description = "User name", example = "string")
    @NotBlank(message = "The name must not be empty and null")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "The name must not contain special characters")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    private String nameUser;

    @Schema(description = "User last name", example = "string")
    @NotBlank(message = "The last name must not be empty and null")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "The name must not contain special characters")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    private String lastNameUser;

    @Schema(description = "User card", example = "1000000077")
    @NotBlank(message = "The document must not be empty or null")
    @Pattern(regexp = "^[0-9]{6,12}$", message = "The document must contain between 6 and 12 digits")
    private String documentUser;

    @Schema(description = "birthdate of the user", example = "2004-01-01")
    @NotNull(message = "The birthday must not be null")
    @Past(message = "The birthday must be a past date")
    private LocalDate birthdayUser;

    @Schema(description = "User address", example = "Cra 78")
    @NotBlank(message = "The address must not be empty or null")
    @Size(min = 5, max = 100, message = "The address must be between 5 and 100 characters")
    private String addressUser;

    @Schema(description = "User phone", example = "3002002030")
    @NotBlank(message = "The phone must not be empty or null")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "The phone number must contain between 7 and 15 digits")
    private String phoneUser;

    @Schema(description = "email of the user", example = "user@gmail.com")
    @NotBlank(message = "The email must not be empty or null")
    @Email(message = "The email must be a valid email address")
    private String emailUser;

    @Schema(description = "User base salary", example = "3000000")
    @NotNull(message = "The base salary must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "The base salary must be greater than 0")
    @DecimalMax(value = "15000000.0", inclusive = true, message = "The base salary must not exceed 15000000")
    @Digits(integer = 10, fraction = 2, message = "The base salary must have up to 10 digits and 2 decimals")
    private BigDecimal baseSalaryUser;

    @Schema(description = "Role id", example = "1")
    @NotNull(message = "The role must not be null")
    @DecimalMin(value = "1", message = "The role must be greater than 1")
    @DecimalMax(value = "3", message = "The role must not exceed 3")
    private Long idRole;
}
