package com.pedro.fincontrol.resources;


import com.pedro.fincontrol.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dashboard")
@CrossOrigin("*")
public class DashboardResource {

    @Autowired
    private DashboardService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Double> getResumo(@PathVariable Long id) {
        Double balance = service.getBalance(id);
        return ResponseEntity.ok().body(balance);
    }
}
