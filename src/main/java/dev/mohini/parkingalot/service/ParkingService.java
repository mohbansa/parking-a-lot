package dev.mohini.parkingalot.service;

import dev.mohini.parkingalot.model.ParkingTicket;

import java.util.List;

public interface ParkingService {
    String createCapacity(int capacity);
    String parkCar(String carNumber);
    String leave(String carNumber, int hours);
    List<ParkingTicket> status();

}
