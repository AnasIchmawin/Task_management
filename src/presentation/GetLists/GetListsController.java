package presentation.GetLists;

import java.util.ArrayList;
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
import metier.GestionnaireListe;
import presentation.listes.ListeFormController;

public class GetListsController {
    private LinkedHashMap<List<Integer>, List<String>> GridCaseInfos;
    private GestionnaireListe gestionnaireListe;
    private ListeFormController listeFormController;
    private GetListsView getListsView;
    private GetListsModel getListsModel;

    public GetListsController() {
        super();
    }

    public GetListsController(GetListsView getListsView, ListeFormController listeFormController) {
        this.gestionnaireListe = new GestionnaireListe();
        this.getListsView = getListsView;
        this.getListsModel = new GetListsModel();
        this.listeFormController = listeFormController;
        this.getListsView.createListGridPane(this);
    }

    public void showView() {
        Stage stage = new Stage();
        this.getListsView.start(stage);
    }

    public void handleConfirmButton(ActionEvent event) {
        this.getListsModel.setListDeleted(getSelectedLists());
        this.gestionnaireListe.supprimerListe(this.getListsModel.getListDeleted());
        List<String> listIds = new ArrayList<>();
        for (java.util.Map.Entry<String, String> entry : getListsMap().entrySet()) {
            listIds.add(entry.getKey());
        }
        this.getListsModel.setListDeleted(listIds);
        getListsView.getZoneLists().getChildren().clear();
        this.listeFormController.displayLists(false);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void displayLists(GridPane zoneLists) {
        GridCaseInfos = new LinkedHashMap<>();
        if (getListsMap().isEmpty()) {
            System.out.println("No Lists available");
            return;
        }
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : getListsMap().entrySet()) {
            String taskTitle = entry.getValue();

            CheckBox checkBox = createCheckBox(taskTitle);
            zoneLists.add(checkBox, colCount, rowCount);

            GridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), taskTitle));
            colCount++;
            if (colCount == 4) {
                colCount = 0;
                rowCount++;
            }
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

    public LinkedHashMap<String, String> getListsMap() {
        List<Document> lists = gestionnaireListe.obtenirToutesLesListes();
        LinkedHashMap<String, String> listes_Disponibles = new LinkedHashMap<>();
        for (Document list : lists) {
            if (list.containsKey("taches") && !list.getList("taches", Document.class).isEmpty()) {
                continue;
            }
            String id = list.getObjectId("_id").toString();
            String titre = list.getString("titre");
            listes_Disponibles.put(id, titre);
        }
        return listes_Disponibles;
    }

    // creer une methode pour recuperer les listes selectionnees
    public List<String> getSelectedLists() {
        List<String> selectedLists = new ArrayList<>();
        for (Map.Entry<List<Integer>, List<String>> entry : GridCaseInfos.entrySet()) {
            List<Integer> key = entry.getKey();
            List<String> value = entry.getValue();
            CheckBox checkBox = (CheckBox) getListsView.getZoneLists().getChildren().get(key.get(0) * 4 + key.get(1));
            if (checkBox.isSelected()) {
                selectedLists.add(value.get(0));
            }
        }
        return selectedLists;
    }

    public void setview(GetListsView getListsView2) {
        this.getListsView = getListsView2;
    }

}
