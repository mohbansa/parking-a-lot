package dev.mohini.parkingalot.repository;

import dev.mohini.parkingalot.model.ParkingTicket;

import java.util.*;

public class ParkingRepositoryImpl implements ParkingRepository {

    public HashMap<String, ParkingTicket> tickets=new HashMap<>();

    private static ParkingRepositoryImpl slotCapacityRepository = null;
    public static PriorityQueue<Integer> slots=null;
    public static int SLOTS_SIZE=0;
    private ParkingRepositoryImpl() {}
    public static ParkingRepositoryImpl createSlotCapacityRepository(int capacity) {
        if (slotCapacityRepository == null) {
            synchronized (ParkingRepositoryImpl.class) {
                if (slotCapacityRepository == null) {
                    slots=new PriorityQueue<>(capacity, Collections.reverseOrder());
                    slotCapacityRepository = new ParkingRepositoryImpl();
                    SLOTS_SIZE=capacity;
                }
            }
        }
        return slotCapacityRepository;
    }

    public boolean checkParkingAvailable()
    {
        int slotSize=ParkingRepositoryImpl.SLOTS_SIZE;
        int slotAvailable=ParkingRepositoryImpl.slots.size();
        if(slotSize>slotAvailable)
            return true;
        return false;

    }

    public Optional<ParkingTicket> getSlotNumber(String number)
    {
        Optional<ParkingTicket> optionals= Optional.ofNullable(tickets.get(number));

        return optionals;
    }

    public int getCharges(int hours)
    {
        if(hours<2)
            return 10;
        return 10+(hours-2)*10;
    }


    public void addTicket(ParkingTicket ticket)
    {
        tickets.put(ticket.getNumber(),ticket);
    }

    public void removeTicket(String carNumber)
    {
        tickets.remove(carNumber);
    }



    public List<ParkingTicket> getAllTickets()
    {
        return new ArrayList<ParkingTicket>(tickets.values());
    }


}
