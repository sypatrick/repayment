package com.v1.repayment.strategy;

import com.v1.repayment.domain.LoanInfo;
import com.v1.repayment.domain.RepaymentSchedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BulletPaymentStrategy implements RepaymentStrategy{
    private static final BigDecimal YEAR_MONTHS = BigDecimal.valueOf(12);

    @Override
    public List<RepaymentSchedule> calculateRepaymentSchedule(LoanInfo loanInfo) {
        List<RepaymentSchedule> schedules = new ArrayList<>();

        BigDecimal rate = convertToRate(loanInfo.getInterestRate());
        BigDecimal monthlyRate = rate.divide(YEAR_MONTHS, 10, RoundingMode.HALF_UP);
        BigDecimal monthlyInterest = loanInfo.getLoanAmount().multiply(monthlyRate)
                .setScale(0, RoundingMode.DOWN);

        for (int month = 1; month <= loanInfo.getTerm(); month++) {
            boolean isLastMonth = (month == loanInfo.getTerm());

            schedules.add(RepaymentSchedule.builder()
                    .paymentNum(month)
                    .principal(isLastMonth ? loanInfo.getLoanAmount() : BigDecimal.ZERO)
                    .interest(monthlyInterest)
                    .remainingBalance(isLastMonth ? BigDecimal.ZERO : loanInfo.getLoanAmount())
                    .build());
        }

        return schedules;
    }

}
