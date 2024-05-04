package presentation.listes;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presentation.NewList.AddListView;

public class ListeFormController {
    
    public ListeFormController() {
        super();
    }


    public void handleAjouterButtonAction(GridPane gridPane) {
        AddListView NewListFormulaire = new AddListView() ;
        Stage stage  = new Stage() ;
        NewListFormulaire.start(stage);

    }
}
