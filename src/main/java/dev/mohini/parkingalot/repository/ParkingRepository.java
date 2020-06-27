package dev.mohini.parkingalot.repository;

import dev.mohini.parkingalot.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingRepository  {

    boolean checkParkingAvailable();
    Optional<ParkingTicket> getSlotNumber(String number);
    int getCharges(int hours);
    void addTicket(ParkingTicket ticket);
    void removeTicket(String carNumber);
    List<ParkingTicket> getAllTickets();
}
