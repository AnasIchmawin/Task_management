package presentation.NewDocument;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.bson.Document;

public class AddDocumentController {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public AddDocumentController(String databaseName, String collectionName) {
        mongoClient = new MongoClient("localhost", 27017); 
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    public void saveDocument(DocumentModel documentModel) {
        Document document = new Document("titre", documentModel.getTitre())
                .append("url", documentModel.getUrl())
                .append("description", documentModel.getDescription())
                .append("dateInsertion", documentModel.getDateInsertion().toString())
                .append("idProjet", documentModel.getIdProjet())
                .append("idTache", documentModel.getIdTache());

        collection.insertOne(document);

        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Document enregistré avec succès !");
            alert.showAndWait();
        });
    }

    public void close() {
        mongoClient.close();
    }
}
