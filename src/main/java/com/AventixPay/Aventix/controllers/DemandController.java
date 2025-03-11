package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Demand;
import com.AventixPay.Aventix.service.DemandService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandController {

    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @PostMapping
    public ResponseEntity<Demand> createDemand(@RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.createDemand(demand));
    }

    @GetMapping
    public ResponseEntity<List<Demand>> getAllDemands() {
        return ResponseEntity.ok(demandService.getAllDemands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable Long id) {
        return ResponseEntity.ok(demandService.getDemandById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Demand> updateDemand(@PathVariable Long id, @RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.updateDemand(id, demand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemand(@PathVariable Long id) {
        demandService.deleteDemand(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demand> createDemandCarte(@RequestParam String email, @RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.createDemandCarte(email, demand));
    }
}
