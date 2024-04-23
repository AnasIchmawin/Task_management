//n'oublie pas d'ajouter le fichier mongo-java-driver-3.12.14 

package persistence;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DBConnection {
    private static DBConnection instanceBD;
    private MongoClient mongoClient;
    public MongoDatabase database;

    private DBConnection() {
        // Initialisation de la connexion à MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("MyDataBase");
    }

    //recuperer l'instance de la base de données
    public static DBConnection getInstance() {
        if (instanceBD == null) {
            instanceBD = new DBConnection();
        }
        return instanceBD;
    }

    // Récupérer une collection
    public MongoCollection<Document> getCollection(String nomCollection) {
        return database.getCollection(nomCollection);
    }

    // creer une collection 
    public void createCollection(String nomCollection) {
        database.createCollection(nomCollection);
    }

    // Supprimer une collection
    public void dropCollection(String nomCollection) {
        database.getCollection(nomCollection).drop();
    }
}
