package com.sliit.slotopzi.service;

import com.sliit.slotopzi.dto.request.AddAppointment;
import com.sliit.slotopzi.dto.request.AddAppointmentV;
import com.sliit.slotopzi.dto.response.StaffListRespond;
import com.sliit.slotopzi.model.Appointment;
import com.sliit.slotopzi.model.AppointmentSlot;
import com.sliit.slotopzi.model.Staff;
import com.sliit.slotopzi.model.Vehicle;
import com.sliit.slotopzi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentSlotsRepository appointmentSlotsRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    StaffRepository staffRepository;

    public List<AppointmentSlot> getAllSlots() {
        return appointmentSlotsRepository.findAll();
    }

    public boolean addAppointment(AddAppointment addAppointment) {
        Appointment appointment = new Appointment();

        appointment.setVehicle(vehicleRepository.findByVehicleId(addAppointment.getVehicleId()));
        appointment.setDate(addAppointment.getDate());
        appointment.setStaff(staffRepository.findByStaffId(addAppointment.getStaffId()));
        appointment.setAppointmentSlot(appointmentSlotsRepository.findByAppointmentSlotId(addAppointment.getSlotId()));

        if(appointmentRepository.save(appointment)!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean addAppointmentV(AddAppointmentV addAppointmentv) {
        Appointment appointment = new Appointment();

        Vehicle newVehicle = new Vehicle();
        newVehicle.setVehicleNumber(addAppointmentv.getvNo());
        newVehicle.setCustomer(customerRepository.findByUserData_Id(addAppointmentv.getUserid()));
        try {
            Vehicle addedVehicle = vehicleRepository.save(newVehicle);
            appointment.setVehicle(addedVehicle);
        }catch (Exception e){
            return false;
        }
        appointment.setDate(addAppointmentv.getDate());
        appointment.setStaff(staffRepository.findByStaffId(addAppointmentv.getStaffId()));
        appointment.setAppointmentSlot(appointmentSlotsRepository.findByAppointmentSlotId(addAppointmentv.getSlotId()));

        if(appointmentRepository.save(appointment)!=null){
            return true;
        }else{
            return false;
        }
    }

    public List<AppointmentSlot> getFreeSlotsByDate(Date date) {
        String shortDate=new SimpleDateFormat("YYYY-MM-dd").format(date);
        List<AppointmentSlot> response = appointmentSlotsRepository.findAvailableSlotsbyDate(shortDate);
        if(response!=null) {
            return response;
        }else{
            return null;
        }
    }

    public List<StaffListRespond> getServiceAdvisorFromDate(Date date,long id) {
        String shortDate=new SimpleDateFormat("YYYY-MM-dd").format(date);
        List<Staff> output=staffRepository.findAvailableServiceAdvisorsByDate(shortDate,id);
        List<StaffListRespond> response = new ArrayList<>();
        for(Staff staff:output){
            StaffListRespond out = new StaffListRespond();
            out.setFirstName(staff.getFirstName());
            out.setLastname(staff.getLastName());
            out.setId(staff.getStaffId());
            response.add(out);
        }
        return response;
    }



//    public ResponseEntity getUpcomingAppointments(long id) {
//        List<Appointments> appointments = staffRepository.
//    }
}
