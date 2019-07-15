package com.thoughtworks.tdd.service;

import com.thoughtworks.tdd.entity.Car;
import com.thoughtworks.tdd.entity.Ticket;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy {
    @Override
    public Ticket parkCar(Car car) throws Exception {
        double maxAvailablePositionRate = -1d;
        int parkingLotIndex = 0;
        List<ParkingLot> parkingLotList = super.getParkingLots();

        for (int i = 0; i < parkingLotList.size(); i++) {
            if (parkingLotList.get(i).getPositionRate() > maxAvailablePositionRate) {
                maxAvailablePositionRate = parkingLotList.get(i).getPositionRate();
                parkingLotIndex = i;
            }
        }
        return parkingLotList.get(parkingLotIndex).parkCar(car);

//        ParkingLot parkingLot = parkingLotList.stream()
//                .filter(item -> !(item.isCapacityFull()))
//                .sorted(Comparator.comparing(ParkingLot::getPositionRate))
//                .collect(Collectors.toList()).get(0);
//        return parkingLot.parkCar(car);
    }
}
