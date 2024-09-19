package com.mlorenzo.customerservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Customer",
        description = "Schema to hold customer information"
)
@Getter
@Setter
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "John Doe")
    @NotBlank(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The length of the name must be between 5 and 30 characters")
    private String name;

    @Schema(description = "Email address of the customer", example = "john.doe@test.com")
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address must be valid")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "9345432123")
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "^[0-9]{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    @NotNull(message = "Account can not be null")
    @Valid
    private AccountDto account;
}
