package com.thoughtworks.tdd.service;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.repository.Fetch;
import com.thoughtworks.tdd.repository.Park;

public class Customer {
    private Park park;
    private Fetch fetch;
    private Car car;
    private Ticket parkingTicket;

    public void setPark(Park park) {
        this.park = park;
    }

    public void setFetchCar(Fetch fetch) {
        this.fetch = fetch;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Ticket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(Ticket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public String fetchMyCar() {
        try {
            Car car = fetch.fetchCar(parkingTicket);
            setCar(car);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public String parkMyCar() {
        try {
            Ticket ticket = park.parkCar(car);
            setParkingTicket(ticket);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }
}
