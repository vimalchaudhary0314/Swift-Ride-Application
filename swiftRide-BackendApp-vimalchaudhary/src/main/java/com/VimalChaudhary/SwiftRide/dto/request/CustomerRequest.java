package com.VimalChaudhary.SwiftRide.dto.request;

import com.VimalChaudhary.SwiftRide.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {

    private String customerName;
    private int CustomerAge;
    private String emailId;
    private String customerPhone;
    private Gender gender;

}
