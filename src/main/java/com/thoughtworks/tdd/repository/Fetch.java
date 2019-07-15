package com.thoughtworks.tdd.repository;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;

public interface Fetch {
    public Car fetchCar(Ticket ticket) throws Exception;
}
