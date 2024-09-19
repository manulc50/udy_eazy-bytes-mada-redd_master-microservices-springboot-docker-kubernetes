package com.mlorenzo.customerservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
@Getter
@Setter
public class AccountDto {

    @Schema(description = "Number of the account", example = "3454433243")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long number;

    @Schema(description = "Type of the account", example = "Savings")
    @NotBlank(message = "Type can not be null or empty")
    private String type;

    @Schema(description = "Branch Address of the account", example = "123 Main Street, New York")
    @NotBlank(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
