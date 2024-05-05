package presentation.listes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.GestionnaireListe;
import presentation.NewList.AddListView;

public class ListeFormController {
    GestionnaireListe gestionnaireListe;

    public ListeFormController() {
        super();
        this.gestionnaireListe = new GestionnaireListe();
    }

    public void handleAjouterButtonAction(GridPane gridPane) {
        AddListView NewListFormulaire = new AddListView();
        Stage stage = new Stage();
        NewListFormulaire.start(stage);

    }

    public void AfficheListes(GridPane gridPane) {
        List<Document> listes = gestionnaireListe.obtenirToutesLesListes();
        List<String> titresListes = new ArrayList<>();

        for (Document liste : listes) {
            String titre = liste.getString("titre");
            titresListes.add(titre);
        }
        for (String title : titresListes) {
            Button newListButton = new Button(title);
            newListButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 170px; " +
                    "-fx-min-height: 60px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 18px;");

            int colIndex = gridPane.getChildren().size() % 6; // Calculating column index
            int rowIndex = gridPane.getChildren().size() / 6; // Calculating row index

            // Load list icon
            try {
                Image listIcon = new Image("file:./Pictures/to-do.png");
                ImageView listIconView = new ImageView(listIcon);
                listIconView.setFitWidth(15);
                listIconView.setFitHeight(15);
                newListButton.setGraphic(listIconView);
                gridPane.add(newListButton, colIndex, rowIndex);

            } catch (Exception e) {
                System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
            }
        }
    }

}
