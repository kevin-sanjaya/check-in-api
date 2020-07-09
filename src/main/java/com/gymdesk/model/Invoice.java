package com.gymdesk.model;

import com.gymdesk.model.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
@Builder
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @Type(type = "uuid-char")
    @GeneratedValue
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

    private Boolean isPaid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;
}
