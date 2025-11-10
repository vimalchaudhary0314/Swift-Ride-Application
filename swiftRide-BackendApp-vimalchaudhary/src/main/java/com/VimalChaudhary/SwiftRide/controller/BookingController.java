package com.VimalChaudhary.SwiftRide.controller;

import com.VimalChaudhary.SwiftRide.dto.request.BookingRequest;
import com.VimalChaudhary.SwiftRide.dto.response.ApiResponse;
import com.VimalChaudhary.SwiftRide.dto.response.BookingResponse;
import com.VimalChaudhary.SwiftRide.dto.response.BookingStatisticsResponse;
import com.VimalChaudhary.SwiftRide.dto.response.UpdateBookingResponse;
import com.VimalChaudhary.SwiftRide.model.enums.TripStatus;
import com.VimalChaudhary.SwiftRide.service.BookingService;
import com.VimalChaudhary.SwiftRide.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StatisticsService statisticsService;

    // which customer is booking
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<BookingResponse> bookRide(@RequestBody BookingRequest bookingRequest,
            @PathVariable Integer customerId) {
        try {
            BookingResponse bookingResponse = bookingService.bookRide(bookingRequest, customerId);
            return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    // rate booking
    @PatchMapping("/{bookingId}/rate")
    public ResponseEntity<Void> rateBooking(@PathVariable Integer bookingId, @RequestParam Double rating) {
        try {
            bookingService.rateBooking(bookingId, rating);
            return ResponseEntity.ok().build(); // Return 200 OK on success
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request on error
        }
    }

    @GetMapping("/{bookingId}/customer/{customerId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Integer bookingId, @PathVariable Integer customerId,
            @RequestParam Integer driverId) {
        BookingResponse response = bookingService.getBooking(bookingId, customerId, driverId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // driver can update tip status like cancel orr completed
    @PatchMapping("/{bookingId}/driver/{driverId}/update-status/{status}")
    public ResponseEntity<UpdateBookingResponse> updateBookingStatus(@PathVariable Integer driverId,
            @PathVariable Integer bookingId, @PathVariable TripStatus status) {
        UpdateBookingResponse updatedBooking = bookingService.updateBookingStatus(driverId, bookingId, status);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}/customer/{customerId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable Integer bookingId,
            @PathVariable Integer customerId) {
        ApiResponse response = bookingService.deleteBooking(bookingId, customerId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED); // Return 204 No Content on success
    }

    @GetMapping("/status")
    public ResponseEntity<List<BookingResponse>> getBookingsByStatus(@RequestParam TripStatus status) {
        List<BookingResponse> responseList = bookingService.getBookingsByStatus(status);
        if (responseList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no bookings found
        }
        return new ResponseEntity<>(responseList, HttpStatus.ACCEPTED); // Return 200 OK with the list of bookings
    }

    @GetMapping("/average-rating/{driverId}")
    public ResponseEntity<Double> findAverageRatingByDriverId(@PathVariable Integer driverId) {
        Double averageRating = bookingService.findAverageRatingByDriverId(driverId);
        if (averageRating != null) {
            return ResponseEntity.ok(averageRating); // Return 200 OK with the average rating
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no completed bookings found
        }
    }

    // statistics for booking
    @GetMapping("/statistics")
    public ResponseEntity<BookingStatisticsResponse> getBookingStatistics() {
        BookingStatisticsResponse bookingStatistics = statisticsService.getBookingStatistics();
        return new ResponseEntity<>(bookingStatistics, HttpStatus.OK);
    }

}
