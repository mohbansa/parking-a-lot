package dev.mohini.parkingalot.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ParkingTicket {

    @Id
    private  int slot;
    private  String number;
    private int charges;

    public ParkingTicket(int slot, String number)
    {
        this.slot=slot;
        this.number=number;
    }

}
