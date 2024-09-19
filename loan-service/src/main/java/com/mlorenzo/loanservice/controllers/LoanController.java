package com.mlorenzo.loanservice.controllers;

import com.mlorenzo.loanservice.dtos.ErrorResponseDto;
import com.mlorenzo.loanservice.dtos.LoanDto;
import com.mlorenzo.loanservice.dtos.LoanRequestDto;
import com.mlorenzo.loanservice.services.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Tag(
        name = "CRUD REST API for Loans",
        description = "CRUD REST API to CREATE, UPDATE, FETCH and DELETE loans"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    @Operation(
            summary = "Fetch a Loan",
            description = "It fetches an existing Loan"
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
                            schema = @Schema(implementation = ResponseStatusException.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public LoanDto fetchById(@PathVariable("id") Long loanNumber) {
        return loanService.fetchById(loanNumber);
    }

    @Operation(
            summary = "Create a Loan",
            description = "It creates a new Loan"
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LoanDto create(@Valid @RequestBody LoanRequestDto loanRequestDto) {
        return loanService.create(loanRequestDto);
    }

    @Operation(
            summary = "Patch a Loan",
            description = "It patches an existing Loan to update the amount paid"
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
            ),
            @ApiResponse(
                    responseCode="400",
                    description = "BAD REQUEST Http Status",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/pay")
    public void payAmount(@RequestParam BigDecimal amount, @PathVariable(value = "id") Long loanNumber) {
        loanService.payAmount(amount, loanNumber);
    }

    @Operation(
            summary = "Delete a Loan",
            description = "It deletes an existing Loan"
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/id")
    public void delete(@PathVariable(name = "id") Long loanNumber) {
        loanService.deleteById(loanNumber);
    }
}
