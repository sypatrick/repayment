package com.v1.repayment.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 *  1. 회차
 *  2. 원금
 *  3. 이자
 *  4. 잔금
 */
@Getter
@Builder
public class RepaymentSchedule {
    private int paymentNum;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal remainingBalance;

    public BigDecimal getTotalPayment() {
        return principal.add(interest);
    }
}
