package com.thoughtworks.tdd;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.service.Customer;
import com.thoughtworks.tdd.service.ParkingBoy;
import com.thoughtworks.tdd.service.ParkingLot;
import com.thoughtworks.tdd.service.SuperParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperParkingBoyTest {
    @Test
    public void should_return_new_car_park_parking_lot2_when_parking_lot1_has_3_car() throws Exception {

        //Given
        Car newCar = new Car();
        ParkingLot parkingLot1 = new ParkingLot(5);
        parkingLot1.parkCar(new Car());
        parkingLot1.parkCar(new Car());
        parkingLot1.parkCar(new Car());
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.parkCar(new Car());
        ParkingBoy superSmartParkingBoy = new SuperParkingBoy();
        superSmartParkingBoy.addParkingLot(parkingLot1);
        superSmartParkingBoy.addParkingLot(parkingLot2);

        //When
        Ticket ticket = superSmartParkingBoy.parkCar(newCar);

        //Then
        assertEquals(newCar, parkingLot2.fetchCar(ticket));
    }

    @Test
    public void should_return_car_when_give_ticket() throws Exception {

        //Given
        Car car = new Car();
        ParkingBoy superSmartParkingBoy = new SuperParkingBoy();
        superSmartParkingBoy.addParkingLot(new ParkingLot());

        //When
        Ticket ticket = superSmartParkingBoy.parkCar(car);

        //Then
        assertEquals(car, superSmartParkingBoy.fetchCar(ticket));
    }

    @Test
    public void should_return_new_car_park_parking_lot2_when_parking_lot1_is_full() throws Exception {

        //Given
        Car newCar = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperParkingBoy();
        superSmartParkingBoy.addParkingLot(parkingLot1);
        superSmartParkingBoy.addParkingLot(parkingLot2);
        Customer customer = new Customer();
        customer.setFetchCar(superSmartParkingBoy);
        customer.setPark(superSmartParkingBoy);
        customer.setCar(newCar);

        //When
        for (int i = 0; i < 10; i++) {
            superSmartParkingBoy.parkCar(new Car());
        }
        Ticket ticket = superSmartParkingBoy.parkCar(newCar);
        //Then
        assertEquals(customer.getCar(), superSmartParkingBoy.fetchCar(ticket));
    }
}
