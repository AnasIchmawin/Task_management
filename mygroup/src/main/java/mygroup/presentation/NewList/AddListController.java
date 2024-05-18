package mygroup.presentation.NewList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import mygroup.metier.Gestionnaire.GestionnaireListe;
import mygroup.metier.POJO.POJOListe;
import mygroup.metier.Errors.NonValidList;
import mygroup.presentation.listes.ListeFormController;
import mygroup.presentation.tache_ajoute.addTacheview;

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
        this.updateView();
    }

    public AddListController(AddListView addListView, AddListController addListController) {
        this.gestionnaireListe = new GestionnaireListe();
        this.addListView = addListView;
        this.addListModel = addListController.getAddListModel();
        this.listeFormController = addListController.getListeFormController();
        this.updateView();
        this.displayTasks();
    }

    public ListeFormController getListeFormController() {
        return this.listeFormController;
    }

    public void saveInfosListe(ActionEvent event) {
        try {
            updateListModel();
            String titre = addListModel.getTitre();
            String description = addListModel.getDescription();
            LinkedHashMap<String, String> taches = addListModel.getTachesSelectionnees();


            POJOListe nouvelleListe = new POJOListe(titre, description, new ArrayList<>(taches.keySet()));
            this.gestionnaireListe.setListe(nouvelleListe);
            gestionnaireListe.creerListe();
            listeFormController.addList();
            // mettre id list to  taches : 
            // get last id list 
            String lastListId = gestionnaireListe.getLastListId();
            for(String id : taches.keySet()){
                //
            }
            
            showAlert(AlertType.INFORMATION, "Succès", "Liste créée avec succès",
                    "La liste a été créée avec succès", Duration.seconds(1));


        } catch (NonValidList e) {
            showAlert(AlertType.ERROR, "Erreur", "Erreur lors de la création de la liste",
                    e.getMessage(), Duration.seconds(2));
        }
        finally {
            closerWindow(event);
        }
    }

    private void updateListModel() {
        addListModel.setTitre(addListView.getTitre());
        addListModel.setDescription(addListView.getDescription());
    }

    public void getTasksView(ActionEvent event) {
        addTacheview view = new addTacheview(this);
        Stage stage = new Stage();
        view.start(stage);
    }



    public void displayTasks() {
        this.addListView.getZoneTaches().getChildren().clear();
        List<String> mesTaches = addListModel.getTasksTitles();

        for (String title : mesTaches) {
            Button newTaskButton = createTaskButton(title);
            int colIndex = addListView.getZoneTaches().getChildren().size() % 6; // Calculating column index
            int rowIndex = addListView.getZoneTaches().getChildren().size() / 6; // Calculating row index
            addListView.getZoneTaches().add(newTaskButton, colIndex, rowIndex);
        }
    }



    public void updateView() {
        addListView.setTitre(this.addListModel.getTitre());
        addListView.setDescription(this.addListModel.getDescription());
    }

    public AddListModel getAddListModel() {
        return this.addListModel;
    }

    public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addNewTask(String Id , String title) {
        this.addListModel.getTachesSelectionnees().put(Id, title);
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
            Image listIcon = new Image("file:./mygroup/src/main/java/Pictures/to-do.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }



    public static void showAlert(AlertType type, String title, String headerText, String contentText,
            Duration duration) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
            alert.close();
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

}
