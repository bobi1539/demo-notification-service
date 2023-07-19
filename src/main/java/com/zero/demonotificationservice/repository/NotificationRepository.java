package com.zero.demonotificationservice.repository;

import com.zero.demonotificationservice.entity.MNotification;
import com.zero.demonotificationservice.entity.MUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<MNotification, Integer> {
    List<MNotification> findAll(Specification<MNotification> specification);
}
