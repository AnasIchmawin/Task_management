package presentation.GetDocument;

import javafx.scene.Node;

import javafx.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.GestionnaireDocument;
import presentation.seance_ajoute.SceanceAjouteController;
import presentation.tache_ajoute.ControllerFromTacheAjout;

public class GetDocController {
    private GetDocView getDocView;
    private SceanceAjouteController sceanceAjouteController;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private GestionnaireDocument gestionnaireDocument;
    private LinkedHashMap<List<Integer>, List<String>> GridCaseInfos;

    public GetDocController(GetDocView view, ControllerFromTacheAjout controllerFromTacheAjout) {
        this.getDocView = view;
        
        this.gestionnaireDocument = new GestionnaireDocument();
    }
    public GetDocController(GetDocView view, SceanceAjouteController sceanceAjouteController) {
        this.getDocView = view;
        
        this.gestionnaireDocument = new GestionnaireDocument();
    }


    public void diplayDocs() {
        GridCaseInfos = new LinkedHashMap<>();

        if (getTasksMap().isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : getTasksMap().entrySet()) {
            String taskTitle = entry.getValue();

            CheckBox checkBox = createCheckBox(taskTitle);
            this.getDocView.getZoneDocuments().add(checkBox, colCount, rowCount);
            GridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), taskTitle));
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    // Method to create a checkbox
    private CheckBox createCheckBox(String Doc) {
        CheckBox checkBox = new CheckBox(Doc);
        checkBox.setTextFill(Color.WHITE);
        checkBox.getStyleClass().add("task-checkbox-style");
        checkBox.setStyle("-fx-focus-color: transparent;");
        return checkBox;
    }

    // Method to get all tasks
    public LinkedHashMap<String, String> getTasksMap() {
        List<Document> tasks = gestionnaireDocument.getAllDocuments();
        LinkedHashMap<String, String> documents_dispo = new LinkedHashMap<>();
        for (Document task : tasks) {
            String id = task.getObjectId("_id").toString();
            String titre = task.getString("titre");
            documents_dispo.put(id, titre);
        }
        return documents_dispo;
    }

    public void handleCancelButtonAction(ActionEvent event) {
        this.closeWindow(event);
    }

    public void handleConfirmButton(ActionEvent eventAddList) {
        for (Node node : this.getDocView.getZoneDocuments().getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String task = checkBox.getText();
                    String id = getIdFromMap(GridCaseInfos, GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
                    System.out.println("Task selected: " + task + " with id: " + id);
                    this.sceanceAjouteController.addDocToSeance(id, task);
                }
            }
        }
        this.sceanceAjouteController.displayDocuments();
        this.closeWindow(eventAddList);

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

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
