package com.mlorenzo.customerservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
// Esta anotación de Lombok convierte las propiedades en privadas y finales
@Value
public class ErrorResponseDto {

    @Schema(description = "API path invoked by the client")
    String apiPath;

    @Schema(description = "Error message representing the error happened")
    String message;

    @Schema(description = "Date and time representing when the error happened")
    LocalDateTime date;
}
