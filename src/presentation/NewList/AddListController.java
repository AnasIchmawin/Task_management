package presentation.NewList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import presentation.GetTasks.GetTasksModel;
import presentation.GetTasks.GetTasksView;
import presentation.listes.ListeFormController;

public class AddListController {
    private GestionnaireListe gestionnaireListe;
    private AddListView addListView;
    private AddListModel addListModel;

    public AddListController() {
        this.gestionnaireListe = new GestionnaireListe();
    }

    public AddListController(AddListView addListView) {
        this.gestionnaireListe = new GestionnaireListe();
        this.addListView = addListView;
    }

    public AddListController(AddListModel addListModel, List<String> tasks) {
        this.gestionnaireListe = new GestionnaireListe();
        this.addListModel = addListModel(addListModel.getTitre() , addListModel.getDescription() , tasks);
        setTitleAndDescription(this.addListModel.getTitre(), this.addListModel.getDescription());
    }

    public AddListController(AddListModel addListModel2, Map<String, String> selectedTasks) {
        //TODO Auto-generated constructor stub
    }

    private AddListModel addListModel(String titre, String desc, List<String> tasks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addListModel'");
    }

    public void saveInfosListe(AddListModel addListModel, ActionEvent event) {
        List<Document> tachesDocuments = convertToDocument(addListModel.getTitreSelectionnes());
        POJOListe nouvelleListe = new POJOListe(addListModel.getTitre(), addListModel.getDescription(),
                tachesDocuments);
        this.gestionnaireListe.setListe(nouvelleListe);

        try {
            gestionnaireListe.creerListe();
            // cloeser la fenetre
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la fenêtre
            // actuelle
            stage.close();

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

    private List<Document> convertToDocument(HashMap<String ,String> tacheList) {
        List<Document> tachesDocuments = new ArrayList<>();
        for (Map.Entry<String, String> entry : tacheList.entrySet()) {
            Document tache = new Document();
            tache.append("id", entry.getKey());
            tache.append("titre", entry.getValue());
            tachesDocuments.add(tache);
        }
        return tachesDocuments;
    }

    public void getTasksView(ActionEvent event) {
        String titre = this.addListView.getTitre();
        String discription = this.addListView.getDescription();
        this.addListModel = new AddListModel(titre, discription, new HashMap<>());

        GetTasksController controller = new GetTasksController(this);
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

    public void setTitleAndDescription(String titre, String description) {
        this.addListView.setTitre(titre);
        this.addListView.setDescription(description);
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
   public AddListModel  getAddListModel(){
       return this.addListModel;
   }
}
