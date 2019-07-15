package com.thoughtworks.tdd.service;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket parkCar(Car car) throws Exception {
        int maxEmptyPositionsNumber = 0;
        int parkingLotIndex = 0;
        List<ParkingLot> parkingLotList = super.getParkingLots();
//        ParkingLot parkingLot = parkingLotList.stream()
//                .filter(item -> !(item.isCapacityFull()))
//                .sorted(Comparator.comparing(ParkingLot::getPosition))
//                .collect(Collectors.toList()).get(0);
//        return parkingLot.parkCar(car);
        for (int i = 0; i < parkingLotList.size(); i++) {
            if (parkingLotList.get(i).getPosition() > maxEmptyPositionsNumber) {
                maxEmptyPositionsNumber = parkingLotList.get(i).getPosition();
                parkingLotIndex = i;
            }
        }
        return parkingLotList.get(parkingLotIndex).parkCar(car);
    }
}
