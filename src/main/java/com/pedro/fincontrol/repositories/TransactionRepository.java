package com.pedro.fincontrol.repositories;

import com.pedro.fincontrol.entities.Transaction;
import com.pedro.fincontrol.entities.User;
import com.pedro.fincontrol.entities.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = :type")
    Double sumAmountByUserIdAndType(Long userId, TransactionType type);
}
