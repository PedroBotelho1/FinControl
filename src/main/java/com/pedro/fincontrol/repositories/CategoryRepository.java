package com.pedro.fincontrol.repositories;

import com.pedro.fincontrol.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
