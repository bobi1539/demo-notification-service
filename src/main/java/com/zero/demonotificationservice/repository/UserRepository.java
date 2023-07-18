package com.zero.demonotificationservice.repository;

import com.zero.demonotificationservice.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<MUser, UUID> {
}
