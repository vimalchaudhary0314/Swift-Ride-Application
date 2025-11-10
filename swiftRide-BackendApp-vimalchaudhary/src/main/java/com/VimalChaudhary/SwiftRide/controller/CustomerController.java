package com.VimalChaudhary.SwiftRide.controller;

import com.VimalChaudhary.SwiftRide.dto.request.CustomerRequest;
import com.VimalChaudhary.SwiftRide.dto.response.ApiResponse;
import com.VimalChaudhary.SwiftRide.dto.response.CustomerResponse;
import com.VimalChaudhary.SwiftRide.model.Customer;
import com.VimalChaudhary.SwiftRide.model.enums.Gender;
import com.VimalChaudhary.SwiftRide.service.CustomerService;
import com.VimalChaudhary.SwiftRide.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customer) {
        CustomerResponse customerResponse = customerService.addCustomer(customer);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public List<CustomerResponse> getAllByGender(@PathVariable Gender gender) {
        return customerService.getAllByGender(gender);
    }

    // get all the people of particular gender and age
    @GetMapping("/gender-And-age")
    public List<CustomerResponse> getAllByGenderAndAge(@RequestParam Gender gender, @RequestParam int age) {
        return customerService.getAllByGenderAndAge(gender, age);
    }

    @GetMapping("/gender-And-age-greater-than")
    public List<CustomerResponse> getAllByGenderAndGreaterThanAge(@RequestParam Gender gender, @RequestParam int age) {
        return customerService.getAllByGenderAndGreaterThanAge(gender, age);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Integer customerId,
            @RequestBody CustomerRequest customerRequest) {
        try {
            Customer updated = customerService.updateCustomer(customerId, customerRequest);
            CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(updated);
            return ResponseEntity.ok(customerResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if customer not found
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer customerId) {
        ApiResponse response = customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
