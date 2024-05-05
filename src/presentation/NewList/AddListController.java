package presentation.NewList;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.GestionnaireListe;
import metier.POJOListe;
import metier.Errors.NonValidList;
import presentation.GetTasks.GetTasksController;
import presentation.GetTasks.GetTasksView;
import presentation.listes.ListeFormController;

public class AddListController {
    private GestionnaireListe gestionnaireListe;

    public AddListController() {
        this.gestionnaireListe = new GestionnaireListe();
    }

    public void saveInfosListe(AddListModel addListModel) {
        List<Document> tachesDocuments = convertToDocumentList(addListModel.getTitreSelectionnes());
        POJOListe nouvelleListe = new POJOListe(addListModel.getTitre(), addListModel.getDiscription(),
                tachesDocuments);

        try {
            gestionnaireListe.creerListe(nouvelleListe);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "La liste a été créée avec succès !");

        } catch (NonValidList e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
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

    public void getTasksView(ActionEvent event, AddListModel addListModel, ListeFormController listeFormController) {
        GetTasksController controller = new GetTasksController(addListModel, listeFormController);
        GetTasksView view = new GetTasksView(controller);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        view.start(stage);
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
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newListButton;
    }
}
