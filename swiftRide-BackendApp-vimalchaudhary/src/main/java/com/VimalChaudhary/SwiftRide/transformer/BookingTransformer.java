package com.VimalChaudhary.SwiftRide.transformer;

import com.VimalChaudhary.SwiftRide.dto.request.BookingRequest;
import com.VimalChaudhary.SwiftRide.dto.response.BookingResponse;
import com.VimalChaudhary.SwiftRide.dto.response.VehicleResponse;
import com.VimalChaudhary.SwiftRide.model.Booking;
import com.VimalChaudhary.SwiftRide.model.Customer;
import com.VimalChaudhary.SwiftRide.model.Driver;
import com.VimalChaudhary.SwiftRide.model.Vehicle;
import com.VimalChaudhary.SwiftRide.model.enums.TripStatus;

public class BookingTransformer {
    public static Booking bookingRequestToBooking(BookingRequest bookingRequest, double perKmCharge) {
        return Booking.builder()
                .pickUpLocation(bookingRequest.getPickUpLocation())
                .dropOffLocation(bookingRequest.getDropOffLocation())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.REQUESTED)
                .rating(0.0)
                .billAmount(bookingRequest.getTripDistanceInKm() * perKmCharge).build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking, Customer customer, Vehicle vehicle,
            Driver driver) {
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setDriver(DriverTransformer.driverToDriverResponse(driver));
        return BookingResponse.builder()
                .pickUpLocation(booking.getPickUpLocation())
                .dropOffLocation(booking.getDropOffLocation())
                .billAmount(booking.getBillAmount())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customer(CustomerTransformer.customerToCustomerResponse(customer))
                .vehicle(VehicleTransformer.vehicleToVehicleResponseWithDriver(vehicle, driver))
                .build();
    }
}
