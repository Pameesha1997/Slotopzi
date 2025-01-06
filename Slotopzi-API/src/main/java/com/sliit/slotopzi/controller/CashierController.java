package com.sliit.slotopzi.controller;

import com.sliit.slotopzi.dto.request.AddInvoiceDataRequest;
import com.sliit.slotopzi.dto.response.CashierRepairDetailsRespond;
import com.sliit.slotopzi.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")

public class CashierController {

    @Autowired
    CashierService cashierService;

    @GetMapping("/cashierGetRepairInvoice/{id}")//repair Id
    public ResponseEntity cashierGetRepairInvoice(@PathVariable long id){
        return ResponseEntity.ok(cashierService.cashierGetRepairInvoice(id));
    }

    @GetMapping("/getAllRepairs")
    public ResponseEntity getAllRepairs(){
        List<CashierRepairDetailsRespond> response = cashierService.getAllRepairs();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/getHandOverRepairs")
    public ResponseEntity getHandOverRepairs(){
        List<CashierRepairDetailsRespond> response = cashierService.getHandOverRepairs();
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/updateStatusPaid")
    public ResponseEntity updateStatusPaid(@RequestBody AddInvoiceDataRequest addInvoiceDataRequest){
        cashierService.updateStatusPaid(addInvoiceDataRequest);
        return ResponseEntity.ok().body("Status Updated Sucesssfully!!");
    }


}
