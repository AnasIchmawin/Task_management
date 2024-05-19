package mygroup.presentation.listes;

import mygroup.metier.Gestionnaire.GestionnaireListe;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mygroup.presentation.GetLists.GetListsView;
import mygroup.presentation.NewList.AddListView;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.projets.ProjetsFormView;
import mygroup.presentation.taches.TachesFormView;

public class ListeFormController {
    private GestionnaireListe gestionnaireListe;
    private ListeFormView listView;
    private ListModel listModel;

    private static final int MAX_COLUMNS = 5;
    private static final int BUTTON_WIDTH = 170;
    private static final int BUTTON_HEIGHT = 60;
    private static final String BUTTON_STYLE = "-fx-background-color: #112D4E; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;";

    public ListeFormController(ListeFormView listeFormView) {
        this.gestionnaireListe = new GestionnaireListe();
        this.listView = listeFormView;
        this.listModel = new ListModel(getMapList(), new LinkedHashMap<>());
        this.displayAvailableLists(false);
    }

    // navigation vers la page de création de liste
    public void handleAjouterButtonAction() {
        AddListView newListFormulaire = new AddListView(this);
        Stage stage = new Stage();
        newListFormulaire.start(stage);
    }

    // afficher les listes ordonnées par ordre alphabétique
    public void handleOrdonnerButton() {
        displayAvailableLists(true);
    }

    // navigation vers la page de projets
    public void handleProjectsButton() {
        Stage stage = (Stage) listView.getZoneListes().getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

    // navigation vers la page d'archivage
    public void handleArchiveButton() {
        Stage stage = (Stage) listView.getZoneListes().getScene().getWindow();
        ArchiveFormView archiveFormView = new ArchiveFormView();
        archiveFormView.start(stage);
    }

    // navigation vers la page de suppression de liste
    public void handleSupprimerButtonAction() {
        GetListsView getListview = new GetListsView(this);
        getListview.start(new Stage());
    }

    // afficher les listes
    public void displayAvailableLists(boolean isSorted) {
        if (isSorted) {
            listModel.sortListsByTitle();
        }
        // clear the gridpane
        listView.getZoneListes().getChildren().clear();
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : listModel.getLists().entrySet()) {
            Button newListButton = createListButton(entry.getValue());
            newListButton.setOnAction(event -> handleButtoListnAction(newListButton));
            addListButton(newListButton, colCount, rowCount);
            listModel.putInGridInfoCase(rowCount, colCount, entry.getKey());

            if (++colCount == MAX_COLUMNS) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    public void removeList(List<String> listId) {
        gestionnaireListe.deleteList(listId);
        listModel.removeList(listId);
        displayAvailableLists(false);
    }

    public String getListId() {
        return listModel.getListID();
    }

    // navigation vers la page de tâches
    private void handleButtoListnAction(Button newListButton) {
        String listId = getListIdFromButton(newListButton);
        listModel.setListID(listId);
        startTachesFormView();
    }

    // récupérer l'ID de la liste à partir du bouton
    private String getListIdFromButton(Button button) {
        List<List<String>> caseInfo = new LinkedList<>();
        caseInfo.add(Arrays.asList(GridPane.getRowIndex(button).toString(),
                GridPane.getColumnIndex(button).toString()));
        return listModel.getGridInfoCase().get(caseInfo);
    }

    // navigation vers la page de tâches
    private void startTachesFormView() {
        TachesFormView tachesFormView = new TachesFormView(this);
        Stage stage = (Stage) listView.getZoneListes().getScene().getWindow();
        tachesFormView.start(stage);
    }

    // ajout de bouton à la grille
    private void addListButton(Button newListButton, int colCount, int rowCount) {
        listView.getZoneListes().add(newListButton, colCount, rowCount);
    }

    // recuperer les listes de la base de données
    private LinkedHashMap<String, String> getMapList() {
        List<Document> listes = gestionnaireListe.getAllLists();
        LinkedHashMap<String, String> listInfos = new LinkedHashMap<>();
        for (Document liste : listes) {
            String id = liste.getObjectId("_id").toString();
            String titre = liste.getString("titre");
            listInfos.put(id, titre);
        }
        return listInfos;
    }

    // recherche de liste
    public void searchList(String searchText) {
        int colCount = 0;
        int rowCount = 0;

        listView.getZoneListes().getChildren().clear();

        for (Map.Entry<String, String> entry : listModel.getLists().entrySet()) {
            String buttonTitle = entry.getValue().toLowerCase();
            if (buttonTitle.contains(searchText.toLowerCase())) {
                Button button = createListButton(entry.getValue());
                addListButton(button, colCount, rowCount);
                listModel.putInGridInfoCase(rowCount, colCount, entry.getKey());
                colCount++;
                if (colCount == MAX_COLUMNS) {
                    colCount = 0;
                    rowCount++;
                }
            }
        }
    }

    // création d'un bouton pour chaque liste
    private Button createListButton(String title) {
        Button newListButton = new Button(title);
        setButtonStyle(newListButton);
        addMouseEvents(newListButton);
        addListIcon(newListButton);
        return newListButton;
    }

    // ajout de l'icône de la liste
    private void addListIcon(Button button) {
        try {
            Image listIcon = new Image("file:./mygroup/src/main/java/Pictures/to-do.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            button.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }
    }

    // style du bouton
    private void setButtonStyle(Button button) {
        button.setStyle(BUTTON_STYLE);
    }

    // événements de souris
    private void addMouseEvents(Button button) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #8E9EB2; "
                    + "-fx-background-radius: 10px; "
                    + "-fx-min-width: " + BUTTON_WIDTH + "px; "
                    + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
                    + "-fx-text-fill: #ffffff; "
                    + "-fx-font-size: 18px;");
            button.setCursor(javafx.scene.Cursor.HAND);
        });

        button.setOnMouseExited(event -> {
            button.setStyle(BUTTON_STYLE);
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
    }

    public void addList() {
        Document list = gestionnaireListe.getLastList();
        String id = list.getObjectId("_id").toString();
        String titre = list.getString("titre");
        listModel.addList(id, titre);
        displayAvailableLists(false);
    }
}
