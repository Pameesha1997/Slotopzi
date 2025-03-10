package com.sliit.slotopzi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private long invoiceId;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal amount;

    @CreationTimestamp
    private Date invoiceDate;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "repair_id")
    Repair repair;

}
