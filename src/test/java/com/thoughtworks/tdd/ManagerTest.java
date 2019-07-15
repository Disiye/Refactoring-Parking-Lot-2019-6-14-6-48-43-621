package com.thoughtworks.tdd;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;
import com.thoughtworks.tdd.service.Customer;
import com.thoughtworks.tdd.service.Manager;
import com.thoughtworks.tdd.service.ParkingBoy;
import com.thoughtworks.tdd.service.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {

    @Test
    void should_return_car_when_manager_sent_parking_boy_to_park_car() throws Exception {

        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        Car car = new Car();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        Manager manager = new Manager(parkingLotList);

        //When
        manager.managerParkingBoy(parkingBoy, parkingLotList);
        manager.setPark(parkingBoy);
        Ticket ticket = manager.letParkingBoyPartCar(car);
        Car fetchCar = parkingBoy.fetchCar(ticket);

        //Then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_car_when_let_parking_boy_to_fetch_car() throws Exception {

        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        Manager manager = new Manager(parkingLotList);

        //When
        manager.managerParkingBoy(parkingBoy, parkingLotList);
        manager.setPark(parkingBoy);
        manager.setFetch(parkingBoy);
        Ticket ticket = manager.letParkingBoyPartCar(car);

        //Then
        assertEquals(car, manager.letParkingBoyFetchCar(ticket));
    }

    @Test
    void should_return_car_when_give_ticket_by_manager() throws Exception {

        //Given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        Manager manager = new Manager(parkingLotList);
        ParkingLot managedParkingLot = new ParkingLot();
        manager.addParkingLot(managedParkingLot);

        //When
        Ticket ticket = manager.parkCar(car);

        //Then
        assertEquals(car, manager.fetchCar(ticket));
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_manager_fetch_car_with_wrong_ticket() throws Exception {

        //Given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy();
        Manager manager = new Manager(parkingLotList);
        manager.managerParkingBoy(parkingBoy, parkingLotList);
        manager.setFetch(parkingBoy);
        Customer customer = new Customer();
        customer.setCar(car);
        customer.setPark(manager);
        customer.setFetchCar(manager);

        //When
        customer.parkMyCar();
        Ticket wrongTicket = new Ticket();
        customer.setParkingTicket(wrongTicket);

        //Then
        assertEquals("Unrecognized parking ticket.", customer.fetchMyCar());
    }

    @Test
    void should_return_Please_provide_your_parking_ticket_when_manger_fetch_car_without_ticket() throws Exception {
        //Given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy();
        Manager manager = new Manager(parkingLotList);
        manager.managerParkingBoy(parkingBoy, parkingLotList);
        manager.setFetch(parkingBoy);
        Customer customer = new Customer();
        customer.setCar(car);
        customer.setPark(manager);
        customer.setFetchCar(manager);

        //When
        customer.parkMyCar();
        customer.fetchMyCar();

        //Then
        assertEquals("Please provide your parking ticket.", customer.fetchMyCar());
    }

    @Test
    void should_return_not_enough_position_when_parking_lot_is_full() throws Exception {

        //Given
        Car eleventhCar = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy();
        Manager manager = new Manager(parkingLotList);
        manager.managerParkingBoy(parkingBoy, parkingLotList);
        manager.setPark(parkingBoy);
        Customer customer = new Customer();
        customer.setCar(eleventhCar);
        customer.setFetchCar(manager);
        customer.setPark(manager);

        //When
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.parkCar(car);
        }

        //Then
        assertEquals("Not enough position.", customer.parkMyCar());
    }
}
