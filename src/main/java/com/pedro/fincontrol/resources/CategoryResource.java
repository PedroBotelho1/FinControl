package com.pedro.fincontrol.resources;

import com.pedro.fincontrol.entities.Category;
import com.pedro.fincontrol.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<Category> insert(@RequestBody Category obj) {
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
