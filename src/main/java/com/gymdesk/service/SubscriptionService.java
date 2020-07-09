package com.gymdesk.service;


import com.gymdesk.dto.SubscriptionTypeDto;
import com.gymdesk.model.SubscriptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Slf4j
@Service
public class SubscriptionService {

    public List<SubscriptionTypeDto> getAllSubscriptionType() {
        List<SubscriptionTypeDto> subscriptionTypes = new ArrayList<>();

        EnumSet.allOf(SubscriptionType.class)
                .forEach(subscription -> subscriptionTypes.add(
                        new SubscriptionTypeDto().builder()
                                .value(subscription)
                                .label(subscription.getLabel())
                                .monthlyFee(subscription.getMonthlyFee())
                                .build()));

        return subscriptionTypes;
    }
}
