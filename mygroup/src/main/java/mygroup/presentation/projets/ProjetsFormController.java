package mygroup.presentation.projets;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireProjet ;
import mygroup.presentation.GetProjets.GetProjetsView;
import mygroup.presentation.NewProjet.AddProjetView;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormView;

public class ProjetsFormController {
    private static final int MAX_COLUMNS = 5;
    private static final int BUTTON_WIDTH = 170;
    private static final int BUTTON_HEIGHT = 60;
    private static final String BUTTON_STYLE = "-fx-background-color: #112D4E; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;";
            
    private GestionnaireProjet gestionnaireProjet;
    private ProjetsFormView projetView;
    private ProjetsModel projetsModel;
    private HashMap<List<Integer>, List<String>> gridCaseInfos;

    public ProjetsFormController(ProjetsFormView projetsFormView) {
        this.gestionnaireProjet = new GestionnaireProjet();
        this.projetView = projetsFormView;
        this.projetsModel = new ProjetsModel(getProjetsMap());
    }


    // Affiche le formulaire d'ajout de projet
    public void handleAjouterButtonAction() {
        AddProjetView newProjectFormulaire = new AddProjetView(this);
        Stage stage = new Stage();
        newProjectFormulaire.start(stage);
    }

    public void handleSupprimerButtonAction() {
        GetProjetsView getProjetView = new GetProjetsView(this);
        getProjetView.start(new Stage());
    }

    // Affiche les projets triés
    public void handleOrdonnerButton() {
        projetView.getSearchField().setText("");
        projetView.setTypeBoxValue("Tous");
        projetView.setCategorieBoxValue("Tous");

        this.displayProjets(true);
    }

    // Affiche les projets dans la vue projet
    public void displayProjets(boolean isSorted) {
        projetsModel.setProjets(getProjetsMap());
        if(isSorted) {
            projetsModel.sortProjets();
        }
        this.projetView.getZoneProjets().getChildren().clear();
        gridCaseInfos = new HashMap<>();
        int colCount = 0;
        int rowCount = 0;
        
        for (Map.Entry<String, String> entry : projetsModel.getProjets().entrySet()) {
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
        projetView.getZoneProjets().add(projetButton, colCount, rowCount);
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
        Button newProjetButton = new Button(titre);
        newProjetButton.setStyle(BUTTON_STYLE);

        newProjetButton.setOnMouseEntered(event -> {
            newProjetButton.setStyle("-fx-background-color: #8E9EB2; "
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

    public void handleListesButton() {
        Stage stage = (Stage) projetView.getZoneProjets().getScene().getWindow();
        ListeFormView listes = new ListeFormView();
        listes.start(stage);
    }

    public void handleArchiveButton() {
        Stage stage = (Stage) projetView.getZoneProjets().getScene().getWindow();
        ArchiveFormView archiveFormView = new ArchiveFormView();
        archiveFormView.start(stage);
    }

    public void SearchProjet(String searchText) {
        // chnager les valeur des combox to Tous
        projetView.setTypeBoxValue("Tous");
        projetView.setCategorieBoxValue("Tous");
        // Réinitialisation des compteurs de colonnes et de lignes
        int colCount = 0;
        int rowCount = 0;

        projetView.getZoneProjets().getChildren().clear();

        for (Map.Entry<String, String> entry : projetsModel.getProjets().entrySet()) {
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

    //handleTypeBoxAction
    public void handleBoxsAction() {
        String type = projetView.getTypeBoxValue();
        String categorie = projetView.getCategorieBoxValue();
            // Réinitialisation des compteurs de colonnes et de lignes
        int colCount = 0;
        int rowCount = 0;

        projetView.getZoneProjets().getChildren().clear();

        //afficher les projet qui ont la valeur de key (type) est egale a type selectionner
        for (Map.Entry<String, String> entry : projetsModel.getProjets().entrySet()) {
            String id = entry.getKey();
            String titre = entry.getValue();
            Document projet = gestionnaireProjet.obtenirProjet(id);
            String typeProjet = projet.getString("type");
            String categorieProjet = projet.getString("categorie");
            if ((typeProjet.equals(type) || type.equals("Tous")) && (categorieProjet.equals(categorie) || categorie.equals("Tous"))) {
                Button button = createProjectButton(titre);
                addProjetButton(button, colCount, rowCount);
                gridCaseInfos.put(List.of(colCount, rowCount), List.of(id, titre));
                colCount++;
                if (colCount == MAX_COLUMNS) {
                    colCount = 0;
                    rowCount++;
                }
            }
        }
    }

}