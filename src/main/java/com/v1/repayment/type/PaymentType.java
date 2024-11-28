package com.v1.repayment.type;

import com.v1.repayment.strategy.BulletPaymentStrategy;
import com.v1.repayment.strategy.EmiStrategy;
import com.v1.repayment.strategy.RepaymentStrategy;

public enum PaymentType {
    EQUATED_MONTHLY_INSTALLMENT {
        @Override
        public RepaymentStrategy createStrategy() {
            return new EmiStrategy();
        }
    },
    BULLET_PAYMENT {
        @Override
        public RepaymentStrategy createStrategy() {
            return new BulletPaymentStrategy();
        }
    };

    public abstract RepaymentStrategy createStrategy();
}
