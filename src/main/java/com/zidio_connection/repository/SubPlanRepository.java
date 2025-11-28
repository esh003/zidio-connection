package com.zidio_connection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zidio_connection.entity.SubscriptionPlan;
import com.zidio_connection.enums.SubPlanDuration;

@Repository
public interface SubPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    List<SubscriptionPlan> findByActiveTrue();

    List<SubscriptionPlan> findByDurationAndActiveTrue(SubPlanDuration duration);
}
