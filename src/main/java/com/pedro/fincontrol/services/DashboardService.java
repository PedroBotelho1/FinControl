package com.pedro.fincontrol.services;

import com.pedro.fincontrol.entities.enums.TransactionType;
import com.pedro.fincontrol.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository repository;

    public Double getBalance(Long userId) {

        Double income = repository.sumAmountByUserIdAndType(userId, TransactionType.INCOME);

        Double expense = repository.sumAmountByUserIdAndType(userId, TransactionType.EXPENSE);

        if(income == null) income = 0.0;
        if(expense == null) expense = 0.0;

        return income - expense;
    }
}
