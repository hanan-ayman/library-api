package com.vodafone.apis.libraryapi.publisher.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    private Integer publisherId;

    @Size(min = 1 , max = 50 , message = "Publisher name must be between 1 to 50")
    private String name;

    @Email(message = "please enter a valid email")
    private String emailId;

    @Pattern(regexp = "//d{3}-//d{3}-//d{3}" , message = "please enter a valid phone number format 111-222-333")
    private String phoneNumber;
}
