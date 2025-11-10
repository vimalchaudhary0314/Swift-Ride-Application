package com.VimalChaudhary.SwiftRide.transformer;

import com.VimalChaudhary.SwiftRide.dto.request.DriverRequest;
import com.VimalChaudhary.SwiftRide.dto.response.DriverResponse;
import com.VimalChaudhary.SwiftRide.model.Driver;

public class DriverTransformer {

    public static Driver requestDriverToDriver(DriverRequest driverRequest) {
        return Driver.builder().name(driverRequest.getName())
                .email(driverRequest.getEmail())
                .age(driverRequest.getAge())
                .phone(driverRequest.getPhone()).build();
    }

    public static DriverResponse driverToDriverResponse(Driver driver) {
        return DriverResponse.builder()
                .name(driver.getName())
                .age(driver.getAge())
                .email(driver.getEmail())
                .phone(driver.getPhone())
                .build();
    }
}
