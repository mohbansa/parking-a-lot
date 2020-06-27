package dev.mohini.parkingalot.controller;


import dev.mohini.parkingalot.model.ParkingTicket;
import dev.mohini.parkingalot.service.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parkingalot")
public class ParkingController {


    @Autowired
    ParkingServiceImpl parkingService;

    @GetMapping("/park/{number}")
    public String parkCar(@PathVariable(name="number") String number)
    {


        String message =parkingService.parkCar(number);
        return message;

    }

    @GetMapping("/leave/{number}/{hours}")
    public String leaveCar(@PathVariable(name="number") String number,
                           @PathVariable(name="hours")  int hours)
    {

        String message =parkingService.leave(number, hours);
        return message;
    }

    @GetMapping("/create/{capacity}")
    public String createCapacity(@PathVariable(name="capacity") int capacity)
    {
        String message=parkingService.createCapacity(capacity);
        return message;
    }

    @GetMapping("/status")
    public List<ParkingTicket> getStatus()
    {
        return parkingService.status();
    }
}
