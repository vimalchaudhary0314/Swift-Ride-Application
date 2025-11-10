package com.VimalChaudhary.SwiftRide.transformer;

import com.VimalChaudhary.SwiftRide.dto.request.UpdateBookingRequest;
import com.VimalChaudhary.SwiftRide.dto.response.UpdateBookingResponse;
import com.VimalChaudhary.SwiftRide.model.Booking;
import com.VimalChaudhary.SwiftRide.model.enums.TripStatus;

import java.util.Date;

public class UpBookTransformer {
    public static Booking upBookingReqToBooking(Booking booking, UpdateBookingRequest bookingRequest) {
        booking.setLastUpdateAt(new Date());
        booking.setTripStatus(TripStatus.COMPLETED);
        // booking.setTripStatus(bookingRequest.getTripStatus());
        return booking;
    }

    public static UpdateBookingResponse bookingToUpdateBookingResponse(Booking updatedBooking) {
        UpdateBookingResponse bookingResponse = new UpdateBookingResponse();
        bookingResponse.setBookedAt(updatedBooking.getBookedAt());
        bookingResponse.setLastUpdateAt(updatedBooking.getLastUpdateAt());
        bookingResponse.setRating(updatedBooking.getRating());
        bookingResponse.setTripStatus(updatedBooking.getTripStatus());
        bookingResponse.setBillAmount(updatedBooking.getBillAmount());
        bookingResponse.setDropOffLocation(updatedBooking.getDropOffLocation());
        bookingResponse.setPickUpLocation(updatedBooking.getPickUpLocation());
        bookingResponse.setTripDistanceInKm(updatedBooking.getTripDistanceInKm());
        return bookingResponse;
    }
}
