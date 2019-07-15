package com.thoughtworks.tdd;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.service.Customer;
import com.thoughtworks.tdd.service.ParkingBoy;
import com.thoughtworks.tdd.service.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingBoyTest {
    @Test
    void should_return_car_when_give_ticket() throws Exception {

        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);

        //When
        Ticket ticket = parkingBoy.parkCar(car);

        //Then
        assertEquals(car, parkingBoy.fetchCar(ticket));
    }

    @Test
    void should_return_cars_when_give_tickets() throws Exception {

        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);

        //When
        Car fetchCar1 = parkingBoy.fetchCar(parkingBoy.parkCar(car1));
        Car fetchCar2 = parkingBoy.fetchCar(parkingBoy.parkCar(car2));

        //Then
        assertEquals(car1, fetchCar1);
        assertEquals(car2, fetchCar2);
    }

    @Test
    void should_return_exception_when_take_wrong_ticket() throws Exception {

        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);

        //When
//        Ticket ticket = parkingBoy.parkCar(car);
        Ticket ticket = new Ticket();

        //Then
        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.fetchCar(ticket);
        });
    }

    @Test
    void should_fetch_no_car_when_not_give_ticket() throws Exception {

        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);

        //When
        parkingBoy.parkCar(car);

        //Then
        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.fetchCar(null);
        });
    }

    @Test
    void should_return_exception_when_parking_lot_has_no_position() throws Exception {

        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);

        //When
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.parkCar(car);
        }
        //Then
        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.parkCar(new Car());
        });
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_give_wrong_ticket() throws Exception {

        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setPark(parkingBoy);

        //When
        Ticket wrongTicket = new Ticket();
        customer.setParkingTicket(wrongTicket);

        //Then
        assertEquals("Unrecognized parking ticket.", customer.fetchMyCar());
    }

    @Test
    void should_return_please_provide_your_parking_ticket_when_null_ticket() throws Exception {
        //Given

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setPark(parkingBoy);

        //When
        String message = customer.fetchMyCar();

        //Then
        assertEquals("Please provide your parking ticket.", message);
    }

    @Test
    void should_return_not_enough_position_when_parking_lot_is_full() throws Exception {

        //Given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setPark(parkingBoy);
        customer.setCar(eleventhCar);

        //When
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.parkCar(car);
        }

        //Then
        assertEquals("Not enough position.", customer.parkMyCar());
    }

    @Test
    void should_return_new_car_in_no2_parking_lot_when_no1_parking_lot_is_full() throws Exception {

        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot());
        parkingBoy.addParkingLot(new ParkingLot());
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setPark(parkingBoy);
        customer.setCar(new Car());

        //When
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.parkCar(car);
        }
        Ticket ticket = parkingBoy.parkCar(new Car());

        //Then
        assertEquals(customer.getCar(), parkingBoy.fetchCar(ticket));
    }
}
