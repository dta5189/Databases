package mysql;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: Lab 3 - Java MySQL & MongoDB CRUD
 * Purpose Details: CRUD operations for Customer in MySQL
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 3/1/2026
 * Last Date Changed: 3/1/2026
 * Rev: 1.0
 */
public class MySqlCustomerDAO {

    /**
     * Inserts a Customer into MySQL database.
     *
     * @param customer the customer to insert
     * @return true if insert succeeded, false otherwise
     */
    public boolean createCustomer(Customer customer) {
        String sql =
                "INSERT INTO Customer (firstName, lastName, email, phone, loyaltyPoints) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MySqlConnectionRepo.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getLoyaltyPoints());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("MySQL insert failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads a Customer from MySQL by customerID.
     *
     * @param customerID the id to search for
     * @return Customer if found, otherwise null
     */
    public Customer getCustomerById(int customerID) {
        String sql =
                "SELECT customerID, firstName, lastName, email, phone, loyaltyPoints, createdAt " +
                        "FROM Customer WHERE customerID = ?";

        try (Connection conn = MySqlConnectionRepo.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                Customer c = new Customer();
                // You likely DON'T have setters for customerID/createdAt (which is fine).
                // For the lab, you can either:
                // 1) add setters for customerID/createdAt (simpler), OR
                // 2) ignore setting them in the object (still works for printing basics)

                c.setFirstName(rs.getString("firstName"));
                c.setLastName(rs.getString("lastName"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setLoyaltyPoints(rs.getInt("loyaltyPoints"));

                return c;
            }

        } catch (SQLException e) {
            System.out.println("MySQL read failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates an existing customer in MySQL.
     *
     * @param customerID the id of the customer to update
     * @param updated customer object with new values
     * @return true if update succeeded, false otherwise
     */
    public boolean updateCustomer(int customerID, Customer updated) {
        String sql =
                "UPDATE Customer SET firstName = ?, lastName = ?, email = ?, phone = ?, loyaltyPoints = ? " +
                        "WHERE customerID = ?";

        try (Connection conn = MySqlConnectionRepo.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, updated.getFirstName());
            stmt.setString(2, updated.getLastName());
            stmt.setString(3, updated.getEmail());
            stmt.setString(4, updated.getPhone());
            stmt.setInt(5, updated.getLoyaltyPoints());
            stmt.setInt(6, customerID);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("MySQL update failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a customer in MySQL by customerID.
     *
     * @param customerID the id of the customer to delete
     * @return true if delete succeeded, false otherwise
     */
    public boolean deleteCustomer(int customerID) {
        String sql = "DELETE FROM Customer WHERE customerID = ?";

        try (Connection conn = MySqlConnectionRepo.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("MySQL delete failed: " + e.getMessage());
            return false;
        }
    }
    
}