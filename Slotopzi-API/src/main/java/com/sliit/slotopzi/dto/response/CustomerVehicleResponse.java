package com.sliit.slotopzi.dto.response;

import com.sliit.slotopzi.model.Vehicle;

public class CustomerVehicleResponse {

    private Vehicle vehicle;
    private String nextService;

    public CustomerVehicleResponse(Vehicle vehicle, String nextService) {
        this.vehicle = vehicle;
        this.nextService = nextService;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getNextService() {
        return nextService;
    }

    public void setNextService(String nextService) {
        this.nextService = nextService;
    }
}
