package com.sliit.slotopzi.service;


import com.sliit.slotopzi.dto.response.InvoiceByRepairId;
import com.sliit.slotopzi.dto.response.MaterialList;
import com.sliit.slotopzi.dto.response.ServiceList;
import com.sliit.slotopzi.enums.ItemRequestStatus;
import com.sliit.slotopzi.enums.ServiceEntryStatus;
import com.sliit.slotopzi.model.Repair;
import com.sliit.slotopzi.model.ServiceEntry;
import com.sliit.slotopzi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    public InvoiceByRepairId getInvoiceByRepairId(long id) {
        InvoiceByRepairId response = new InvoiceByRepairId();
        Repair repair = repairRepository.findByRepairId(id);
        List<ServiceEntry> services = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIs(id, ServiceEntryStatus.COMPLETED);
        List<ServiceList> serviceList = new ArrayList<>();
        if(services.size()!=0) {
            for (ServiceEntry serviceEntry : services) {
                System.out.println(serviceEntry.getEstimatedTime()/60);
                serviceList.add(new ServiceList(serviceEntry.getSubCategory().getSubCatName() + (serviceEntry.getDescription() == null ? " " : " (" + serviceEntry.getDescription() + ") "), new BigDecimal((double)serviceEntry.getEstimatedTime()/60).multiply(new BigDecimal(1000))));
            }
        }
        List<MaterialList> materialList=new ArrayList<>();
        response.setCustomerName(repair.getVehicle().getCustomer().getFirstName()+' '+repair.getVehicle().getCustomer().getLastName());
        response.setAddress(repair.getVehicle().getCustomer().getUserData().getAddress());
        response.setCity(repair.getVehicle().getCustomer().getUserData().getCity());
        response.setMobileNumber(repair.getVehicle().getCustomer().getUserData().getContactNo());
        response.setInvoiceDate(repair.getInvoices().getInvoiceDate().toString());
        response.setAmount(repair.getInvoices().getAmount());
        response.setInvoiceNumber(repair.getInvoices().getInvoiceId());
        response.setPaymentType(repair.getPaymentType().toString());
        response.setVehicleNumber(repair.getVehicle().getVehicleNumber());
        response.setModel(repair.getVehicle().getModel());
        response.setServices(serviceList);
        response.setMaterials(materialList);
        return response;
    }
}

