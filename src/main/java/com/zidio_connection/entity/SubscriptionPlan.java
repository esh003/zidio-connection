package com.zidio_connection.entity;

import com.zidio_connection.enums.SubPlanDuration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscription_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "Basic", "Pro", "Premium"
    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    // e.g. 499.0, 999.0
    @Column(nullable = false)
    private Double price;

    // MONTHLY / QUARTERLY / HALFYEARLY / YEARLY
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubPlanDuration duration;

    // make plan visible or not
    private Boolean active = true;
}
