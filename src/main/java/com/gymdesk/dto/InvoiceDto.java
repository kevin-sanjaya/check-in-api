package com.gymdesk.dto;

import com.gymdesk.model.InvoiceType;
import com.gymdesk.model.SubscriptionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class InvoiceDto {

    private UUID invoiceId;

    private SubscriptionType subscriptionType;

    private BigDecimal administrationFee;

    private BigDecimal subscriptionMonthlyFee;

    private BigDecimal subscriptionTotalFee;

    private BigDecimal discount;

    private String discountReason;

    private BigDecimal totalFee;

    private LocalDate generatedOn;

    private InvoiceType invoiceType;

    private String memberName;
}
