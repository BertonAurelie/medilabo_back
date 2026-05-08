package com.ocab.medilabopatient.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * DTO used to transfer patient request data.
 */
public class PatientDto {

    /**
     * Patient first name.
     */
    @NotBlank(message = "firstName may not be empty")
    private String firstName;

    /**
     * Patient last name.
     */
    @NotBlank(message = "lastName may not be empty")
    private String lastName;

    /**
     * Patient birthday.
     */
    @NotNull(message = "birthday may not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Valid
    private Date birthday;

    /**
     * Patient gender.
     */
    @NotBlank(message = "gender may not be empty")
    private String gender;

    /**
     * Patient address.
     */
    private String address;

    /**
     * Patient phone number.
     * Accepts spaces, dots, or hyphens.
     */
    @Pattern(
            regexp = "^(\\d{3}[- .]?){2}\\d{4}$",
            message = "phone number invalid"
    )
    private String phoneNumber;

    /**
     * Patient email address.
     */
    @NotBlank(message = "email may not be empty")
    @Email(
            regexp = ".+@.+\\..+",
            message = "Please provide a valid email address"
    )
    private String email;

    /**
     * Patient password.
     */
    private String password;

    /**
     * Default constructor.
     */
    public PatientDto() {
    }

    /**
     * Get first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name.
     *
     * @param firstName patient first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name.
     *
     * @param lastName patient last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get birthday.
     *
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set birthday.
     *
     * @param birthday patient birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get gender.
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set gender.
     *
     * @param gender patient gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address.
     *
     * @param address patient address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get phone number.
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set phone number.
     *
     * @param phoneNumber patient phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get email.
     *
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email.
     *
     * @param email patient email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.
     *
     * @param password patient password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}