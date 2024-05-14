package presentation.NewList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.GestionnaireListe;
import metier.POJOListe;
import metier.Errors.NonValidList;
import presentation.GetTasks.GetTasksView;
import presentation.listes.ListeFormController;

public class AddListController {
    private GestionnaireListe gestionnaireListe;
    private AddListView addListView;
    private AddListModel addListModel;
    private ListeFormController listeFormController;

    public AddListController(AddListView addListView, ListeFormController listeFormController) {
        this.gestionnaireListe = new GestionnaireListe();
        this.addListView = addListView;
        this.listeFormController = listeFormController;
        this.addListModel = new AddListModel("", "", new LinkedHashMap<>());
    }

    public void start(Stage primaryStage) {
        addListView.start(primaryStage);
    }

    public void saveInfosListe(ActionEvent event) {
        try {
            if (!addListView.getTitre().isEmpty()) {
                updateListModel();
                String titre = addListModel.getTitre();
                String description = addListModel.getDescription();
                LinkedHashMap<String, String> taches = addListModel.getTachesSelectionnees();

                POJOListe nouvelleListe = new POJOListe(titre, description, new ArrayList<>(taches.values()));
                this.gestionnaireListe.setListe(nouvelleListe);
                gestionnaireListe.creerListe();
                showAlert(Alert.AlertType.INFORMATION, "Succès", "La liste a été créée avec succès");
                this.listeFormController.displayAvailableLists(false);
                closerWindow(event);

            }
        } catch (NonValidList e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
    }

    private void updateListModel() {
        addListModel.setTitre(addListView.getTitre());
        addListModel.setDescription(addListView.getDescription());
    }

    public void getTasksView(ActionEvent event) {
        String titre = addListView.getTitre();
        String description = addListView.getDescription();
        this.addListModel.setTitre(titre);
        this.addListModel.setDescription(description);

        GetTasksView view = new GetTasksView(this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        view.start(stage);
    }

    public void displayTasks(GridPane gridPane) {
        List<String> mesTaches = addListModel.getTasksTitles();

        for (String title : mesTaches) {
            Button newTaskButton = createTaskButton(title);
            int colIndex = gridPane.getChildren().size() % 6; // Calculating column index
            int rowIndex = gridPane.getChildren().size() / 6; // Calculating row index
            gridPane.add(newTaskButton, colIndex, rowIndex);
        }
    }

    public void updateView(AddListView view) {
        view.setTitre(this.addListModel.getTitre());
        view.setDescription(this.addListModel.getDescription());
    }

    public AddListModel getAddListModel() {
        return this.addListModel;
    }

    public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addTask(String id, String title) {
        this.addListModel.getTachesSelectionnees().put(id, title);
    }

    private Button createTaskButton(String title) {
        Button newTaskButton = new Button(title);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
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
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
