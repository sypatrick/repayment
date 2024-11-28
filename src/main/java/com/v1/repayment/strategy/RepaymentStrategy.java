package com.v1.repayment.strategy;

import com.v1.repayment.domain.LoanInfo;
import com.v1.repayment.domain.RepaymentSchedule;

import java.math.BigDecimal;
import java.util.List;

public interface RepaymentStrategy {
    List<RepaymentSchedule> calculateRepaymentSchedule(LoanInfo loanInfo);

    default BigDecimal convertToRate(BigDecimal percentageRate){
        return percentageRate.divide(BigDecimal.valueOf(100));
    }
}
