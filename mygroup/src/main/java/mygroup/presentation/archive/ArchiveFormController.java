package mygroup.presentation.archive ;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireProjet;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projets.ProjetsFormView ;

public class ArchiveFormController {
    private static final int MAX_COLUMNS = 5;
    private static final int BUTTON_WIDTH = 170;
    private static final int BUTTON_HEIGHT = 60;
    private static final String BUTTON_STYLE = "-fx-background-color: #262626; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;";
    private ArchiveFormView archiveView;
    private GestionnaireProjet gestionnaireProjet;
    private ArchiveModel archiveModel;
    private HashMap<List<Integer>, List<String>> gridCaseInfos;

    public ArchiveFormController(ArchiveFormView archiveFormView) {
        this.archiveView = archiveFormView;
        this.gestionnaireProjet = new GestionnaireProjet();
        this.archiveModel = new ArchiveModel(getProjetsArchiver());
        displayProjets(false);
    }

    public void handleListesButtonAction() {
        Stage stage = (Stage) archiveView.getZoneProjets().getScene().getWindow();
        ListeFormView liste = new ListeFormView();
        liste.start(stage);
    }

    //handleProjectsButtonAction
    public void handleProjectsButtonAction() {
        Stage stage = (Stage) archiveView.getZoneProjets().getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

        
    public Button createProjectButton(String titre) {
        Button newProjetButton = new Button(titre);
        newProjetButton.setStyle(BUTTON_STYLE);
        newProjetButton.setDisable(true);

        newProjetButton.setOnMouseEntered(event -> {
            newProjetButton.setStyle("-fx-background-color: #262626; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;");
            newProjetButton.setCursor(javafx.scene.Cursor.HAND);
        });

        newProjetButton.setOnMouseExited(event -> {
            newProjetButton.setStyle(BUTTON_STYLE);
            newProjetButton.setCursor(javafx.scene.Cursor.DEFAULT);
        });

        try {
            Image projetIcon = new Image("file:./Pictures/to-do.png");
            ImageView projetIconView = new ImageView(projetIcon);
            projetIconView.setFitWidth(15);
            projetIconView.setFitHeight(15);
            newProjetButton.setGraphic(projetIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône du Projet : " + e.getMessage());
        }

        return newProjetButton;
    }

    public void displayProjets(boolean isSorted) {
        archiveModel.setProjets(getProjetsArchiver());
        if(isSorted) {
            archiveModel.sortProjets();
        }
        this.archiveView.getZoneProjets().getChildren().clear();
        gridCaseInfos = new HashMap<>();
        int colCount = 0;
        int rowCount = 0;
        
        for (Map.Entry<String, String> entry : archiveModel.getProjets().entrySet()) {
            Button button = createProjectButton(entry.getValue());
            addProjetButton(button, colCount, rowCount);
            colCount++;
            if (colCount == MAX_COLUMNS) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    private void addProjetButton(Button projetButton, int colCount, int rowCount) {
        archiveView.getZoneProjets().add(projetButton, colCount, rowCount);
    }

    public LinkedHashMap<String, String> getProjetsArchiver() {
         return gestionnaireProjet.getProjetsArchiver();
    }

    public void SearchProjet(String searchText) {
        int colCount = 0;
        int rowCount = 0;

        archiveView.getZoneProjets().getChildren().clear();

        for (Map.Entry<String, String> entry : archiveModel.getProjets().entrySet()) {
            String buttonTitle = entry.getValue().toLowerCase();
            if (buttonTitle.contains(searchText.toLowerCase())) {
                Button button = createProjectButton(entry.getValue());
                addProjetButton(button, colCount, rowCount);
                gridCaseInfos.put(List.of(colCount, rowCount), List.of(entry.getKey(), entry.getValue()));
                colCount++;
                if (colCount == MAX_COLUMNS) {
                    colCount = 0;
                    rowCount++;
                }
            }
        }
    }

}