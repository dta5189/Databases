package mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Project: Lab 3 - Java MySQL & MongoDB CRUD
 * Purpose Details: Provides MongoDB connection client
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 3/1/2026
 * Last Date Changed: 3/1/2026
 * Rev: 1.0
 */
public class MongoConnectionRepo {

    /** MongoDB connection string for local community server */
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    /**
     * Creates and returns a MongoClient.
     *
     * @return MongoClient
     */
    public static MongoClient getClient() {
        return MongoClients.create(CONNECTION_STRING);
    }
}