package presentation.listes;

import javafx.scene.layout.GridPane;
import presentation.NewList.AddListView;

public class ListeFormController {

    private AddListView addListView;

    public ListeFormController() {
        super();
    }

    public ListeFormController(AddListView addListView) {
        this.addListView = addListView;
    }

    public void handleAjouterButtonAction(GridPane gridPane) {
        addListView.showView();
    }
}
