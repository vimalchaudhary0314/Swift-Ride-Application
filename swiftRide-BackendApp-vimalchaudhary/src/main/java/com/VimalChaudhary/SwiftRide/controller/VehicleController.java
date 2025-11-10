
package com.VimalChaudhary.SwiftRide.controller;

import com.VimalChaudhary.SwiftRide.dto.request.VehicleRequest;
import com.VimalChaudhary.SwiftRide.dto.response.ApiResponse;
import com.VimalChaudhary.SwiftRide.dto.response.VehicleResponse;
import com.VimalChaudhary.SwiftRide.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Register vehicle
    @PostMapping("/register")
    public ResponseEntity<VehicleResponse> registerVehicle(@RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse vehicleResponse = vehicleService.registerVehicle(vehicleRequest);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.CREATED);
    }

    // Get vehicle by ID
    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Integer vehicleId) {
        VehicleResponse vehicleResponse = vehicleService.getVehicleById(vehicleId);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }

    // Get all vehicles
    @GetMapping("/")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> vehicleList = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    // Update vehicle
    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Integer vehicleId,
            @RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse updatedVehicle = vehicleService.updateVehicle(vehicleId, vehicleRequest);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    // Delete vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse> deleteVehicle(@PathVariable Integer vehicleId) {
        ApiResponse response = vehicleService.deleteVehicleById(vehicleId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
