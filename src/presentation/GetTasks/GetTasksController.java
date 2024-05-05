package presentation.GetTasks;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.GestionnaireTache;
import presentation.NewList.AddListModel;
import presentation.NewList.AddListView;
import presentation.listes.ListeFormController;

public class GetTasksController {
    private GestionnaireTache gestionnaireTache;
    private AddListModel addListModel;
    private ListeFormController listeFormController;

    public GetTasksController() {
        this.gestionnaireTache = new GestionnaireTache();
        this.addListModel = new AddListModel("", "", new ArrayList<>());
    }

    public GetTasksController(AddListModel addListModel, ListeFormController listeFormController) {
        this.gestionnaireTache = new GestionnaireTache();
        this.addListModel = addListModel;
        this.listeFormController = listeFormController;
    }

    public List<String> getTaskTitles() {
        List<Document> tasks = gestionnaireTache.getAllTasks();
        List<String> titles = new ArrayList<>();
        for (Document task : tasks) {
            titles.add(task.getString("titre"));
        }
        return titles;
    }

    public void handleCancelButton(ActionEvent eventAddList) {
        AddListView view = new AddListView(this.addListModel, this.listeFormController);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
        view.start(stage);
    }

    public void afficherTasks(GridPane gridPane) {
        int colCount = 0;
        int rowCount = 0;
        ArrayList<String> tasksList = new ArrayList<>(this.getTaskTitles());
        for (String task : tasksList) {
            CheckBox checkBox = creerCheckBox(task);
            gridPane.add(checkBox, colCount, rowCount);
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    private CheckBox creerCheckBox(String task) {
        CheckBox checkBox = new CheckBox(task);
        checkBox.setTextFill(Color.WHITE);
        checkBox.getStyleClass().add("task-checkbox-style");
        checkBox.setStyle("-fx-focus-color: transparent;");
        return checkBox;
    }

    public void handleConfirmButton(GridPane gridPane, ActionEvent eventAddList) {
        List<String> selectedTitles = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String title = checkBox.getText();
                    selectedTitles.add(title);
                }
            }
        }
        this.addListModel.setTitreSelectionnes(selectedTitles);
        AddListView view = new AddListView(this.addListModel, this.listeFormController);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow();
        view.start(stage);
    }
}
