package com.gymdesk.controller;

import com.gymdesk.dto.SubscriptionTypeDto;
import com.gymdesk.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "${application.base.api.path}/subscription")
@Slf4j
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionTypeDto> getAllSubscriptionType() {
        log.info("Get all subscription type endpoint reached");

        Scheduler scheduler = Schedulers.newParallel("asd", 5);

        Flux.range(1, 10)
                .flatMap(
                        i -> Mono.defer(() -> {
                            System.out.println(String.format("Executing %s on thread %s", i, Thread.currentThread().getName()));

                            subscriptionService.getAllSubscriptionType();

                            System.out.println(String.format("Finish executing %s", i));

                            return Mono.just(i);
                        }).subscribeOn(scheduler)

                ).subscribe();
                // .log()
                // .subscribe(System.out::println);

        return subscriptionService.getAllSubscriptionType();
    }
}
