package presentation.GetTasks;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import metier.GestionnaireTache;
import presentation.NewList.AddListView;

public class GetTasksController {

    private GestionnaireTache gestionnaireTache;

    public GetTasksController() {
        this.gestionnaireTache = new GestionnaireTache();
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
        AddListView view = new AddListView();
        Stage stage = (Stage) ((Node) eventAddList.getSource()).getScene().getWindow(); // Récupérer la fenêtre actuelle
        view.start(stage);

    }

}
