package com.v1.repayment.strategy;

import com.v1.repayment.domain.LoanInfo;
import com.v1.repayment.domain.RepaymentSchedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class EmiStrategy implements RepaymentStrategy {
    private static final BigDecimal YEAR_MONTHS = BigDecimal.valueOf(12);

    @Override
    public List<RepaymentSchedule> calculateRepaymentSchedule(LoanInfo loanInfo) {
        List<RepaymentSchedule> schedules = new ArrayList<>();

        BigDecimal rate = convertToRate(loanInfo.getInterestRate());
        BigDecimal monthlyRate = rate.divide(YEAR_MONTHS, 10, RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = getMonthlyPayment(loanInfo.getLoanAmount(), monthlyRate, loanInfo.getTerm())
                .setScale(0, RoundingMode.HALF_UP);
        BigDecimal remainingBalance = loanInfo.getLoanAmount();

        for (int month = 1; month <= loanInfo.getTerm(); month++) {
            BigDecimal interest = remainingBalance.multiply(monthlyRate).setScale(0, RoundingMode.DOWN);
            BigDecimal principal = monthlyPayment.subtract(interest);

            if (month == loanInfo.getTerm()) {
                principal = remainingBalance;
            }

            remainingBalance = remainingBalance.subtract(principal);

            schedules.add(RepaymentSchedule.builder()
                    .paymentNum(month)
                    .principal(principal)
                    .interest(interest)
                    .remainingBalance(remainingBalance)
                    .build());
        }

        return schedules;
    }

    /**
     * 월 납입금 계산
     * 원금 × (월이자율 × (1 + 월이자율)^기간) / ((1 + 월이자율)^기간 - 1)
     */
    private BigDecimal getMonthlyPayment(BigDecimal principal, BigDecimal monthlyRate, int term) {
        BigDecimal top = monthlyRate.multiply(
                BigDecimal.ONE.add(monthlyRate).pow(term)
        );
        BigDecimal bottom = BigDecimal.ONE.add(monthlyRate).pow(term).subtract(BigDecimal.ONE);

        return principal.multiply(top).divide(bottom, 0, RoundingMode.DOWN);
    }
}
