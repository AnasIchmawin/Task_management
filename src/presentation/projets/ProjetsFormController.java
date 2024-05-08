package presentation.projets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.GestionnaireProjet;
import presentation.NewList.AddListView;
import presentation.NewProjet.AddProjetView;

public class ProjetsFormController {
    private GestionnaireProjet gestionnaireProjet;
    private ProjetsFormView projetsView;
    private ProjetsModel projetsModel;
    private HashMap<List<Integer>, List<String>> gridCaseInfos;

    public ProjetsFormController(ProjetsFormView projetsFormView) {
        this.gestionnaireProjet = new GestionnaireProjet();
        this.projetsView = projetsFormView;
        this.projetsModel = new ProjetsModel(getProjetsMap());
    }

    // Affiche le formulaire d'ajout de projet
    public void handleAjouterButtonAction() {
        AddListView newProjectFormulaire = new AddProjetView(this);
        Stage stage = new Stage();
        newProjectFormulaire.start(stage);
    }

    // Affiche les projets triés
    public void handleOrdonnerButton() {
        this.displayProjets(true);
    }

    // Affiche les projets dans la vue projet
    public void displayProjets(boolean isSorted) {
        projetsModel.setProjets(getProjetsMap());
        if(isSorted) {
            projetsModel.sortProjets();
        }
        this.projetsView.getZoneProjets().getChildren().clear();
        gridCaseInfos = new HashMap<>();
        int colCount = 0;
        int rowCount = 0;
        if (this.projetsModel.getProjets() == null) {
            return;
        }
        for (java.util.Map.Entry<String, String> entry : this.projetsModel.getProjets().entrySet()) {
            Button newProjectButton = createProjectButton(entry.getValue());
            while (this.projetsView.getZoneProjets().getChildren().contains(newProjectButton)) {
                colCount++;
                if (colCount == 5) {
                    colCount = 0;
                    rowCount++;
                }
                gridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), entry.getValue()));
            }
            this.projetsView.getZoneProjets().add(newProjectButton, colCount, rowCount);
        }
    }

    // Method to get all projects
    public LinkedHashMap<String, String> getProjetsMap() {
        List<Document> projets = gestionnaireProjet.obtenirToutesLesProjets();
        LinkedHashMap<String, String> projetAvailable = new LinkedHashMap<>();
        for (Document projet : projets) {
            String id = projet.getObjectId("_id").toString();
            String titre = projet.getString("titre");
            projetAvailable.put(id, titre);
        }
        return projetAvailable;
    }

    // Method to create a project button
    public Button createProjectButton(String titre) {
        Button newProjectButton = new Button(titre);
        newProjectButton.setStyle("-fx-background-color: #112D4E; " +
        "-fx-background-radius: 10px; " +
        "-fx-min-width: 170px; " +
        "-fx-min-height: 60px;" +
        "-fx-text-fill: #ffffff;" +
        "-fx-font-size: 18px;");
        newProjectButton.setOnMouseEntered(event -> {
            newProjectButton.setStyle("-fx-background-color: #8E9EB2; " +
            "-fx-background-radius: 10px; " +
            "-fx-min-width: 170px; " +
            "-fx-min-height: 60px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-font-size: 18px;");
            newProjectButton.setCursor(javafx.scene.Cursor.HAND);
        });

        newProjectButton.setOnMouseExited(event -> {
            newProjectButton.setStyle("-fx-background-color: #112D4E; " +
            "-fx-background-radius: 10px; " +
            "-fx-min-width: 170px; " +
            "-fx-min-height: 60px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-font-size: 18px;");
        });
        try {
            Image projetIcon = new Image("file:./Pictures/to-do.png");
            ImageView projetIconView = new ImageView(projetIcon);
            projetIconView.setFitHeight(15);
            projetIconView.setFitWidth(15);
            newProjectButton.setGraphic(projetIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône du projet : " + e.getMessage());
        }
        return newProjectButton;
    }
}
