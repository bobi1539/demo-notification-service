package com.zero.demonotificationservice.repository;

import com.zero.demonotificationservice.entity.MNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<MNotification, Integer> {
}
