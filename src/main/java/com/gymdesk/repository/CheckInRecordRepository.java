package com.gymdesk.repository;

import com.gymdesk.model.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckInRecordRepository extends JpaRepository<CheckInRecord, UUID> {
}
