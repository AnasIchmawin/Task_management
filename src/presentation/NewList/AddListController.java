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
import presentation.GetTasks.GetTasksController;
import presentation.GetTasks.GetTasksView;
import presentation.listes.ListeFormController;

public class AddListController {
    private final GestionnaireListe gestionnaireListe;
    private final AddListView addListView;
    private final AddListModel addListModel;

    public AddListController(AddListView addListView) {
        this.gestionnaireListe = new GestionnaireListe();
        this.addListView = addListView;
        this.addListModel = new AddListModel("", "",new LinkedHashMap<>());
    }

    public void saveInfosListe(ActionEvent event) {
        if (!addListView.getTitre().isEmpty()) {
            updateListModel();
            POJOListe nouvelleListe = new POJOListe(this.addListModel);
            this.gestionnaireListe.setListe(nouvelleListe);

            try {
                gestionnaireListe.creerListe();
            } catch (NonValidList e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
            }
        }
    }

    private void updateListModel() {
        addListModel.setTitre(addListView.getTitre());
        addListModel.setDescription(addListView.getDescription());
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void getTasksView(ActionEvent event, ListeFormController listeFormController) {
        String titre = addListView.getTitre();
        String description = addListView.getDescription();
        this.addListModel.setTitre(titre);
        this.addListModel.setDescription(description);

        GetTasksController controller = new GetTasksController(this, listeFormController);
        GetTasksView view = new GetTasksView(controller);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        view.start(stage);
    }

    public void displayTasks(GridPane gridPane) {
        List<String> mesTaches = new ArrayList<>(getTasksTitles());

        for (String title : mesTaches) {
            Button newTaskButton = createTaskButton(title);
            int colIndex = gridPane.getChildren().size() % 6; // Calculating column index
            int rowIndex = gridPane.getChildren().size() / 6; // Calculating row index
            gridPane.add(newTaskButton, colIndex, rowIndex);
        }
    }

    private List<String> getTasksTitles() {
        return new ArrayList<>(this.addListModel.getTachesSelectionnees().values());
    }

    public void updateView(AddListView view) {
        view.setTitre(this.addListModel.getTitre());
        view.setDescription(this.addListModel.getDescription());
    }

    public AddListModel getAddListModel() {
        return this.addListModel;
    }

    public void addSeanceToList(String id, String task) {
        this.addListModel.addTask(id, task);
    }

    public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
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
}
