package com.orbit.repository;

import com.orbit.entity.Attachment;
import com.orbit.entity.EventType;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 附件repository
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
