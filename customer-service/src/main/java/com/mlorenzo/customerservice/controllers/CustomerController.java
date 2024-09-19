package com.mlorenzo.customerservice.controllers;

import com.mlorenzo.customerservice.dtos.CustomerDto;
import com.mlorenzo.customerservice.dtos.ErrorResponseDto;
import com.mlorenzo.customerservice.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for customers",
        description = "CRUD REST API to CREATE, UPDATE, FETCH and DELETE customers"
)
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(
            summary = "Fetch a customer",
            description = "It fetches an existing customer with their account by their mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode="200",
                    description = "OK Http Status"
            ),
            @ApiResponse(
                    responseCode="404",
                    description = "NOT FOUND Http Status",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("{mobileNumber}")
    public ResponseEntity<CustomerDto> getByMobileNumber(@PathVariable
                  @Pattern(regexp = "^[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber) {
        return ResponseEntity.ok(customerService.getByMobileNumber(mobileNumber));
    }

    @Operation(
            summary = "Create a customer",
            description = "It creates a new customer and their account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED Http Status"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST Http Status",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping
    ResponseEntity<CustomerDto> create(@Validated @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.create(customerDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a customer",
            description = "It updates an existing customer and their account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode="204",
                    description = "NO CONTENT Http Status"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST Http Status",
                    content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode="404",
                    description = "NOT FOUND Http Status",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("{mobileNumber}")
    public ResponseEntity<Void> update(@RequestBody @Validated CustomerDto customerDto, @PathVariable
            @Pattern(regexp = "^[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber) {
        customerService.updateByMobileNumber(customerDto, mobileNumber);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete a customer",
            description = "It deletes an existing customer and their account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode="204",
                    description = "NO CONTENT Http Status"
            ),
            @ApiResponse(
                    responseCode="404",
                    description = "NOT FOUND Http Status",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("{mobileNumber}")
    public ResponseEntity<Void> delete(@PathVariable
                @Pattern(regexp = "^[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber) {
        customerService.deleteByMobileNumber(mobileNumber);
        return ResponseEntity.noContent().build();
    }

}
