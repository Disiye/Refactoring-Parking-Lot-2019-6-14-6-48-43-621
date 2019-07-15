package com.thoughtworks.tdd.service;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.repository.Fetch;
import com.thoughtworks.tdd.repository.Park;

import java.util.HashMap;

public class ParkingLot implements Park, Fetch {
    private HashMap<Ticket, Car> parkingTicketCar;
    private int capacity = 10;

    public ParkingLot() {
        parkingTicketCar = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        parkingTicketCar = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public Ticket parkCar(Car car) throws Exception {
        if (parkingTicketCar.size() == capacity) {
            throw new Exception("Not enough position.");
        }
        Ticket ticket = new Ticket();
        parkingTicketCar.put(ticket, car);
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception("Please provide your parking ticket.");
        }
        Car car = parkingTicketCar.remove(ticket);
        if (car == null) {
            throw new Exception("Unrecognized parking ticket.");
        }
        return car;
    }


    int getPosition() {
        return capacity - parkingTicketCar.size();
    }

    double getPositionRate() {
        return capacity - parkingTicketCar.size() / (capacity * 1.0);
    }

    boolean isCapacityFull() {
        return capacity > parkingTicketCar.size();
    }
}
