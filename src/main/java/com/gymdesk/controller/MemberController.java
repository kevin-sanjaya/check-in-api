package com.gymdesk.controller;

import com.gymdesk.dto.InvoiceDto;
import com.gymdesk.dto.MemberDto;
import com.gymdesk.model.member.Member;
import com.gymdesk.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping(path = "${application.base.api.path}/member")
@Slf4j
@EnableAsync
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/create") // async post member
    public CompletableFuture<InvoiceDto> createMember(@RequestBody @Valid MemberDto memberDto) {

        log.info("Create member endpoint reached");

        log.info(Thread.currentThread().getName());

        return memberService.createMember(memberDto);
    }

    @GetMapping // flux get all member
    public Publisher<Member> getAllMember() {

        log.info("Thread in controller: " +  Thread.currentThread().getName());

        return memberService.getAllMember();
    }

    @GetMapping(value = "/async") // async get all member
    public CompletableFuture<List<Member>> getAllMemberAsync() {

        log.info(Thread.currentThread().getName());

        return memberService.getAllMemberAsync();
    }

    @GetMapping(value = "/test")
    public HttpStatus test() {
        return HttpStatus.OK;
    }


    @GetMapping(value = "/{uuid}")
    public MemberDto getMemberById(@PathVariable("uuid") UUID memberId) {
        log.info("Get member {} endpoint reached", memberId);

        return memberService.getMemberById(memberId);
    }
}
