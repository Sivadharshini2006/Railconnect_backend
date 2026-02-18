package com.railconnect.tteservice.controller;

import com.railconnect.tteservice.entity.*;
import com.railconnect.tteservice.service.TteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tte")
@CrossOrigin
public class TteController {

    private final TteService tteService;

    public TteController(TteService tteService) {
        this.tteService = tteService;
    }

    @GetMapping("/waitlist")
    public List<Booking> getWaitlist(
            @RequestParam String trainId,
            @RequestParam String date) {

        return tteService.getWaitlist(trainId, date);
    }

    @PutMapping("/allot/{pnr}")
    public String allotSeat(@PathVariable String pnr) {

        tteService.allotSeat(pnr);
        return "Seat Allotted Successfully";
    }

    @PostMapping("/chart")
    public Chart generateChart(
            @RequestParam String trainId,
            @RequestParam String date) {

        return tteService.generateChart(trainId, date);
    }

    @GetMapping("/verify/{pnr}")
    public Booking verifyTicket(@PathVariable String pnr) {

        return tteService.verifyTicket(pnr);
    }
}
