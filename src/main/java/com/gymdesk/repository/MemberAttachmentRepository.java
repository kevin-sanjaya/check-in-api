package com.gymdesk.repository;

import com.gymdesk.model.member.MemberAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberAttachmentRepository extends JpaRepository<MemberAttachment, UUID> {
}
