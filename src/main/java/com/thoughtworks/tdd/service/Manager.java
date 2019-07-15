package com.thoughtworks.tdd.service;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.repository.Fetch;
import com.thoughtworks.tdd.repository.Park;

import java.util.HashMap;
import java.util.List;

public class Manager extends ParkingBoy {
    private HashMap<ParkingBoy, List<ParkingLot>> managementList;
    private Park park;
    private Fetch fetch;

    public Manager(List<ParkingLot> prividedParkingLots) {
        this.managementList = new HashMap<>();
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public void setFetch(Fetch fetch) {
        this.fetch = fetch;
    }

    @Override
    public Ticket parkCar(Car car) throws Exception {
        return (park == null) ? super.parkCar(car) : letParkingBoyPartCar(car);
    }

    @Override
    public Car fetchCar(Ticket ticket) throws Exception {
        return (fetch == null) ? super.fetchCar(ticket) : letParkingBoyFetchCar(ticket);
    }

    public void managerParkingBoy(ParkingBoy parkingBoy, List<ParkingLot> parkingLots) {
        parkingBoy.setParkingLots(parkingLots);
        this.managementList.put(parkingBoy, parkingLots);
    }

    public Ticket letParkingBoyPartCar(Car car) throws Exception {
        return park.parkCar(car);
    }

    public Car letParkingBoyFetchCar(Ticket ticket) throws Exception {
        return fetch.fetchCar(ticket);
    }

}
