package dev.mohini.parkingalot.repository;

import dev.mohini.parkingalot.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepositoryH2Impl extends JpaRepository<ParkingTicket, Integer> {

}
