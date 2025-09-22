package co.com.bancolombia.api.dto.response.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    @Schema(description = "access token identifier")
    private String accessToken;

    @Schema(description = "expires of access token")
    private Long expiresIn;
}
