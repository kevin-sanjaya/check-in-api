package com.gymdesk.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymdesk.model.SubscriptionType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MemberDto {

    @NotNull
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    private String mobileNumber;

    private String placeOfBirth;

    private String address;

    private String emergencyContact;

    private String occupation;

    @NotNull
    private LocalDate startDate;

    private LocalDate expiryDate;

    @NotNull
    private LocalDate dateOfBirth;

    private byte[] memberPhoto;

    private byte[] scannedIdentityDocument;

    private byte[] scannedContractDocument;
}
