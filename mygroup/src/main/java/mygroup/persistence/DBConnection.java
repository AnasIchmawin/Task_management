package mygroup.persistence ;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
    private static DBConnection instanceBD;
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DBConnection() {
        try {
            // Initialisation de la connexion à MongoDB
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("MyDataBase");
            if(database!=null){
                System.out.println("connected");

            }
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le programme)
            System.err.println("Erreur lors de la connexion à MongoDB: " + e.getMessage());
            System.exit(1);
        }
    }

    // Récupérer l'instance de la base de données
    public static synchronized DBConnection getInstance() {
        if (instanceBD == null) {
            instanceBD = new DBConnection();
        }
        return instanceBD;
    }

    // Obtenir la base de données
    public MongoDatabase getDatabase() {
        return database;
    }
    
}
