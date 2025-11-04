package com.ocab.medilabopatient.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PatientDto {
    @NotBlank(message = "firstName may not be empty")
    private String firstName;
    @NotBlank(message = "lastName may not be empty")
    private String lastName;

    @NotNull(message = "birthday may not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Valid
    private Date birthday;

    @NotBlank(message = "gender may not be empty")
    private String gender;

    private String address;

    @Pattern(regexp="^(\\d{3}[- .]?){2}\\d{4}$", message = "phone number invalid") //phoneNumber with Whitespaces, Dots or Hyphens
    private String phoneNumber;

    @NotBlank(message="email may not be empty")
    @Email(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    public PatientDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
