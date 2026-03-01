package model;

import java.time.LocalDateTime;

/**
 * Project: Lab 3 - Java MySQL & MongoDB CRUD
 * Purpose Details: Customer model for retail shopping store database operations
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 3/1/2026
 * Last Date Changed: 3/1/2026
 * Rev: 1.0
 */
public class Customer {

    /**
     * Unique identifier for the customer.
     */
    private int customerID;

    /**
     * Customer first name.
     */
    private String firstName;

    /**
     * Customer last name.
     */
    private String lastName;

    /**
     * Customer email address.
     */
    private String email;

    /**
     * Customer phone number.
     */
    private String phone;

    /**
     * Number of loyalty reward points the customer has earned.
     */
    private int loyaltyPoints;

    /**
     * The date and time the customer record was created.
     */
    private LocalDateTime createdAt;

    /**
     * No-argument constructor required for database frameworks.
     */
    public Customer() {
    }

    /**
     * Constructs a Customer with essential details.
     */
    public Customer(String firstName, String lastName, String email,
                    String phone, int loyaltyPoints) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.loyaltyPoints = loyaltyPoints;
    }

    // -------- Getters --------

    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // -------- Setters (only for changeable fields) --------

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints >= 0) {
            this.loyaltyPoints = loyaltyPoints;
        }
    }

    /**
     * Adds loyalty points to the customer's account.
     * @param points number of points to add
     */
    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}


