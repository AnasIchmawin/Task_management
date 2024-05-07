package presentation.GetTasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.GestionnaireTache;
import presentation.NewList.AddListController;
import presentation.NewList.AddListModel;
import presentation.NewList.AddListView;
import presentation.NewProjet.AddProjetModel;
import presentation.listes.ListeFormController;

public class GetTasksController {
    private GestionnaireTache gestionnaireTache;
    private GetTasksModel getTasksModel;
    private AddListController addListController;
    private AddProjetModel addProjetModel;
    private ListeFormController listeFormController;
    private GetTasksView getTasksView;

    public GetTasksController(AddListController addListController) {
        this.gestionnaireTache = new GestionnaireTache();
        this.getTasksModel = new GetTasksModel();
        this.addListController = addListController;
        this.getTasksView = new GetTasksView(this);
    }

    // public GetTasksController(GetTasksModel getTasksModel, ListeFormController
    // listeFormController) {
    // this.gestionnaireTache = new GestionnaireTache();
    // this.getTasksModel = getTasksModel;
    // this.listeFormController = listeFormController;
    // }

    // public GetTasksController(AddProjetModel addProjetModel2, ListeFormController
    // listeFormController) {
    // this.gestionnaireTache = new GestionnaireTache();
    // this.addProjetModel = addProjetModel2;
    // this.listeFormController = listeFormController;
    // }

    public GetTasksController(AddProjetModel addListModel, ListeFormController listeFormController2) {
        //TODO Auto-generated constructor stub
    }

    // Method to get all tasks
    public Map<String, String> getTask() {
        List<Document> tasks = gestionnaireTache.getAllTasks();
        // les taches desponible sous forme de map <id, titre>
        Map<String, String> taches_Disponibles = new HashMap<>();
        for (Document document : tasks) {
            // caster le id en string
            String id = document.getObjectId("_id").toString();
            String titre = document.getString("titre");
            taches_Disponibles.put(id, titre);
        }
        return taches_Disponibles;
    }

    // Method to handle cancel button
    public void handleCancelButton(ActionEvent eventAddList) {
        AddListView view = new AddListView(this.listeFormController);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
        view.start(stage);
    }

    // Method to display tasks
    public Map<List<Integer>, String> afficherTasks(GridPane gridPane) {

        // Getting all ids tasks
        List<String> keys = new ArrayList<>(getTask().keySet());

        // If there are no tasks
        if (keys.size() == 0) {
            return null;
        }

        int colCount = 0;
        int rowCount = 0;
        int index = 0;

        ArrayList<String> titleTasks = new ArrayList<>();
        Map<List<Integer>, String> stocker_Id = new HashMap<>();

        // Getting all tasks
        for (String key : keys) {
            titleTasks.add(getTask().get(key));
        }

        // Adding tasks to the grid
        for (String task : titleTasks) {
            CheckBox checkBox = creerCheckBox(task);
            gridPane.add(checkBox, colCount, rowCount);
            stocker_Id.put(List.of(rowCount, colCount), keys.get(index));
            System.out.println("id: " + keys.get(0) + " task: " + task + " row: " + rowCount + " col: " + colCount);
            index++;
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }
        return stocker_Id;
    }

    // Method to handle confirm button
    public void handleConfirmButton(GridPane gridPane, ActionEvent eventAddList ) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    Integer rowIndex = GridPane.getRowIndex(checkBox);
                    Integer colIndex = GridPane.getColumnIndex(checkBox);
                    String Id = getIdFromMap(afficherTasks(gridPane), rowIndex, colIndex);
                    String title = checkBox.getText();
                    this.getTasksModel.addTask(Id, title);
                }
            }
        }

        this.addListController = new AddListController(this.addListController.getAddListModel(),this.getTasksModel.getTaskList());
        AddListView view = new AddListView(this.listeFormController);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
        view.start(stage);
    }

    // Method to create a checkbox
    private CheckBox creerCheckBox(String task) {
        CheckBox checkBox = new CheckBox(task);
        checkBox.setTextFill(Color.WHITE);
        checkBox.getStyleClass().add("task-checkbox-style");
        checkBox.setStyle("-fx-focus-color: transparent;");
        return checkBox;
    }

    private String getIdFromMap(Map<List<Integer>, String> map, int row, int col) {
        Set<List<Integer>> keys = map.keySet();
        for (List<Integer> key : keys) {
            if (key.get(0) == row && key.get(1) == col) {
                return map.get(key);
            }
        }
        return null;
    }
}
