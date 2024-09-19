package com.mlorenzo.loanservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.math.BigDecimal;

@Schema(
        name = "Loan Request",
        description = "Schema to request a loan"
)
// Esta anotación de Lombok convierte las propiedades en privadas y finales
@Value
public class LoanRequestDto {

    @Schema(description = "Mobile number of the customer", example = "9345432123")
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "^[0-9]{10}", message = "Mobile number must be 10 digits")
    String customerMobileNumber;

    @Schema(description = "Total amount of the loan", example = "10000.70")
    @Positive(message = "Total amount must be greater than zero")
    BigDecimal total;

    @Schema(description = "Type of the loan", example = "Home Loan")
    @NotBlank(message = "Type can not be null or empty")
    String type;
}
