package com.sliit.slotopzi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private long appointmentId;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date date;

    @CreationTimestamp
    private Date addedDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "appointment_slot")
    AppointmentSlot appointmentSlot;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id")
    Staff staff;

}
