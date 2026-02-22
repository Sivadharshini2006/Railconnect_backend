package com.railconnect.trainservice.controller;

import com.railconnect.trainservice.model.Quota;
import com.railconnect.trainservice.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotas")
@CrossOrigin(origins = "http://localhost:5173")
public class QuotaController {

    @Autowired
    private QuotaRepository repository;

    @GetMapping("/all")
    public List<Quota> getAllQuotas() {
        return repository.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Quota> updateQuota(@PathVariable String id, @RequestBody Quota details) {
        return repository.findById(id).map(quota -> {
            quota.setDesc(details.getDesc());
            quota.setPct(details.getPct());
            return ResponseEntity.ok(repository.save(quota));
        }).orElse(ResponseEntity.notFound().build());
    }
}