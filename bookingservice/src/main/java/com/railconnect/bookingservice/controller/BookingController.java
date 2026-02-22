package com.railconnect.bookingservice.controller;
import com.railconnect.bookingservice.entity.Booking;
import com.railconnect.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/booking")

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
    @GetMapping("/pnr/{pnr}")
    public ResponseEntity<Booking> getByPnr(@PathVariable String pnr) {
        Booking b = bookingService.getBookingByPnr(pnr.trim());
        return ResponseEntity.ok(b);
    }
    
    
    @GetMapping("/user/{email}")
    public List<Booking> byUser(@PathVariable String email){
        return bookingService.getBookingsByUser(email);
    }

    @PutMapping("/cancel/{pnr}")
    public ResponseEntity<Booking> cancel(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.cancelBooking(pnr));
    }
    @PutMapping("/confirm/{pnr}")
    public ResponseEntity<Booking> confirm(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.confirmBooking(pnr));
    }
    
 
}