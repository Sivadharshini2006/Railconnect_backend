package com.railconnect.tteservice.controller;

import com.railconnect.tteservice.entity.*;
import com.railconnect.tteservice.service.TteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/tte")
@CrossOrigin(origins = "http://localhost:5173") // Vite
public class TteController {

    private final TteService tteService;

    public TteController(TteService tteService) {
        this.tteService = tteService;
    }

    @GetMapping("/waitlist")
    public List<Booking> getWaitlist(@RequestParam String trainName,
                                    @RequestParam String journeyDate) {
        return tteService.getWaitlist(trainName, journeyDate);
    }

    @PostMapping("/chart")
    public Chart generateChart(@RequestParam String trainName,
                              @RequestParam String journeyDate) {
        return tteService.generateChart(trainName, journeyDate);
    }

    @PutMapping("/allot/{pnr}")
    public String allotSeat(@PathVariable String pnr) {
        tteService.allotSeat(pnr);
        return "Seat Allotted Successfully";
    }

    @GetMapping("/verify/{pnr}")
    public Booking verifyTicket(@PathVariable String pnr) {
        return tteService.verifyTicket(pnr);
    }
}