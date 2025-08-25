package co.com.bancolombia.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "UserResponse", description = "Model represent a user on database")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {
    @Schema(description = "Unique identifier of the user")
    private String idUser;

    @Schema(description = "User's first name", example = "string")
    private String nameUser;

    @Schema(description = "User's last name", example = "string")
    private String lastNameUser;

    @Schema(description = "User's document/card", example = "100200300")
    private String documentUser;

    @Schema(description = "Birthdate of the user", example = "1995-08-25")
    private LocalDate birthdayUser;

    @Schema(description = "User's address", example = "Cra 12 #34-56, Bogotá")
    private String addressUser;

    @Schema(description = "User's phone", example = "3001234567")
    private String phoneUser;

    @Schema(description = "User's email address", example = "string@mail.com")
    private String emailUser;

    @Schema(description = "User's base salary", example = "3500000.00")
    private BigDecimal baseSalaryUser;

}
