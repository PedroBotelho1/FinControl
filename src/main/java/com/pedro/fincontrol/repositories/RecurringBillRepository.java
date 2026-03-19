package com.pedro.fincontrol.repositories;

import com.pedro.fincontrol.entities.RecurringBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringBillRepository extends JpaRepository<RecurringBill, Long> {
}
