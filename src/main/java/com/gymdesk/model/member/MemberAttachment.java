package com.gymdesk.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member_attachment")
public class MemberAttachment {

    @Id
    @Column(name = "member_attachment_id")
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID memberAttachmentId;

    @Lob
    private byte[] scannedIdentityDocument;

    @Lob
    private byte[] scannedContractDocument;

    @Lob
    private byte[] memberPhoto;
}
