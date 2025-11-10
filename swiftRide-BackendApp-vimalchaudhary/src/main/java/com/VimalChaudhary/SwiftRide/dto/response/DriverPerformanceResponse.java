package com.VimalChaudhary.SwiftRide.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverPerformanceResponse {
    private String driverName;
    private long totalRides;
    private double averageRating;
}
