package com.thoughtworks.tdd;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.service.Customer;
import com.thoughtworks.tdd.service.ParkingBoy;
import com.thoughtworks.tdd.service.ParkingLot;
import com.thoughtworks.tdd.service.SmartParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartParkingBoyTest {
    @Test
    void should_return_new_car_park_parking_lot2_when_parking_lot1_has_one_car() throws Exception {

        //Given
        Car newCar = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);

        //When
        smartParkingBoy.parkCar(new Car());
        Ticket ticket1 = smartParkingBoy.parkCar(newCar);

        //Then
        assertEquals(newCar, parkingLot2.fetchCar(ticket1));
    }

    @Test
    void should_return_car_when_give_ticket() throws Exception {

        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.addParkingLot(parkingLot);

        //When
        Ticket ticket = smartParkingBoy.parkCar(car);

        //Then
        assertEquals(car, smartParkingBoy.fetchCar(ticket));
    }

    @Test
    public void should_return_new_car_in_parking_lot2_when_parking_lot1_is_full() throws Exception {

        //Given
        Car eleventhCar = new Car();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.addParkingLot(new ParkingLot());
        smartParkingBoy.addParkingLot(new ParkingLot());
        Customer customer = new Customer();
        customer.setFetchCar(smartParkingBoy);
        customer.setPark(smartParkingBoy);
        customer.setCar(eleventhCar);

        //When
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            smartParkingBoy.parkCar(car);
        }
        Ticket ticket = smartParkingBoy.parkCar(eleventhCar);

        //Then
        assertEquals(customer.getCar(), smartParkingBoy.fetchCar(ticket));
    }
}
