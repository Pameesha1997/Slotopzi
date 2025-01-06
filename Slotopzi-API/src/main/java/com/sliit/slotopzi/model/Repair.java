package com.sliit.slotopzi.model;

import com.sliit.slotopzi.enums.PaymentType;
import com.sliit.slotopzi.enums.RepairStatus;
import com.sliit.slotopzi.enums.RepairType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Repair {

    @Id
    @GeneratedValue
    private long repairId;

    @CreationTimestamp
    private Date repairAddedDate;

    @Column
    private Date repairCompletedDate;

    @Column
    private String fbDocId;

    @Column
    private int millage;

    @Column
    @Enumerated(EnumType.STRING)
    private RepairType repairType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    @ManyToOne
    @JsonIgnore
    Staff staff;

    @OneToMany(targetEntity = ServiceEntry.class, mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ServiceEntry> serviceEntries;

    @OneToOne(targetEntity = Invoice.class, mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonIgnore
    private Invoice invoices;

    @OneToMany(targetEntity = RateAndReview.class, mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RateAndReview> rateAndReviews;



}
