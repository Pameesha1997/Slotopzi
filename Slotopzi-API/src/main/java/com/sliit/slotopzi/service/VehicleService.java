package com.sliit.slotopzi.service;

import com.sliit.slotopzi.dto.response.CustomerVehicleResponse;
import com.sliit.slotopzi.dto.response.ExpenseResponse;
import com.sliit.slotopzi.dto.response.OngoingServicesResponse;
import com.sliit.slotopzi.dto.response.VehicleServices;
import com.sliit.slotopzi.enums.RepairStatus;
import com.sliit.slotopzi.enums.RepairType;
import com.sliit.slotopzi.enums.ServiceEntryStatus;
import com.sliit.slotopzi.model.Repair;
import com.sliit.slotopzi.model.ServiceEntry;
import com.sliit.slotopzi.model.Vehicle;
import com.sliit.slotopzi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RepairRepository repairRepository;

    @Autowired
    ServiceEntryRepository serviceEntryRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    public List<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findAllByCustomer_CustomerId(id);
    }

    public List<Vehicle> getVehicleByEmail(String email) {
        return vehicleRepository.findAllByCustomer_UserData_Email(email);
    }

    public ExpenseResponse getSummaryByCustomer(long id) {
        long cusId = customerRepository.findByUserData_Id(id).getCustomerId();
        float totalSpent = customerRepository.customerSummary(cusId);
        float totalSpentMonth = customerRepository.customerSummary2(cusId);
        float  repairsPerMonth = customerRepository.customerSummary3(cusId);
        int  activeRepairs = customerRepository.customerSummary4(cusId);
        return new ExpenseResponse(totalSpent,totalSpentMonth,totalSpent/12,repairsPerMonth,activeRepairs);
    }

    public List<CustomerVehicleResponse> getVehicleByUserId(long id) {
        List <CustomerVehicleResponse> response = new ArrayList<CustomerVehicleResponse>();
            List <Vehicle> vlist=  vehicleRepository.findAllByCustomer_UserData_Id(id);
            for(Vehicle vehicle : vlist){
                int millage =0;
                List<Repair> repairs = repairRepository.findAllByVehicle_VehicleIdAndRepairTypeOrderByRepairAddedDateDesc(vehicle.getVehicleId(), RepairType.RSERVICE);
                if(repairs.size()==0){
                    millage =-1;
                }else{
                    millage = repairs.get(0).getMillage();
                }
                response.add(new CustomerVehicleResponse(vehicle,(millage==-1?"- -":String.valueOf(millage+2500))));
            }
        return response;
    }

    public Vehicle getDetailsByVid(long id) {
        return vehicleRepository.findByVehicleId(id);
    }

    public ExpenseResponse getExpensesByVid(long id) {
        float totalSpent = vehicleRepository.vehicleSummary(id);
        float totalSpentMonth = vehicleRepository.vehicleSummary2(id);
        float  repairsPerMonth = vehicleRepository.vehicleSummary3(id);
        int  activeRepairs = vehicleRepository.vehicleSummary4(id);
        return new ExpenseResponse(totalSpent,totalSpentMonth,totalSpent/12,repairsPerMonth,activeRepairs);
    }

    public List <VehicleServices> getCompletedRepairsByVid(long id) {
        List <Repair> repairs = repairRepository.findAllByStatusAndVehicleVehicleId(RepairStatus.COMPLETED,id);
        List <VehicleServices> output = new ArrayList<>();
        for (Repair repair : repairs) {
            VehicleServices n= new VehicleServices(repair.getRepairId(),repair.getRepairCompletedDate().toString());
            output.add(n);
        }
        return output;
    }

    public List<OngoingServicesResponse> getOngoingServices(long id) {
        int time =0;
        List <Repair> repairs=repairRepository.findAllByStatusAndVehicle_Customer_UserData_id(RepairStatus.ONGOING,id);
        List<OngoingServicesResponse> response = new ArrayList<>();

        for(Repair repair:repairs){
            long repairid= repair.getRepairId();
            String vehicleNumber = repair.getVehicle().getVehicleNumber();

            List<ServiceEntry> entriesQ = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIsOrServiceEntryStatusIs(repairid, ServiceEntryStatus.ADDED, ServiceEntryStatus.PENDING);
            List<ServiceEntry> entriesO = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIs(repairid, ServiceEntryStatus.ONGOING);
            List<ServiceEntry> entriesC = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIs(repairid,ServiceEntryStatus.COMPLETED);

            List<String> q=new ArrayList<>();
            List<String> o=new ArrayList<>();
            List<String> c=new ArrayList<>();

            for(ServiceEntry entry:entriesQ){q.add(entry.getSubCategory().getSubCatName());time+=entry.getEstimatedTime();}
            for(ServiceEntry entry:entriesO){o.add(entry.getSubCategory().getSubCatName());time+=entry.getEstimatedTime();}
            for(ServiceEntry entry:entriesC){c.add(entry.getSubCategory().getSubCatName());}

            OngoingServicesResponse n = new OngoingServicesResponse(repairid,vehicleNumber,time,q,o,c);
            response.add(n);
        }
        return response;
    }

    public String getDocId(long id) {
        return repairRepository.findByRepairId(id).getFbDocId();
    }

    public List<Vehicle> getCusVehicleByUserId(long id) {
        return vehicleRepository.findAllByCustomer_UserData_Id(id);
    }

    public List<Object> getcharts(long id) {
//        System.out.println(invoiceRepository.getsummonth(id));
        return invoiceRepository.getsummonth(customerRepository.findByUserData_Id(id).getUserData().getId());
    }

    public List<Object> getcharts2(long id) {
        return invoiceRepository.getsummonth2(customerRepository.findByUserData_Id(id).getUserData().getId());
    }
}
