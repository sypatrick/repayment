package com.v1.repayment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LoanRequest {
    @NotNull(message = "대출금액을 기입하여 주세요.")
    @DecimalMin(value = "0", message = "0원 이상이어야합니다.")
    private BigDecimal loanAmount;

    @Min(value = 1, message = "최소 1개월이상이어야 합니다.")
    private int term;

    @NotNull(message = "이자율을 기입하여 주세요.")
    @DecimalMin(value = "0", message = "이자율은 0 이상이어야합니다.")
    private BigDecimal interestRate;

    @NotBlank(message = "상환 방식은 필수입니다.")
    private String paymentType; // 만기일시상환 or 원리금균등상환
}
