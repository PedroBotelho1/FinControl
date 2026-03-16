package com.pedro.fincontrol.repositories;

import com.pedro.fincontrol.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
