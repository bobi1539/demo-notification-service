package com.zero.demonotificationservice.repository;

import com.zero.demonotificationservice.entity.MCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<MCategory, Integer> {
}
