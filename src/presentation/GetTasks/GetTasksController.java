package presentation.GetTasks;

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
import metier.GestionnaireTache;
import presentation.NewList.AddListController;
import presentation.NewList.AddListView;
import presentation.NewProjet.AddProjetController;
import presentation.NewProjet.AddProjetView;
import presentation.listes.ListeFormController;
import presentation.projets.ProjetsFormController;

public class GetTasksController {
    private GestionnaireTache gestionnaireTache;
    private AddListController addListController;
    private AddProjetController addProjetController;
    private ListeFormController listeFormController;
    private LinkedHashMap<List<Integer>, List<String>> GridCaseInfos;
    private ProjetsFormController projetsFormController;

    public GetTasksController(AddListController addListController, ListeFormController listeFormController) {
        this.gestionnaireTache = new GestionnaireTache();
        this.addListController = addListController;
        this.listeFormController = listeFormController;
    }

    public GetTasksController(AddProjetController addProjetController, ProjetsFormController projetsFormController) {
        this.gestionnaireTache = new GestionnaireTache();
        this.addProjetController = addProjetController;
        this.projetsFormController = projetsFormController;
    }

    // Method to get all tasks
    public LinkedHashMap<String, String> getTasksMap() {
        List<Document> tasks = gestionnaireTache.getAllTasks();
        LinkedHashMap<String, String> taches_Disponibles = new LinkedHashMap<>();
        for (Document task : tasks) {
            String id = task.getObjectId("_id").toString();
            String titre = task.getString("titre");
            taches_Disponibles.put(id, titre);
        }
        return taches_Disponibles;
    }

    // Method to handle cancel button
    public void handleCancelButton(ActionEvent eventAddList) {
        if (this.addListController != null) {
            AddListView view = new AddListView(this.addListController, this.listeFormController);
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            view.start(stage);
        } else {
            AddProjetView view = new AddProjetView(this.addProjetController, this.projetsFormController);
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            view.start(stage);
        }
    }

    // Method to display tasks
    public void displayTasks(GridPane gridPane) {
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
            gridPane.add(checkBox, colCount, rowCount);

            GridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), taskTitle));
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
                    String task = checkBox.getText();
                    String id = getIdFromMap(GridCaseInfos, GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
                    System.out.println("Task selected: " + task + " with id: " + id);
                    if (this.addListController != null)
                        this.addListController.addTaskToList(id, task);
                    else {
                        this.addProjetController.addTaskToList(id, task);
                    }
                }
            }
        }
        if (this.addListController != null && this.addListController != null) {
            AddListView view = new AddListView(this.addListController, this.listeFormController);
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            view.start(stage);
        } else {
            AddProjetView view = new AddProjetView(this.addProjetController, this.projetsFormController);
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            view.start(stage);
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
