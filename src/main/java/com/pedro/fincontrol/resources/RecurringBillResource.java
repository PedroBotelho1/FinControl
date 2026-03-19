package com.pedro.fincontrol.resources;

import com.pedro.fincontrol.entities.RecurringBill;
import com.pedro.fincontrol.services.RecurringBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/recurringbill")
public class RecurringBillResource {

    @Autowired
    private RecurringBillService service;

    @GetMapping
    public ResponseEntity<List<RecurringBill>> findAll() {
        List<RecurringBill> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RecurringBill> findById(@PathVariable Long id) {
        RecurringBill obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<RecurringBill> insert(@RequestBody RecurringBill obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RecurringBill> update(@PathVariable Long id, @RequestBody RecurringBill obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
