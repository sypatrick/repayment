package com.v1.repayment.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class LoanResponse {
    @Getter
    @Builder
    public static class RepaymentInfo{
        private int paymentNum; // 납입회차
        private BigDecimal principal;
        private BigDecimal interest;
        private BigDecimal totalPayment;
    }

    @Getter
    @Builder
    public static class RepaymentDetail{
        private BigDecimal loanAmount;
        private int term;
        private BigDecimal interestRate;
        private String paymentType;
        private List<RepaymentInfo> repaymentInfoList;
    }
}
