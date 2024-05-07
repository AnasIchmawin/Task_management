package presentation.listes;

import metier.GestionnaireListe;
import java.util.LinkedHashMap;

import java.util.HashMap;
import java.util.List;
import org.bson.Document;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import presentation.NewList.AddListView;

public class ListeFormController {
    private GestionnaireListe gestionnaireListe;
    private ListeFormView listView;
    private ListModel listModel;
    private HashMap<List<Integer>, List<String>> gridCaseInfos;

    public ListeFormController(ListeFormView listeFormView) {
        this.gestionnaireListe = new GestionnaireListe();
        this.listView = listeFormView;
        this.listModel = new ListModel(getListMap());
    }

    // Affiche le formulaire d'ajout de liste
    public void handleAjouterButtonAction() {
        AddListView newListFormulaire = new AddListView(this);
        Stage stage = new Stage();
        newListFormulaire.start(stage);
    }

    // Affiche les listes triées
    public void handleOrdonnerButton() {
        this.displayLists(true);
    }

    // Affiche les listes dans la vue liste
    public void displayLists(boolean isSorted) {
        listModel.setLists(getListMap());
        if(isSorted) {
            listModel.sortLists();
        }
        this.listView.getZoneListes().getChildren().clear();
        gridCaseInfos = new LinkedHashMap<>(); // Utilisation de LinkedHashMap
        int colCount = 0;
        int rowCount = 0;
        if (this.listModel.getLists() == null) {
            return;
        }
        for (java.util.Map.Entry<String, String> entry : this.listModel.getLists().entrySet()) {
            // Tester si une node existe déjà
            Button newListButton = createListButton(entry.getValue());
            while (this.listView.getZoneListes().getChildren().contains(newListButton)) {
                colCount++;
                if (colCount == 5) {
                    colCount = 0;
                    rowCount++;
                }
                newListButton = createListButton(entry.getValue());
            }
            this.listView.getZoneListes().add(newListButton, colCount, rowCount);
            gridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), entry.getValue()));
            colCount++;
            if (colCount == 5) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    // Method to get all tasks
    public LinkedHashMap<String, String> getListsMap() {
        List<Document> listes = gestionnaireListe.obtenirToutesLesListes();
        LinkedHashMap<String, String> listAvailable = new LinkedHashMap<>();
        for (Document liste : listes) {
            String id = liste.getObjectId("_id").toString();
            String titre = liste.getString("titre");
            listAvailable.put(id, titre);
        }
        return listAvailable;
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

    // Crée un bouton pour une liste
    private Button createListButton(String title) {
        Button newListButton = new Button(title);
        newListButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 170px; " +
                "-fx-min-height: 60px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");
        newListButton.setOnMouseEntered(event -> {
            newListButton.setStyle("-fx-background-color: #8E9EB2; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 170px; " +
                    "-fx-min-height: 60px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 18px;");
            newListButton.setCursor(javafx.scene.Cursor.HAND);
        });

        newListButton.setOnMouseExited(event -> {
            newListButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 170px; " +
                    "-fx-min-height: 60px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 18px;");
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

}