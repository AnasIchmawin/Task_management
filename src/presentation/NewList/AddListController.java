
package presentation.NewList;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import presentation.GetTasks.GetTasksController;
import presentation.GetTasks.GetTasksView;


public class AddListController {
    private GetTasksController getTasksController;
    private GetTasksView getTasksView;

    public AddListController(){
        super() ;
    }

    public AddListController(GetTasksController getTasksController, GetTasksView getTasksView) {
        this.getTasksController = getTasksController;
        this.getTasksView = getTasksView;
    }


    public void handleAjouterButtonAction() {
        System.out.println("enregeistrer");
    }

    public void saveInfosListe(String titre, String description, ArrayList<String> taches) {
        System.out.println("saver les donner dans la base de donnees .");
    }

    public void getTasksView(ActionEvent event) {
        // Créer une instance de GetTasksView
        getTasksView = new GetTasksView(this.getTasksController);
        // Récupérer la fenêtre actuelle
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Afficher GetTasksView
        getTasksView.showView(stage, event);
    }
    

}