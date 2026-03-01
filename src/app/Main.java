package app;

import model.Customer;
import mysql.MySqlCustomerDAO;
import mongo.MongoCustomerDAO;
/**
 * Project: Lab 3 - Java MySQL & MongoDB CRUD
 * Purpose Details: Runs CRUD operations for Customer in MySQL (and later MongoDB)
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 3/1/2026
 * Last Date Changed: 3/1/2026
 * Rev: 1.0
 */
public class Main {

    public static void main(String[] args) {

        MySqlCustomerDAO dao = new MySqlCustomerDAO();

        // ---------- CREATE ----------
        Customer c1 = new Customer("David", "Adeleye", "davidA@gmail.com", "215-111-1111", 0);
        Customer c2 = new Customer("Enzo", "Okoye", "enzoO@gmail.com", "215-222-2222", 10);
        Customer c3 = new Customer("Mitch", "Jones", "mitchj@gmail.com", "215-333-3333", 25);

        System.out.println("Insert c1: " + dao.createCustomer(c1));
        System.out.println("Insert c2: " + dao.createCustomer(c2));
        System.out.println("Insert c3: " + dao.createCustomer(c3));

        // ---------- READ ----------
        System.out.println("\n=== READ (after inserts) ===");
        System.out.println("Customer 1: " + dao.getCustomerById(1));
        System.out.println("Customer 2: " + dao.getCustomerById(2));
        System.out.println("Customer 3: " + dao.getCustomerById(3));

        // ---------- UPDATE ----------
        System.out.println("\n=== UPDATE ===");
        Customer updatedC1 = new Customer(
                "David",
                "Adeleye",
                "david.adeleye@gmail.com",
                "215-111-9999",
                50
        );
        System.out.println("Update customerID 1: " + dao.updateCustomer(1, updatedC1));

        // ---------- READ (after update) ----------
        System.out.println("\n=== READ (after update) ===");
        System.out.println("Customer 1: " + dao.getCustomerById(1));

        // ---------- DELETE ----------
        System.out.println("\n=== DELETE ===");
        System.out.println("Delete customerID 2: " + dao.deleteCustomer(2));
        System.out.println("Delete customerID 3: " + dao.deleteCustomer(3));

        // ---------- READ (after delete) ----------
        System.out.println("\n=== READ (after delete) ===");
        System.out.println("Customer 1: " + dao.getCustomerById(1));
        System.out.println("Customer 2: " + dao.getCustomerById(2));
        System.out.println("Customer 3: " + dao.getCustomerById(3));

// -------------------- MONGODB CRUD --------------------
        System.out.println("\n================= MONGODB CRUD =================");

        MongoCustomerDAO mongoDao = new MongoCustomerDAO();

// Clean collection so emails don’t conflict across runs
        mongoDao.deleteAll();

// CREATE
        Customer m1 = new Customer("David", "Adeleye", "davidA@gmail.com", "215-111-1111", 0);
        Customer m2 = new Customer("Enzo", "Okoye", "enzoO@gmail.com", "215-222-2222", 10);
        Customer m3 = new Customer("Mitch", "Jones", "mitchJ@gmail.com", "215-333-3333", 25);

        System.out.println("Mongo insert m1: " + mongoDao.createCustomer(m1));
        System.out.println("Mongo insert m2: " + mongoDao.createCustomer(m2));
        System.out.println("Mongo insert m3: " + mongoDao.createCustomer(m3));

// READ (after inserts)
        System.out.println("\n=== MONGO READ (after inserts) ===");
        System.out.println("m1: " + mongoDao.getCustomerByEmail("davidA@gmail.com"));
        System.out.println("m2: " + mongoDao.getCustomerByEmail("enzoO@gmail.com"));
        System.out.println("m3: " + mongoDao.getCustomerByEmail("mitchJ@gmail.com"));

// UPDATE (change m1 email + points)
        System.out.println("\n=== MONGO UPDATE ===");
        Customer updatedM1 = new Customer(
                "David",
                "Adeleye",
                "david.updated@gmail.com",
                "215-111-9999",
                50
        );

        System.out.println(
                "Mongo update m1: " +
                        mongoDao.updateCustomerByEmail("davidA@gmail.com", updatedM1)
        );

// READ (after update)
        System.out.println("\n=== MONGO READ (after update) ===");
        System.out.println("m1: " + mongoDao.getCustomerByEmail("david.updated@gmail.com"));

// DELETE
        System.out.println("\n=== MONGO DELETE ===");
        System.out.println("Mongo delete m2: " + mongoDao.deleteCustomerByEmail("enzoO@gmail.com"));
        System.out.println("Mongo delete m3: " + mongoDao.deleteCustomerByEmail("mitchJ@gmail.com"));

// READ (after delete)
        System.out.println("\n=== MONGO READ (after delete) ===");
        System.out.println("m1: " + mongoDao.getCustomerByEmail("david.updated@gmail.com"));
        System.out.println("m2: " + mongoDao.getCustomerByEmail("enzoO@gmail.com"));
        System.out.println("m3: " + mongoDao.getCustomerByEmail("mitchJ@gmail.com"));
    }
}