package com.mlorenzo.loanservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Schema(
        name = "Loan",
        description = "Schema to hold loan information"
)
@Getter @Setter
public class LoanDto {
    @Schema(description = "The number of the loan", example = "1321631302")
    private Long number;

    @Schema(description = "Type of the loan", example = "Home Loan")
    private String type;

    @Schema(description = "Total amount of the loan", example = "10000.70")
    private BigDecimal total;

    @Schema(description = "Amount paid of the loan", example = "3500.50")
    private BigDecimal amountPaid;

    @Schema(description = "Outstanding amount of the loan", example = "6000")
    private BigDecimal outstandingAmount;
}
