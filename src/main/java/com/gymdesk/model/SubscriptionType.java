package com.gymdesk.model;

import java.math.BigDecimal;

public enum SubscriptionType {
    BRONZE("Bronze", new BigDecimal("300000")),
    SILVER("Silver", new BigDecimal("450000")),
    GOLD("Gold", new BigDecimal("600000"));

    private String label;
    private BigDecimal monthlyFee;

    SubscriptionType(String label, BigDecimal monthlyFee) {
        this.label = label;
        this.monthlyFee = monthlyFee;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }
}
