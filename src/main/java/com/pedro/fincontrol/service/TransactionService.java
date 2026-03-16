package com.pedro.fincontrol.service;

import com.pedro.fincontrol.entities.Transaction;
import com.pedro.fincontrol.repositories.TransactionRepository;
import com.pedro.fincontrol.service.exceptions.DatabaseException;
import com.pedro.fincontrol.service.exceptions.ResourceNotFoundException;
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
    private TransactionRepository repository;

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction findById(Long id) {
        Optional<Transaction> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Transaction insert(Transaction obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Transaction update(Long id, Transaction obj) {
        try {
            Transaction entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
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
