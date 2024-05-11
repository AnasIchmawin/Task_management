package presentation.listes;

import metier.GestionnaireListe;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import presentation.GetLists.GetListsView;
import presentation.NewList.AddListView;
import presentation.archive.ArchiveFormController;
import presentation.archive.ArchiveFormView;
import presentation.projets.ProjetsFormView;
import presentation.taches.TachesFormController;
import presentation.taches.TachesFormView;

public class ListeFormController {
    private static final int MAX_COLUMNS = 5;
    private static final int BUTTON_WIDTH = 170;
    private static final int BUTTON_HEIGHT = 60;
    private static final String BUTTON_STYLE = "-fx-background-color: #112D4E; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;";

    private String buttonclickedInfos ;
    private GestionnaireListe gestionnaireListe;
    private ListeFormView listView;
    private ListModel listModel;
    private Map<List<Integer>, List<String>> gridCaseInfos;

    public ListeFormController(ListeFormView listeFormView) {
        this.gestionnaireListe = new GestionnaireListe();
        this.listView = listeFormView;
        this.listModel = new ListModel(getListMap());
    }

    public void handleAjouterButtonAction() {
        AddListView newListFormulaire = new AddListView(this);
        Stage stage = new Stage();
        newListFormulaire.start(stage);
    }

    public void handleOrdonnerButton() {
        displayLists(true);
    }

    public void handleProjectsButton() {
        Stage stage = (Stage) listView.getZoneListes().getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

    public void handleSupprimerButtonAction() {
        GetListsView getListview = new GetListsView(this);
        getListview.start(new Stage());
    }

    public void handleArchiveButton() {
        Stage stage = (Stage) listView.getZoneListes().getScene().getWindow();
        ArchiveFormView archiveFormView = new ArchiveFormView();
        archiveFormView.start(stage);
    }

    public void displayLists(boolean isSorted) {
        listModel.setLists(getListMap());
        if (isSorted) {
            listModel.sortLists();
        }
        listView.getZoneListes().getChildren().clear();
        gridCaseInfos = new LinkedHashMap<>();
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : listModel.getLists().entrySet()) {
            Button newListButton = createListButton(entry.getValue());
            newListButton.setOnAction(event -> handleButtoListnAction(newListButton));
            addListButton(newListButton, colCount, rowCount);
            gridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), entry.getValue()));

            if (++colCount == MAX_COLUMNS) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    //recuperer les informations du bouton cliqué (ID de la liste)
    private void handleButtoListnAction(Button newListButton) {
        List<String> listInfos = gridCaseInfos.get(List.of(this.listView.getZoneListes().getRowIndex(newListButton), this.listView.getZoneListes().getColumnIndex(newListButton)));
        String Id = listInfos.get(0);
        System.out.println("ID de la liste : " + Id);
        TachesFormController tachesFormController = new TachesFormController(Id);
        TachesFormView tachesFormView = new TachesFormView();
        Stage stage = (Stage) listView.getZoneListes().getScene().getWindow();
        tachesFormView.start(stage);
    
    }

    private Button createListButton(String title) {
        Button newListButton = new Button(title);
        newListButton.setStyle(BUTTON_STYLE);

        newListButton.setOnMouseEntered(event -> {
            newListButton.setStyle("-fx-background-color: #8E9EB2; "
                    + "-fx-background-radius: 10px; "
                    + "-fx-min-width: " + BUTTON_WIDTH + "px; "
                    + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
                    + "-fx-text-fill: #ffffff; "
                    + "-fx-font-size: 18px;");
            newListButton.setCursor(javafx.scene.Cursor.HAND);
        });

        newListButton.setOnMouseExited(event -> {
            newListButton.setStyle(BUTTON_STYLE);
            newListButton.setCursor(javafx.scene.Cursor.DEFAULT);
        });

        try {
            Image listIcon = new Image("file:./Pictures/to-do.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newListButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newListButton;
    }

    private void addListButton(Button newListButton, int colCount, int rowCount) {
        listView.getZoneListes().add(newListButton, colCount, rowCount);
    }

    private LinkedHashMap<String, String> getListMap() {
        List<Document> listes = gestionnaireListe.obtenirToutesLesListes();
        LinkedHashMap<String, String> listMap = new LinkedHashMap<>();
        for (Document liste : listes) {
            String id = liste.getObjectId("_id").toString();
            String titre = liste.getString("titre");
            listMap.put(id, titre);
        }
        return listMap;
    }

    public void searchList(String searchText) {
        int colCount = 0;
        int rowCount = 0;

        listView.getZoneListes().getChildren().clear();

        for (Map.Entry<String, String> entry : listModel.getLists().entrySet()) {
            String buttonTitle = entry.getValue().toLowerCase();
            if (buttonTitle.contains(searchText.toLowerCase())) {
                Button button = createListButton(entry.getValue());
                addListButton(button, colCount, rowCount);
                gridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), entry.getValue()));
                colCount++;
                if (colCount == MAX_COLUMNS) {
                    colCount = 0;
                    rowCount++;
                }
            }
        }
    }
}
