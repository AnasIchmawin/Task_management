package mygroup.presentation.GetSeances;

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
import mygroup.metier.Gestionnaire.GestionnaireSeance;
import mygroup.presentation.NewList.AddListController;
import mygroup.presentation.NewProjet.AddProjetController;
import mygroup.presentation.NewProjet.AddProjetView;
import mygroup.presentation.listes.ListeFormController;
import mygroup.presentation.projets.ProjetsFormController;

public class GetSeancesController {
    private GestionnaireSeance gestionnaireSeance;
    @SuppressWarnings("unused")
    private AddListController addListController;
    private AddProjetController addProjetController;
    @SuppressWarnings("unused")
    private ListeFormController listeFormController;
    private LinkedHashMap<List<Integer>, List<String>> GridCaseInfos;
    private ProjetsFormController projetsFormController;

    public GetSeancesController(AddListController addListController, ListeFormController listeFormController) {
        this.gestionnaireSeance = new GestionnaireSeance();
        this.addListController = addListController;
        this.listeFormController = listeFormController;
    }

    public GetSeancesController(AddProjetController addProjetController, ProjetsFormController projetsFormController) {
        this.gestionnaireSeance = new GestionnaireSeance();
        this.addProjetController = addProjetController;
        this.projetsFormController = projetsFormController;
    }

    // Method to get all seances
    public LinkedHashMap<String, String> getSeancesMap() {
        List<Document> seances = gestionnaireSeance.getAllSeances();
        LinkedHashMap<String, String> seancesDisponibles = new LinkedHashMap<>();
        for (Document seance : seances) {
            String id = seance.getObjectId("_id").toString();
            String titre = seance.getString("titre");
            seancesDisponibles.put(id, titre);
        }
        return seancesDisponibles;
    }

    // Method to handle cancel button
    public void handleCancelButton(ActionEvent eventAddList) { 
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
        AddProjetView view = new AddProjetView(this.addProjetController, this.projetsFormController);
        view.start(stage);
    }

    // Method to display seances
    public void displaySeances(GridPane gridPane) {
        GridCaseInfos = new LinkedHashMap<>();

        if (getSeancesMap().isEmpty()) {
            System.out.println("No seances available");
            return;
        }

        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : getSeancesMap().entrySet()) {
            String seanceTitle = entry.getValue();

            CheckBox checkBox = createCheckBox(seanceTitle);
            gridPane.add(checkBox, colCount, rowCount);

            GridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), seanceTitle));
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    // Method to handle confirm button
    public void handleConfirmButton(GridPane gridPane, ActionEvent eventAddList) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String seance = checkBox.getText();
                    String id = getIdFromMap(GridCaseInfos, GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
                    this.addProjetController.addTaskToList(id, seance);
                }
            }
        }
        AddProjetView view = new AddProjetView(this.addProjetController, this.projetsFormController);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
        view.start(stage);
    }

    // Method to create a checkbox
    private CheckBox createCheckBox(String seance) {
        CheckBox checkBox = new CheckBox(seance);
        checkBox.setTextFill(Color.WHITE);
        checkBox.getStyleClass().add("seance-checkbox-style");
        checkBox.setStyle("-fx-focus-color: transparent;");
        return checkBox;
    }

    private String getIdFromMap(Map<List<Integer>, List<String>> map, int row, int col) {
        for (Map.Entry<List<Integer>, List<String>> entry : map.entrySet()) {
            List<Integer> key = entry.getKey();
            if (key.get(0) == row && key.get(1) == col) {
                return entry.getValue().get(0);
            }
        }
        return null;
    }
}

