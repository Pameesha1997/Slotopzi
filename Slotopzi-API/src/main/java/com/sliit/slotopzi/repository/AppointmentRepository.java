package com.sliit.slotopzi.repository;

import com.sliit.slotopzi.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByStaff_StaffIdAndDate(long staffId,Date date);


}
