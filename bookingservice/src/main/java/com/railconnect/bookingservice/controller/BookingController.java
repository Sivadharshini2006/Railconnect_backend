package com.railconnect.bookingservice.controller;
import com.railconnect.bookingservice.entity.Booking;
import com.railconnect.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/new")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        // The @RequestBody above captures name, age, gender from your JSON
        Booking savedBooking = bookingService.createNewBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }
    
    @GetMapping("/status/{pnr}")
    public ResponseEntity<Booking> checkStatus(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.getBookingByPnr(pnr));
    }
 
    
    
    @GetMapping("/user/{email}")
    public ResponseEntity<List<Booking>> userHistory(@PathVariable String email) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(email));
    }

    @PutMapping("/cancel/{pnr}")
    public ResponseEntity<Booking> cancel(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.cancelBooking(pnr));
    }
}