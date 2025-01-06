package com.sliit.slotopzi.controller;

import com.sliit.slotopzi.dto.request.AddAppointment;
import com.sliit.slotopzi.dto.request.AddAppointmentV;
import com.sliit.slotopzi.dto.response.StaffListRespond;
import com.sliit.slotopzi.model.AppointmentSlot;
import com.sliit.slotopzi.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/slots")
    ResponseEntity getVehicles() {
        return ResponseEntity.ok().body(appointmentService.getAllSlots());
    }

    @PostMapping("/addappointment")
    ResponseEntity addNewAppointment(@RequestBody AddAppointment addAppointment){
        boolean success =appointmentService.addAppointment(addAppointment);
        if(success) {
            return ResponseEntity.ok().body("Appointment Placed Successfully");
        }else{
            return ResponseEntity.badRequest().body("Error!!");
        }
    }

    @PostMapping("/addappointmentwithvehicle")
    ResponseEntity addNewAppointmentWithVehicle(@RequestBody AddAppointmentV addAppointmentv){
        boolean success =appointmentService.addAppointmentV(addAppointmentv);
        if(success) {
            return ResponseEntity.ok().body("Appointment Placed Successfully");
        }else{
            return ResponseEntity.badRequest().body("Error!!");
        }
    }

    @GetMapping("/getslotsfromdate/{date}")
    ResponseEntity getSlotsFromDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<AppointmentSlot> slots = appointmentService.getFreeSlotsByDate(date);
        if(slots.isEmpty()) {
            return ResponseEntity.badRequest().body("Sorry No any Free Slots Available on the Date you Selected!");
        }else{
            return ResponseEntity.ok().body(appointmentService.getFreeSlotsByDate(date));
        }
    }

    @GetMapping("/getadvisorfromdateandslot/{date}/{id}")
    ResponseEntity getAdvisorFromDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@PathVariable long id) {
        List<StaffListRespond> result = appointmentService.getServiceAdvisorFromDate(date,id);
        if(result.size()==0){
            return ResponseEntity.badRequest().body("No Any Service Advisors Available Today!!");
        }else {
            return ResponseEntity.ok().body(appointmentService.getServiceAdvisorFromDate(date, id));
        }
    }

//    @GetMapping("/getupcommingAppointments/{id}")
//    ResponseEntity getUpcoming(@PathVariable long id){
//        return ResponseEntity.ok().body(appointmentService.getUpcomingAppointments(id));
//    }


}
