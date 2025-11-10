package com.VimalChaudhary.SwiftRide.transformer;

import com.VimalChaudhary.SwiftRide.dto.request.CustomerRequest;
import com.VimalChaudhary.SwiftRide.dto.response.CustomerResponse;
import com.VimalChaudhary.SwiftRide.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .customerAge(customerRequest.getCustomerAge())
                .customerName(customerRequest.getCustomerName())
                .customerPhone(customerRequest.getCustomerPhone())
                .emailId(customerRequest.getEmailId())
                .gender(customerRequest.getGender()).build();

        return customer;
    }

    public static CustomerResponse customerToCustomerResponse(Customer saved) {

        CustomerResponse customerResponse = CustomerResponse.builder()
                .customerAge(saved.getCustomerAge())
                .customerName(saved.getCustomerName())
                .customerPhone(saved.getCustomerPhone())
                .emailId(saved.getEmailId()).build();
        return customerResponse;
    }

}
