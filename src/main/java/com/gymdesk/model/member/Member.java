package com.gymdesk.model.member;

import com.gymdesk.model.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_member") // member is a reserved word in MySQL (!)
public class Member {

    @Id
    @Column(name = "member_id")
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID memberId;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private String address;

    private String occupation;

    private String mobileNumber;

    private String emergencyContact;

    private LocalDate startDate;

    private LocalDate expiryDate;

    private Boolean subscriptionExpiryWarning;

    private Boolean isMemberActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_attachment_id", referencedColumnName = "member_attachment_id")
    private MemberAttachment memberAttachment;
}
