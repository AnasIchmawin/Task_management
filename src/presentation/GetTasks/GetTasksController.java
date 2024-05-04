package presentation.GetTasks;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.GestionnaireTache;
import presentation.NewList.AddListModel;
import presentation.NewList.AddListView;

public class GetTasksController {


    private GestionnaireTache gestionnaireTache;
    private AddListModel addListModel;

    public GetTasksController() {
        this.gestionnaireTache = new GestionnaireTache();
        this.addListModel = new AddListModel("", "", new ArrayList<>()) ;
    }

    public GetTasksController(AddListModel addListModel) {
        this.gestionnaireTache = new GestionnaireTache();
        this.addListModel = addListModel;
    }

    // recupere les titres des taches :
    public List<String> getTaskTitles() {
        List<Document> tasks = gestionnaireTache.getAllTasks();
        List<String> titles = new ArrayList<>();
        for (Document task : tasks) {
            titles.add(task.getString("titre"));
        }
        return titles;
    }

    public void handleCancelButton(ActionEvent eventAddList) {
        AddListView view = new AddListView(this.addListModel);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow(); // Récupérer la fenêtre actuelle
        view.start(stage);

    }

    public void AfficherTasks(GridPane gridPane) {
        int colCount = 0;
        int rowCount = 0;
        ArrayList<String> tasksList = new ArrayList<>(this.getTaskTitles());
        for (String task : tasksList) {
            CheckBox checkBox = new CheckBox(task);
            checkBox.setTextFill(Color.WHITE);
            checkBox.getStyleClass().add("task-checkbox-style");
            checkBox.setStyle("-fx-focus-color: transparent;");

            gridPane.add(checkBox, colCount, rowCount);

            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    public void handleConfirmButton(GridPane gridPane , ActionEvent eventAddList) {
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

        // l'envoie du selectedTitles au AddList ;
       
        this.addListModel.setTitreSelectionnes(selectedTitles);
        
        AddListView view = new AddListView(this.addListModel);
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow(); // Récupérer la fenêtre actuelle
        view.start(stage);
        
    }
}
