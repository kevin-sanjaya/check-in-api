package com.gymdesk.service;

import com.gymdesk.client.Client;
import com.gymdesk.dto.InvoiceDto;
import com.gymdesk.dto.MemberDto;
import com.gymdesk.exception.NotFoundException;
import com.gymdesk.model.member.Member;
import com.gymdesk.model.member.MemberAttachment;
import com.gymdesk.repository.MemberAttachmentRepository;
import com.gymdesk.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@EnableAsync
public class MemberService {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAttachmentRepository memberAttachmentRepository;

    @Autowired
    private Client client;

    @Autowired
    private ModelMapper modelMapper;

    MemberService() {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Thread.getAllStackTraces().forEach((a, b) -> {
                            if (a.getState().equals(Thread.State.BLOCKED)) {
                                log.info(a.getName() + " " + a.getState());
                            }
                        }
                );
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    // public ParallelFlux<Member> getAllMember () {
    public Flux<Member> getAllMember() {

        log.info("Thread in service: " + Thread.currentThread().getName());

        return Flux.fromIterable(memberRepository.findAll())
                .flatMap(member -> {

                    log.info("Thread in flatmap: " + Thread.currentThread().getName());

                    return Mono.just(member)
                            .subscribeOn(Schedulers.parallel());
                })
                .doOnSubscribe(i -> log.info("Subscription start"))
                .doOnComplete(() -> log.info("Subscription finish"))
                .doOnNext(i -> log.info("Thread in flux: " + Thread.currentThread().getName()));
                // .onBackpressureDrop()

        // parallelism
        /*return Flux.fromIterable(memberRepository.findAll())
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnSubscribe(i -> log.info("Subscription start"))
                .doOnComplete(() -> log.info("Subscription finish"))
                .doOnNext(i -> log.info("Thread in flux: " + Thread.currentThread().getName()))
                .sequential();*/
    }

    @Async
    public CompletableFuture<List<Member>> getAllMemberAsync() {

        log.info(Thread.currentThread().getName());

        final List<Member> members = memberRepository.findAll();

        return CompletableFuture.completedFuture(members);
    }

    @Transactional
    @Async
    public CompletableFuture<InvoiceDto> createMember(MemberDto memberDto) {

        log.info(Thread.currentThread().getName());

        final Member member = modelMapper.map(memberDto, Member.class);

        CompletableFuture.runAsync(() -> {
            log.info(Thread.currentThread().getName());

            member.setIsMemberActive(Boolean.TRUE);
            member.setSubscriptionExpiryWarning(Boolean.FALSE);
            member.setExpiryDate(member.getStartDate().plusMonths(6L));

            final MemberAttachment memberAttachment = modelMapper.map(memberDto, MemberAttachment.class);
            member.setMemberAttachment(memberAttachment);

            memberRepository.save(member);

            log.info("Member {} saved into the database", member.getMemberId());
        });

        return invoiceService.createMemberInvoice(member, false);
    }

    public MemberDto getMemberById(UUID memberId) {
        log.info("Searching member record in the database");

        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundException::new);

        log.info("Member {} has been found", memberId);

        ModelMapper modelMapper = new ModelMapper();
        /* modelMapper.typeMap(Member.class, MemberDto.class)
                .addMapping(Member::getFullName, MemberDto::setFullName);*/
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);

        return memberDto;
    }

}
