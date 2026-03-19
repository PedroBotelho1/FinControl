package com.pedro.fincontrol.services;

import com.pedro.fincontrol.entities.RecurringBill;
import com.pedro.fincontrol.entities.Transaction;
import com.pedro.fincontrol.entities.enums.TransactionStatus;
import com.pedro.fincontrol.repositories.RecurringBillRepository;
import com.pedro.fincontrol.repositories.TransactionRepository;
import com.pedro.fincontrol.services.exceptions.DatabaseException;
import com.pedro.fincontrol.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RecurringBillRepository recurringBillRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        Optional<Transaction> obj = transactionRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Transaction insert(Transaction obj) {
        return transactionRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            transactionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Transaction update(Long id, Transaction obj) {
        try {
            Transaction entity = transactionRepository.getReferenceById(id);

            if(entity.getStatus() == TransactionStatus.PENDING && obj.getStatus() == TransactionStatus.PAID && entity.getRecurringBill() != null) {
                RecurringBill conta = entity.getRecurringBill();

                conta.setCurrentInstallment(conta.getCurrentInstallment() + 1);

                recurringBillRepository.save(conta);
            }
            updateData(entity, obj);
            return transactionRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Transaction entity, Transaction obj) {
        entity.setDescription(obj.getDescription());
        entity.setAmount(obj.getAmount());
        entity.setDueDate(obj.getDueDate());
        entity.setStatus(obj.getStatus());
        entity.setType(obj.getType());
    }
}
