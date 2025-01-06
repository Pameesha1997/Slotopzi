package com.sliit.slotopzi.controller;

import com.sliit.slotopzi.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/byrepairid/{id}")//repair Id
    public ResponseEntity getRepairInvoice(@PathVariable long id){
        return ResponseEntity.ok(invoiceService.getInvoiceByRepairId(id));
    }

}
