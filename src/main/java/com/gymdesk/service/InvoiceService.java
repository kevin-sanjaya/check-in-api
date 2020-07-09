package com.gymdesk.service;

import com.gymdesk.dto.InvoiceDto;
import com.gymdesk.model.Invoice;
import com.gymdesk.model.InvoiceType;
import com.gymdesk.model.member.Member;
import com.gymdesk.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class InvoiceService {

    private static final BigDecimal ADMINISTRATION_FEE = new BigDecimal("50000");

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Async
    public CompletableFuture<InvoiceDto> createMemberInvoice(Member member, Boolean isMemberExtended) {

        log.info(Thread.currentThread().getName());

        final Invoice invoice = new Invoice().builder()
                .member(member)
                .administrationFee(isMemberExtended ? new BigDecimal("0") : ADMINISTRATION_FEE)
                .subscriptionMonthlyFee(member.getSubscriptionType().getMonthlyFee())
                .subscriptionTotalFee(calculateSubscriptionTotalFee(member.getSubscriptionType().getMonthlyFee()))
                .totalFee(calculateTotalFee(member.getSubscriptionType().getMonthlyFee()))
                .subscriptionType(member.getSubscriptionType())
                .generatedOn(LocalDate.now())
                .invoiceType(InvoiceType.NEW)
                .isPaid(Boolean.FALSE)
                .build();

        invoiceRepository.save(invoice);

        log.info("Invoice {} saved into the database", invoice.getInvoiceId());

        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
        invoiceDto.setMemberName(member.getFullName());

        return CompletableFuture.completedFuture(invoiceDto);
    }

    private BigDecimal calculateSubscriptionTotalFee(BigDecimal monthlyFee) {
        BigDecimal subscriptionTotalFee = monthlyFee.multiply(new BigDecimal("6"));

        return subscriptionTotalFee;
    }

    private BigDecimal calculateTotalFee(BigDecimal monthlyFee) {
        BigDecimal totalFee = monthlyFee.multiply(new BigDecimal("6")).add(ADMINISTRATION_FEE);

        return totalFee;
    }
}
