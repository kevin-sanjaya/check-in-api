package com.gymdesk.dto;

import com.gymdesk.model.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionTypeDto {

    @Enumerated(EnumType.STRING)
    private SubscriptionType value;

    private String label;

    private BigDecimal monthlyFee;
}
