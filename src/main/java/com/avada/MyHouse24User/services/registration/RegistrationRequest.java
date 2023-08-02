package com.avada.MyHouse24User.services.registration;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String fathersName;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean agreeToToS;

}