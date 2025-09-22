package co.com.bancolombia.api.dto.response.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseBodyDto {
    @Schema(description = "error message")
    private String message;

    @Schema(description = "error code")
    private String code;

    @Schema(description = "time of error")
    private String timestamp;

    @Schema(description = "error path")
    private String path;
}
