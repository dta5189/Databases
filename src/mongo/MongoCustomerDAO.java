package mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import model.Customer;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Project: Lab 3 - Java MySQL & MongoDB CRUD
 * Purpose Details: CRUD operations for Customer in MongoDB
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 3/1/2026
 * Last Date Changed: 3/1/2026
 * Rev: 1.0
 */
public class MongoCustomerDAO {

    /** Database name in MongoDB */
    private static final String DB_NAME = "retail_store";

    /** Collection name in MongoDB */
    private static final String COLLECTION_NAME = "customers";

    /**
     * Gets the customers collection.
     */
    private MongoCollection<Document> getCollection(MongoClient client) {
        MongoDatabase db = client.getDatabase(DB_NAME);
        return db.getCollection(COLLECTION_NAME);
    }

    /**
     * Converts Customer -> Mongo Document.
     */
    private Document toDocument(Customer c) {
        return new Document("firstName", c.getFirstName())
                .append("lastName", c.getLastName())
                .append("email", c.getEmail())
                .append("phone", c.getPhone())
                .append("loyaltyPoints", c.getLoyaltyPoints());
    }

    /**
     * Converts Mongo Document -> Customer.
     */
    private Customer toCustomer(Document doc) {
        return new Customer(
                doc.getString("firstName"),
                doc.getString("lastName"),
                doc.getString("email"),
                doc.getString("phone"),
                doc.getInteger("loyaltyPoints", 0)
        );
    }

    // CREATE
    public boolean createCustomer(Customer customer) {
        try (MongoClient client = MongoConnectionRepo.getClient()) {
            MongoCollection<Document> col = getCollection(client);

            // Prevent duplicates by email (simple check)
            Document existing = col.find(eq("email", customer.getEmail())).first();
            if (existing != null) return false;

            col.insertOne(toDocument(customer));
            return true;
        } catch (Exception e) {
            System.out.println("Mongo insert failed: " + e.getMessage());
            return false;
        }
    }

    // READ
    public Customer getCustomerByEmail(String email) {
        try (MongoClient client = MongoConnectionRepo.getClient()) {
            MongoCollection<Document> col = getCollection(client);
            Document doc = col.find(eq("email", email)).first();
            if (doc == null) return null;
            return toCustomer(doc);
        } catch (Exception e) {
            System.out.println("Mongo read failed: " + e.getMessage());
            return null;
        }
    }

    // READ ALL
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (MongoClient client = MongoConnectionRepo.getClient()) {
            MongoCollection<Document> col = getCollection(client);
            for (Document doc : col.find()) {
                customers.add(toCustomer(doc));
            }
        } catch (Exception e) {
            System.out.println("Mongo read all failed: " + e.getMessage());
        }
        return customers;
    }

    // UPDATE
    public boolean updateCustomerByEmail(String email, Customer updated) {
        try (MongoClient client = MongoConnectionRepo.getClient()) {
            MongoCollection<Document> col = getCollection(client);

            Document updateDoc = new Document("$set", toDocument(updated));
            UpdateResult result = col.updateOne(eq("email", email), updateDoc);
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Mongo update failed: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteCustomerByEmail(String email) {
        try (MongoClient client = MongoConnectionRepo.getClient()) {
            MongoCollection<Document> col = getCollection(client);
            DeleteResult result = col.deleteOne(eq("email", email));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            System.out.println("Mongo delete failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes ALL documents in the customers collection (for clean reruns / screenshots).
     */
    public void deleteAll() {
        try (MongoClient client = MongoConnectionRepo.getClient()) {
            MongoCollection<Document> col = getCollection(client);
            col.deleteMany(new Document());
        } catch (Exception e) {
            System.out.println("Mongo deleteAll failed: " + e.getMessage());
        }
    }
}