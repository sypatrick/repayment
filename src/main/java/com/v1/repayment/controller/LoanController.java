package com.v1.repayment.controller;

import com.v1.repayment.dto.LoanRequest;
import com.v1.repayment.dto.LoanResponse.RepaymentDetail;
import com.v1.repayment.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/repayment")
    public ResponseEntity<RepaymentDetail> calculateRepayment(@Valid @RequestBody LoanRequest request){
        RepaymentDetail res = loanService.calculateRepayment(request);
        return ResponseEntity.ok(res);
    }
}
