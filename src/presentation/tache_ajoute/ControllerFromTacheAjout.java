package presentation.tache_ajoute;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.GestionnaireTache;
import metier.POJOTache;
import org.bson.Document;
import presentation.NewProjet.AddProjetModel;
import presentation.listes.ListeFormController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControllerFromTacheAjout {
    private GestionnaireTache gestionnaireTache;

    public ControllerFromTacheAjout() {
        this.gestionnaireTache = new GestionnaireTache();
    }

    public void handleAjouterButtonAction(ModuleFromTacheAjout moduleFromTacheAjout) {
        saveInfosTache(moduleFromTacheAjout);
    }

    public void saveInfosTache(ModuleFromTacheAjout moduleFromTacheAjout) {
        List<Document> documents = convertToDocumentList(moduleFromTacheAjout.getTitreSelectionnes());
        POJOTache nouveauTache = new POJOTache();
        nouveauTache.setTitre(moduleFromTacheAjout.getTitre());
        nouveauTache.setCategorie(moduleFromTacheAjout.getCategorie());
        nouveauTache.setDescription(moduleFromTacheAjout.getDescription());
        nouveauTache.setDateDebut(moduleFromTacheAjout.getDateDebut());
        nouveauTache.setDateFin(moduleFromTacheAjout.getDateFin());
        nouveauTache.setDocuments(documents);
        nouveauTache.setProjet(moduleFromTacheAjout.getProjet());
        nouveauTache.setListe(moduleFromTacheAjout.getListe());

        gestionnaireTache.createTask(nouveauTache);
        showAlert(Alert.AlertType.INFORMATION, "Succès", "La tache a été créée avec succès !");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private List<Document> convertToDocumentList(List<String> taches) {
        List<Document> tachesDocuments = new ArrayList<>();
        for (String tache : taches) {
            Document tacheDocument = new Document("titre", tache);
            tachesDocuments.add(tacheDocument);
        }
        return tachesDocuments;
    }

    public void getTasksView(AddProjetModel addListModel, ListeFormController listeFormController) {
        // Implement your view logic here
    }

    public void afficherTaches(List<String> list, GridPane gridPane) {
        List<String> mestaches = new ArrayList<>(list);

        for (String title : mestaches) {
            Button newListButton = creerBouton(title);
            int colIndex = gridPane.getChildren().size() % 6; // Calculating column index
            int rowIndex = gridPane.getChildren().size() / 6; // Calculating row index
            gridPane.add(newListButton, colIndex, rowIndex);
        }
    }

    public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private Button creerBouton(String title) {
        Button newListButton = new Button(title);
        newListButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./Pictures/to-do.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newListButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône du Projet : " + e.getMessage());
        }

        return newListButton;
    }
}
