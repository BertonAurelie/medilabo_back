package com.ocab.medilabopatient.model.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * DTO used to return patient information with age.
 */
public class PatientWithAgeDto {

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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
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
     */
    private String phoneNumber;

    /**
     * Patient email address.
     */
    private String email;

    /**
     * Patient age.
     */
    private int age;

    /**
     * Default constructor.
     */
    public PatientWithAgeDto() {
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
     * Get age.
     *
     * @return patient age
     */
    public int getAge() {
        return age;
    }

    /**
     * Set age.
     *
     * @param age patient age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get email address.
     *
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email address.
     *
     * @param email patient email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
}