package presentation.NewProjet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;



import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.Gestionnaire.GestionnaireProjet;
import metier.POJO.POJOProjet;
import metier.Errors.NonValidList;
import presentation.GetTasks.GetTasksController;
import presentation.GetTasks.GetTasksView;
import presentation.projets.ProjetsFormController;

public class AddProjetController {
    private final GestionnaireProjet gestionnaireProjet;
    private final AddProjetView addProjetView;
    private final AddProjetModel addProjetModel;

    public AddProjetController(AddProjetView addProjetView) {
        this.gestionnaireProjet = new GestionnaireProjet();
        this.addProjetView = addProjetView;
        this.addProjetModel = new AddProjetModel("", "","","","","",new LinkedHashMap<>());
    }

    public void saveInfosProjet(ActionEvent event) {
        if (!addProjetView.getTitre().isEmpty()) {
            updateProjetModel();
            POJOProjet nouveauProjet = new POJOProjet(this.addProjetModel);
            this.gestionnaireProjet.setProjet(nouveauProjet);

            try {
                gestionnaireProjet.creerProjet();
            } catch (NonValidList e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
            }
        }
    }

    private void updateProjetModel() {
        addProjetModel.setTitre(addProjetView.getTitre());
        addProjetModel.setDescription(addProjetView.getDescription());
        addProjetModel.setCategorie(addProjetView.getCategorie());
        addProjetModel.setType(addProjetView.getType());
        addProjetModel.setDateDebut(addProjetView.getDateDebut());
        addProjetModel.setDateFin(addProjetView.getDateFin());
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void getTasksView(ActionEvent event, ProjetsFormController projetFormController) {
        String titre = addProjetView.getTitre();
        String description = addProjetView.getDescription();
        this.addProjetModel.setTitre(titre);
        this.addProjetModel.setDescription(description);

        GetTasksController Controller = new GetTasksController(this, projetFormController);
        GetTasksView View = new GetTasksView(Controller);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        View.start(stage);
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

    // public void displayDocuments(GridPane gridPane) {
    //     List<String> mesDocuments = new ArrayList<>(getDocumentsTitles());

    //     for (String title : mesDocuments) {
    //         Button newDocumentButton = createTaskButton(title);
    //         int colIndex = gridPane.getChildren().size() % 6; // Calculating column index
    //         int rowIndex = gridPane.getChildren().size() / 6; // Calculating row index
    //         gridPane.add(newDocumentButton, colIndex, rowIndex); 
    //     }
    // }

    // public void displaySeances(GridPane gridPane) {
    //     List<String> mesSeances = new ArrayList<>(getSeancesTitles());

    //     for (String title : mesSeances) {
    //         Button newSeanceButton = createTaskButton(title);
    //         int colIndex = gridPane.getChildren().size() % 6; // Calculating column index
    //         int rowIndex = gridPane.getChildren().size() / 6; // Calculating row index
    //         gridPane.add(newSeanceButton, colIndex, rowIndex); 
    //     }
    // }


    private List<String> getTasksTitles() {
        return new ArrayList<>(this.addProjetModel.getTachesSelectionnees().values());
    }

    // private List<String> getDocumentsTitles() {
    //     return new ArrayList<>(this.addProjetModel.getDocumentsSelectionnes().values());
    // }

    // private List<String> getSeancesTitles() {
    //     return new ArrayList<>(this.addProjetModel.getSeancesSelectionnees().values());
    // }

    public void updateView(AddProjetView view) {
        view.setTitre(this.addProjetModel.getTitre());
        view.setDescription(this.addProjetModel.getDescription());
    }

    public AddProjetModel getAddProjetModel(){
        return this.addProjetModel;
    }

    //addTaskToList
    public void addSeanceToList(String id, String task) {
        this.addProjetModel.addTask(id, task);
    }

    //closerWindow
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
