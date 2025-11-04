package com.ocab.medilabopatient.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PatientDto {
    @NotBlank(message = "firstName may not be empty")
    private String firstName;
    @NotBlank(message = "lastName may not be empty")
    private String lastName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthday;
    @NotBlank(message = "gender may not be empty")
    private String gender;

    private String address;
    @Pattern(regexp="^(\\d{3}[- .]?){2}\\d{4}$") //phoneNumber with Whitespaces, Dots or Hyphens
    private String phoneNumber;

    @Email(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    public PatientDto() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
