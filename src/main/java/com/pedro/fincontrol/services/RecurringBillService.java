package com.pedro.fincontrol.services;


import com.pedro.fincontrol.entities.RecurringBill;
import com.pedro.fincontrol.repositories.RecurringBillRepository;
import com.pedro.fincontrol.services.exceptions.DatabaseException;
import com.pedro.fincontrol.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class RecurringBillService {

    @Autowired
    private RecurringBillRepository repository;

    public List<RecurringBill> findAll() {
        return repository.findAll();
    }

    public RecurringBill findById(Long id) {
        Optional<RecurringBill> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public RecurringBill insert(RecurringBill obj) {
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

    public RecurringBill update(Long id, RecurringBill obj) {
        try {
            RecurringBill entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(RecurringBill entity, RecurringBill obj) {
        entity.setTitle(obj.getTitle());
        entity.setTotalInstallments(obj.getTotalInstallments());
        entity.setCurrentInstallment(obj.getCurrentInstallment());
        entity.setInstallmentValue(obj.getInstallmentValue());
        entity.setStartDate(obj.getStartDate());
    }
}
