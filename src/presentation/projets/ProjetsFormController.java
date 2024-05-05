package presentation.projets;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import metier.GestionnaireProjet;
import presentation.NewList.AddListView;

public class ProjetsFormController {

    private GestionnaireProjet gestionnaireProjet;

    public ProjetsFormController() {
        super();
        this.gestionnaireProjet = new GestionnaireProjet();
    }

    public static void handleAjouterButtonAction(GridPane gridPane) {
        AddListView newProjectForm = new AddListView();
        Stage stage = new Stage();
        newProjectForm.start(stage);
    }

    public void AfficheProjets(GridPane gridPane) {
        List<Document> projets = gestionnaireProjet.obtenirTousLesProjets();
        List<String> titresProjets = new ArrayList<>();

        for (Document projet : projets) {
            String titre = projet.getString("titre");
            titresProjets.add(titre);
        }
        for (String title : titresProjets) {
            Button newProjectButton = new Button(title);
            newProjectButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 170px; " +
                    "-fx-min-height: 60px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 18px;");

            int colIndex = gridPane.getChildren().size() % 6; // Calcul de la colonne
            int rowIndex = gridPane.getChildren().size() / 6; // Calcul de la ligne

            // Charger l'icône de la liste
            try {
                Image listIcon = new Image("file:./Pictures/to-do.png");
                ImageView listIconView = new ImageView(listIcon);
                listIconView.setFitWidth(15);
                listIconView.setFitHeight(15);
                newProjectButton.setGraphic(listIconView);
                gridPane.add(newProjectButton, colIndex, rowIndex);

            } catch (Exception e) {
                System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
            }
        }
    }
}
