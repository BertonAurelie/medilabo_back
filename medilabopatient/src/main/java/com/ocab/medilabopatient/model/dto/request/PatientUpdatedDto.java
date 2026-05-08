package com.ocab.medilabopatient.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * DTO used to update patient information.
 */
public class PatientUpdatedDto {

    /**
     * Patient identifier.
     */
    private int id;

    /**
     * Patient first name.
     */
    private String firstName;

    /**
     * Patient last name.
     */
    private String lastName;

    /**
     * Patient birthday.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * Patient gender.
     */
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
    @Email(
            regexp = ".+@.+\\..+",
            message = "Please provide a valid email address"
    )
    private String email;

    /**
     * Default constructor.
     */
    public PatientUpdatedDto() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id patient id
     * @param firstName patient first name
     * @param lastName patient last name
     * @param birthday patient birthday
     * @param gender patient gender
     * @param address patient address
     * @param phoneNumber patient phone number
     * @param email patient email
     */
    public PatientUpdatedDto(
            int id,
            String firstName,
            String lastName,
            Date birthday,
            String gender,
            String address,
            String phoneNumber,
            String email
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Get patient id.
     *
     * @return patient id
     */
    public int getId() {
        return id;
    }

    /**
     * Set patient id.
     *
     * @param id patient id
     */
    public void setId(int id) {
        this.id = id;
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
}