package presentation.listes;

import metier.GestionnaireListe;

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

    public ListeFormController(ListeFormView listeFormView) {
        this.gestionnaireListe = new GestionnaireListe();
        this.listView = listeFormView;
    }

    public void handleAjouterButtonAction() {
        AddListView newListFormulaire = new AddListView(this);
        Stage stage = new Stage();
        newListFormulaire.start(stage);
    }

    public void afficheListes() {
        this.listView.ZoneListes.getChildren().clear();
        List<Document> listes = gestionnaireListe.obtenirToutesLesListes();

        for (Document liste : listes) {
            String titre = liste.getString("titre");
            Button newListButton = createListButton(titre);
            int colIndex = this.listView.ZoneListes.getChildren().size() % 5; // Calculating column index
            int rowIndex = this.listView.ZoneListes.getChildren().size() / 5; // Calculating row index
            this.listView.ZoneListes.add(newListButton, colIndex, rowIndex);
        }
    }

    private Button createListButton(String title) {
        Button newListButton = new Button(title);
        newListButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 170px; " +
                "-fx-min-height: 60px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

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
