package com.zidio_connection.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zidio_connection.entity.SubscriptionPlan;
import com.zidio_connection.enums.SubPlanDuration;
import com.zidio_connection.exception.ResourceNotFoundException;
import com.zidio_connection.repository.SubPlanRepository;

@Service
public class SubscriptionPlanService {

    private final SubPlanRepository subPlanRepo;

    public SubscriptionPlanService(SubPlanRepository subPlanRepo) {
        this.subPlanRepo = subPlanRepo;
    }

    public SubscriptionPlan createPlan(SubscriptionPlan plan) {
        // ensure active is true by default if not set
        if (plan.getActive() == null) {
            plan.setActive(true);
        }
        return subPlanRepo.save(plan);
    }

    public List<SubscriptionPlan> getActivePlans() {
        return subPlanRepo.findByActiveTrue();
    }

    public List<SubscriptionPlan> getByDuration(SubPlanDuration duration) {
        return subPlanRepo.findByDurationAndActiveTrue(duration);
    }

    public SubscriptionPlan deactivate(Long id) {
        SubscriptionPlan plan = subPlanRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription plan not found with id: " + id));
        plan.setActive(false);
        return subPlanRepo.save(plan);
    }
}
