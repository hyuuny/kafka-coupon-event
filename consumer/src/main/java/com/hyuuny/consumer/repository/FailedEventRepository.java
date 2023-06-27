package com.hyuuny.consumer.repository;

import com.hyuuny.consumer.domain.FailedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedEventRepository extends JpaRepository<FailedEvent, Long> {
}
