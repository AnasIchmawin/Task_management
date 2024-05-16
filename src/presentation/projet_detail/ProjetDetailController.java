package presentation.projet_detail;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import metier.Gestionnaire.GestionnaireDocument;
import metier.Gestionnaire.GestionnaireTache;
import persistence.DAO.DAOProjet;
import presentation.GetDocument.GetDocModel;
import presentation.NewDocument.AddDocumentView;





public class ProjetDetailController {
    private GestionnaireDocument gestionnaireDocument;
    private GetDocModel model;
    private Projet_Detail_View view;

    public ProjetDetailController(Projet_Detail_View view){
        this.model = new GetDocModel();
        this.view = view;
        this.gestionnaireDocument = new GestionnaireDocument();

    }
    
    public void handleAjouterButtonAction(GridPane gridPane) {
        Button newListButton = new Button("Seance  1");
        newListButton.getStyleClass().add("AjouterSeance-Style");
        GridPane.setHgrow(newListButton, Priority.ALWAYS); // Définir la croissance horizontale pour occuper toute la
                                                           // largeur disponible

        int colIndex = gridPane.getChildren().size() % 1; // Calcul de l'indice de colonne
        int rowIndex = gridPane.getChildren().size() / 1; // Calcul de l'indice de ligne

        gridPane.add(newListButton, colIndex, rowIndex);
    }

    public void handleSaveButton(GridPane gridPane) {
        // Save button;
    }
    public void handleAjouterDocButtonAction() {
        System.out.println("Ajouter Button Clicked");
        AddDocumentView adddoc = new AddDocumentView(this);
        Stage stage = new Stage();
        adddoc.start(stage);
    }

    public void addDocToTache(String id, String doc) {
        this.model.addDocumentToSeance(id, doc);
        System.out.println("Document added to Projet: " + doc);
        displayDocuments();
    }

    public void displayDocuments() {
        List<String> mesDocs = new ArrayList<>(this.model.getListOfDocuments().values());
        this.view.getZoneDocuments().getChildren().clear();

        for (String doc : mesDocs) {
            Button newTaskButton = createDocButton(doc);
            int colIndex = this.view.getZoneDocuments().getChildren().size() % 6; 
                                                                                              
            int rowIndex = this.view.getZoneDocuments().getChildren().size() / 6; 
            this.view.getZoneDocuments().add(newTaskButton, colIndex, rowIndex);
        }
    }
    private Button createDocButton(String doc) {
        Button newTaskButton = new Button(doc);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./Pictures/document.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }


    //handleajouterTacheButtonAction
    @SuppressWarnings("static-access")
    public void handleAjouterTacheButtonAction(GridPane gridPane) {
        Button deleteButton = createButtonWithIcon("", "file:./Pictures/delete.png", 15, 15);
        Button cloneButton = createButtonWithIcon("", "file:./Pictures/cloner.png", 15, 15);
            CheckBox taskCheckBox = new CheckBox("taskName");
            taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 430px; " +
                    "-fx-min-height: 20px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 17px;"+
                    "-fx-padding: 0px 0px 0px 5px;");
            taskCheckBox.setAlignment(Pos.CENTER_LEFT); // Align the checkbox to the left
            deleteButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 25px; " +
                    "-fx-min-height: 25px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 13px;");
            
            cloneButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 25px; " +
                    "-fx-min-height: 25px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 13px;");

            deleteButton.setOnAction(e -> {

                String projectId = "";
                // Get the task ID from the deleteButton's properties or from your application logic
                String taskId = "";
                handleSupprimerTacheButtonAction(projectId, taskId);
                gridPane.getChildren().removeAll(taskCheckBox, deleteButton, cloneButton);
                //delete the space of the deleted task
                for (Node node : gridPane.getChildren()) {
                    Integer rowIndex = GridPane.getRowIndex(node);
                    if (rowIndex != null && rowIndex > gridPane.getRowIndex(taskCheckBox)) {
                        GridPane.setRowIndex(node, rowIndex - 1);
                    }
                }
            });

            cloneButton.setOnAction(e -> {
                handleAjouterTacheButtonAction(gridPane);
            });

            int row = gridPane.getRowCount();
            gridPane.add(deleteButton, 3, row);
            gridPane.add(cloneButton, 4, row);
            gridPane.add(taskCheckBox, 5, row);

            taskCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                //change color checkbox and thier buttons when checked
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        taskCheckBox.setStyle("-fx-background-color: #FF7E67; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 450px; " +
                                "-fx-min-height: 20px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 17px;"+
                                "-fx-padding: 0px 0px 0px 5px;");
                        deleteButton.setStyle("-fx-background-color: #FF7E67; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                        cloneButton.setStyle("-fx-background-color: #FF7E67; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                    } else {
                        taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 450px; " +
                                "-fx-min-height: 20px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 17px;"+
                                "-fx-padding: 0px 0px 0px 5px;");
                        deleteButton.setStyle("-fx-background-color: #112D4E; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                        cloneButton.setStyle("-fx-background-color: #112D4E; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                    }
                }
            });
    }

        public void handleSupprimerTacheButtonAction(String projectId, String taskId) {
    DAOProjet daoProjet = new DAOProjet();
   
    org.bson.Document project = daoProjet.read(projectId);
    if (project != null) {
        
        List<javax.swing.text.Document> tasks = (List<Document>) project.get("taches");
        tasks.removeIf(task -> ((org.bson.Document) task).getString("id").equals(taskId));
        daoProjet.update(projectId, null, null, tasks);
    }
}



    private Button createButtonWithIcon(String name, String string, int i, int j) {
        Button button = new Button(name);
        try {
            Image icon = new Image(string);
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(i);
            iconView.setFitHeight(j);
            button.setGraphic(iconView);
        } catch (Exception e) {
            System.out.println("Error loading the icon: " + e.getMessage());
        }
        return button;
    }

    public void setIdTitreDocument(String id, String titre) {
        this.model.setIdTitreDocument(id, titre);
    }

}
