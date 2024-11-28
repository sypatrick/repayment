package com.v1.repayment.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 *  1. 대출금액
 *  2. 대출기간
 *  3. 이자율
 *  4. 상환 타입
 */
@Getter
@Builder
public class LoanInfo {
    private BigDecimal loanAmount;
    private int term;
    private BigDecimal interestRate;
    private String paymentType;
}
