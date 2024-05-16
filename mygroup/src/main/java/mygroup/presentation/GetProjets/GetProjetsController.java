package mygroup.presentation.GetProjets;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireProjet;
import mygroup.presentation.projets.ProjetsFormController;

public class GetProjetsController {
    private LinkedHashMap<List<Integer>, List<String>> GridCaseInfos;
    private GestionnaireProjet gestionnaireProjet;
    private ProjetsFormController projetFormController;
    private GetProjetsView getProjetsView;
    private GetProjetsModel getProjetsModel;

    public GetProjetsController() {
        super();
    }

    public GetProjetsController(GetProjetsView getProjetsView, ProjetsFormController projetFormController) {
        this.gestionnaireProjet = new GestionnaireProjet();
        this.getProjetsView = getProjetsView;
        this.getProjetsModel = new GetProjetsModel();
        this.projetFormController = projetFormController;
        this.getProjetsView.createProjetGridPane(this);
    }

    public void showView() {
        Stage stage = new Stage();
        this.getProjetsView.start(stage);
    }

    public void handleConfirmButton(ActionEvent event) {
        this.getProjetsModel.setProjetDeleted(getSelectedProjets());
        this.gestionnaireProjet.supprimerProjet(this.getProjetsModel.getProjetDeleted());
        List<String> projetIds = new ArrayList<>();
        for (java.util.Map.Entry<String, String> entry : getProjetsMap().entrySet()) {
            projetIds.add(entry.getKey());
        }
        this.getProjetsModel.setProjetDeleted(projetIds);
        getProjetsView.getZoneProjets().getChildren().clear();
        this.projetFormController.displayProjets(false);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void displayProjets(GridPane zoneProjets) {
        GridCaseInfos = new LinkedHashMap<>();
        if (getProjetsMap().isEmpty()) {
            System.out.println("No Projets available");
            return;
        }
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : getProjetsMap().entrySet()) {
            String taskTitle = entry.getValue();

            CheckBox checkBox = createCheckBox(taskTitle);
            zoneProjets.add(checkBox, colCount, rowCount);

            GridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), taskTitle));
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }

    }

    // Method to create a checkbox
    private CheckBox createCheckBox(String task) {
        CheckBox checkBox = new CheckBox(task);
        checkBox.setTextFill(Color.WHITE);
        checkBox.getStyleClass().add("task-checkbox-style");
        checkBox.setStyle("-fx-focus-color: transparent;");
        return checkBox;
    }

    public LinkedHashMap<String, String> getProjetsMap() {
        List<Document> projets = gestionnaireProjet.obtenirToutesLesProjets();
        LinkedHashMap<String, String> projets_Disponibles = new LinkedHashMap<>();
        for (Document projet : projets) {
            String id = projet.getObjectId("_id").toString();
            String titre = projet.getString("titre");
            projets_Disponibles.put(id, titre);
        }
        return projets_Disponibles;
    }

    // creer une methode pour recuperer les projets selectionnees
    public List<String> getSelectedProjets() {
        List<String> selectedProjets = new ArrayList<>();
        for (Map.Entry<List<Integer>, List<String>> entry : GridCaseInfos.entrySet()) {
            List<Integer> key = entry.getKey();
            List<String> value = entry.getValue();
            CheckBox checkBox = (CheckBox) getProjetsView.getZoneProjets().getChildren().get(key.get(0) * 4 + key.get(1));
            if (checkBox.isSelected()) {
                selectedProjets.add(value.get(0));
            }
        }
        return selectedProjets;
    }

    public void setview(GetProjetsView getProjetsView2) {
        this.getProjetsView = getProjetsView2;
    }

}
