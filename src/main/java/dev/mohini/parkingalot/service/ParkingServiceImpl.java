package dev.mohini.parkingalot.service;

import dev.mohini.parkingalot.model.ParkingTicket;
import dev.mohini.parkingalot.repository.ParkingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ParkingServiceImpl implements ParkingService{

    @Autowired
    ParkingRepositoryImpl parkingRepositoryImpl;


    public String createCapacity(int capacity)
    {
        ParkingRepositoryImpl slotCapacityRepository=ParkingRepositoryImpl.createSlotCapacityRepository(capacity);
        if(slotCapacityRepository!=null)
            return String.format(("Created parking lot with %s slots"),capacity);
        return String.format("Parking lot is already slots with capacity:%s",ParkingRepositoryImpl.slots.size());

    }

    public String parkCar(String carNumber)
    {
        if(!parkingRepositoryImpl.checkParkingAvailable())
            return String.format("Sorry, parking lot is full");
        int slot= ParkingRepositoryImpl.slots.poll();

        ParkingTicket ticket=new ParkingTicket(slot, carNumber);
        parkingRepositoryImpl.addTicket(ticket);

        return String.format("Allocated slot number: %s", carNumber);
    }


    public String leave(String carNumber, int hours)
    {
        Optional<ParkingTicket> optionals= parkingRepositoryImpl.getSlotNumber(carNumber);
        if(optionals.isEmpty())
            return String.format(("Registration number%s not found"), carNumber);

        int slot=optionals.get().getSlot();
        int charges= parkingRepositoryImpl.getCharges(hours);
        parkingRepositoryImpl.removeTicket(carNumber);
        return String.format("Registration number %s with Slot Number %s is free with Charge %s",carNumber, slot, charges);

    }

    public List<ParkingTicket> status()
    {
        return parkingRepositoryImpl.getAllTickets();
    }



}
