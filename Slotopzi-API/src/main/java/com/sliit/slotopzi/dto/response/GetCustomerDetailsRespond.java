package com.sliit.slotopzi.dto.response;

import java.util.List;

public class GetCustomerDetailsRespond {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private List<VehicleListResponse> vehicleList;

    public List<VehicleListResponse> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<VehicleListResponse> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
