package com.zidio_connection.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.entity.SubscriptionPlan;
import com.zidio_connection.enums.SubPlanDuration;
import com.zidio_connection.service.SubscriptionPlanService;

@RestController
@RequestMapping("/api/subscriptions/plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService planService;

    public SubscriptionPlanController(SubscriptionPlanService planService) {
        this.planService = planService;
    }

    // Create a new plan
    @PostMapping
    public ResponseEntity<SubscriptionPlan> create(@RequestBody SubscriptionPlan plan) {
        return ResponseEntity.ok(planService.createPlan(plan));
    }

    // Get all active plans
    @GetMapping
    public ResponseEntity<List<SubscriptionPlan>> getAllActive() {
        return ResponseEntity.ok(planService.getActivePlans());
    }

    // Filter by duration
    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<SubscriptionPlan>> getByDuration(@PathVariable SubPlanDuration duration) {
        return ResponseEntity.ok(planService.getByDuration(duration));
    }

    // Deactivate a plan
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<SubscriptionPlan> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(planService.deactivate(id));
    }
}
