package com.gymdesk.dto;

import lombok.Data;

@Data
public class MemberAttachmentDto {

    private byte[] memberPhoto;

    private byte[] scannedIdentityDocument;

    private byte[] scannedContractDocument;
}
