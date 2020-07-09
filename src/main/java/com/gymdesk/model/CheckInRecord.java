package com.gymdesk.model;

import com.gymdesk.model.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "check_in_record")
public class CheckInRecord {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID recordId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member checkedInMember;

    private LocalDateTime checkInTimestamp;

    private String occupiedStorageNumber;

    private RentalEquipment rentedEquipment;
}
