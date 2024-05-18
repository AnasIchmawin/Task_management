package mygroup.presentation.GetTasks;

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
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.presentation.NewList.AddListController;
import mygroup.presentation.NewProjet.AddProjetController;
import mygroup.presentation.NewProjet.AddProjetView;
import mygroup.presentation.projets.ProjetsFormController;
import mygroup.presentation.tache_ajoute.ControllerFromTacheAjout;
import mygroup.presentation.tache_ajoute.addTacheview;

public class GetTasksController {
    private GestionnaireTache gestionnaireTache;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private AddProjetController addProjetController;
    private LinkedHashMap<List<Integer>, List<String>> GridCaseInfos;
    private ProjetsFormController projetsFormController;
    private GetTasksView getTasksView;
    private AddListController addListController;

    public GetTasksController(GetTasksView getTasksView,AddListController addListController, ControllerFromTacheAjout controllerFromTacheAjout) {
        this.getTasksView = getTasksView;
        this.gestionnaireTache = new GestionnaireTache();
        this.controllerFromTacheAjout = controllerFromTacheAjout;
        this.addListController = addListController;
        this.displayTasks();
    }

    public GetTasksController(GetTasksView getTasksView, AddProjetController addProjetController) {
        this.getTasksView = getTasksView;
        this.gestionnaireTache = new GestionnaireTache();
        this.addProjetController = addProjetController;
        this.displayTasks();
    }

    // Method to get all tasks
    public LinkedHashMap<String, String> getTasksMap() {
        LinkedHashMap<String, String> tasksMap = new LinkedHashMap<>();
        List<Document> tasks = this.gestionnaireTache.getAllTasks();
        for (Document task : tasks) {
            String id = task.getObjectId("_id").toString();
            String title = task.getString("titre");
            tasksMap.put(id, title);
        }
        return tasksMap;
    }

    // Method to handle cancel button
    public void handleCancelButton(ActionEvent eventAddList) {
        if (this.controllerFromTacheAjout != null) {
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            addTacheview view = new addTacheview(this.controllerFromTacheAjout);
            view.start(stage);
        } if(this.addProjetController != null) {
            AddProjetView view = new AddProjetView(this.addProjetController, this.projetsFormController);
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            view.start(stage);
        }
    }

    // Method to display tasks
    public void displayTasks() {
        System.out.println("Displaying tasks");
        GridCaseInfos = new LinkedHashMap<>();
        System.out.println("girdinfo");

        if (getTasksMap().isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : getTasksMap().entrySet()) {
            String taskTitle = entry.getValue();

            CheckBox checkBox = createCheckBox(taskTitle);
            this.getTasksView.getZoneTasks().add(checkBox, colCount, rowCount);

            GridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), taskTitle));
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    // Method to handle confirm button
    public void handleConfirmButton(ActionEvent eventAddList) {
        for (Node node : this.getTasksView.getZoneTasks().getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String task = checkBox.getText();
                    String id = getIdFromMap(GridCaseInfos, GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
                    System.out.println("Task selected: " + task + " with id: " + id);
                    if (this.addListController != null) {
                        this.addListController.addNewTask(id, task);
                        this.addListController.displayTasks();
                        this.closerWindow(eventAddList);
                    } if(this.addProjetController != null) {
                        this.addProjetController.addTaskToList(id, task);
                    }
                }
            }
        }
        if(this.addProjetController != null) {
            AddProjetView view = new AddProjetView(this.addProjetController, this.projetsFormController);
            Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
            view.start(stage);
        }
    }

    public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
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