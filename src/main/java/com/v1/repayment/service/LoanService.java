package com.v1.repayment.service;

import com.v1.repayment.domain.LoanInfo;
import com.v1.repayment.domain.RepaymentSchedule;
import com.v1.repayment.dto.LoanRequest;
import com.v1.repayment.dto.LoanResponse.RepaymentInfo;
import com.v1.repayment.dto.LoanResponse.RepaymentDetail;
import com.v1.repayment.type.PaymentType;
import com.v1.repayment.strategy.RepaymentStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    public RepaymentDetail calculateRepayment(LoanRequest request) {

        LoanInfo loanInfo = LoanInfo.builder()
                .loanAmount(request.getLoanAmount())
                .term(request.getTerm())
                .interestRate(request.getInterestRate())
                .paymentType(request.getPaymentType())
                .build();

        RepaymentStrategy strategy = PaymentType.valueOf(request.getPaymentType()).createStrategy();

        List<RepaymentSchedule> schedule = strategy.calculateRepaymentSchedule(loanInfo);

        List<RepaymentInfo> repaymentInfoList = schedule.stream()
                .map(scheduleItem -> RepaymentInfo.builder()
                        .paymentNum(scheduleItem.getPaymentNum())
                        .principal(scheduleItem.getPrincipal())
                        .interest(scheduleItem.getInterest())
                        .totalPayment(scheduleItem.getTotalPayment())
                        .build())
                .collect(Collectors.toList());

        return RepaymentDetail.builder()
                .loanAmount(loanInfo.getLoanAmount())
                .term(loanInfo.getTerm())
                .interestRate(loanInfo.getInterestRate())
                .paymentType(loanInfo.getPaymentType())
                .repaymentInfoList(repaymentInfoList)
                .build();
    }
}