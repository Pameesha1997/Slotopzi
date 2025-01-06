package com.sliit.slotopzi.dto.response;

import com.sliit.slotopzi.enums.RepairStatus;

public class OngoingRepairResponse {
    private String vehicleNumber;
    private String sectionName;
    private RepairStatus status;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }
}
