package com.thoughtworks.tdd.repository;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;

public interface Park {
    public Ticket parkCar(Car car) throws Exception;
}
