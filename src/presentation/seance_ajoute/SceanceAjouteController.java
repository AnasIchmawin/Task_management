// package presentation.seance_ajoute;

// import java.awt.Button;
// import java.util.Map.Entry;

// import javafx.stage.Stage;
// import presentation.GetDocument.GetDocModel;
// import presentation.GetDocument.GetDocView;
// import presentation.archive.ArchiveFormController;
// import presentation.archive.ArchiveFormView;

// import presentation.listes.ListeFormView;
// import presentation.projets.ProjetsFormView;

// public class SceanceAjouteController {
//     private static final int MAX_COLUMNS = 5;
//     private static final int BUTTON_WIDTH = 170;
//     private static final int BUTTON_HEIGHT = 60;
//     private static final String BUTTON_STYLE = "-fx-background-color: #112D4E; "
//             + "-fx-background-radius: 10px; "
//             + "-fx-min-width: " + BUTTON_WIDTH + "px; "
//             + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
//             + "-fx-text-fill: #ffffff; "
//             + "-fx-font-size: 18px;";
//     private SceanceAjouteView seancAjouterView;
//     private GetDocModel model;

//     public SceanceAjouteController(SceanceAjouteView seanceAjouterView) {
//         this.seancAjouterView = seanceAjouterView;
//         this.model = new GetDocModel();
//     }

//     public void handleListesButton() {
//         ListeFormView listeFormView = new ListeFormView();
//         Stage stage = this.seancAjouterView.getStage();
//         listeFormView.start(stage);
//     }

//     public void handleProjetsButton() {
//         ProjetsFormView projets = new ProjetsFormView();
//         Stage stage = this.seancAjouterView.getStage();
//         projets.start(stage);
//     }

//     public void handleArchiveButton() {
//         ArchiveFormController archiveController = new ArchiveFormController();
//         ArchiveFormView archive = new ArchiveFormView(archiveController);
//         Stage stage = this.seancAjouterView.getStage();
//         archive.start(stage);
//     }

//     public void handleAjouterButtonAction() {
//         GetDocView view = new GetDocView(this);
//         Stage stage = new Stage();
//         view.start(stage);
//     }

//     public void addDoctoSeance(String id, String doc) {
//         this.model.addDocumentToSeance(id, doc);
//         System.out.println("Document added to seance"+doc);
//     }

//     public void displayDocuments() {
//         for (String doc : this.model.getListOfDocuments().values()) {
//             Button button = new Button(doc);
//             button.setStyle(BUTTON_STYLE);;
//         }
//     }
// }

package presentation.seance_ajoute;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presentation.GetDocument.GetDocModel;
import presentation.GetDocument.GetDocView;
import presentation.archive.ArchiveFormController;
import presentation.archive.ArchiveFormView;
import presentation.listes.ListeFormView;
import presentation.projets.ProjetsFormView;

public class SceanceAjouteController {
    private static final int BUTTON_WIDTH = 170;
    private static final int BUTTON_HEIGHT = 60;
    private static final String BUTTON_STYLE = "-fx-background-color: #112D4E; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;";
    private SceanceAjouteView seanceAjouteView;
    private GetDocModel model;

    public SceanceAjouteController(SceanceAjouteView seanceAjouteView) {
        this.seanceAjouteView = seanceAjouteView;
        this.model = new GetDocModel();
    }

    public void handleListesButton() {
        ListeFormView listeFormView = new ListeFormView();
        Stage stage = this.seanceAjouteView.getStage();
        listeFormView.start(stage);
    }

    public void handleProjetsButton() {
        ProjetsFormView projets = new ProjetsFormView();
        Stage stage = this.seanceAjouteView.getStage();
        projets.start(stage);
    }

    public void handleArchiveButton() {
        ArchiveFormController archiveController = new ArchiveFormController();
        ArchiveFormView archive = new ArchiveFormView(archiveController);
        Stage stage = this.seanceAjouteView.getStage();
        archive.start(stage);
    }

    public void handleAjouterButtonAction() {
        GetDocView view = new GetDocView(this);
        Stage stage = new Stage();
        view.start(stage);
    }

    public void addDocToSeance(String id, String doc) {
        this.model.addDocumentToSeance(id, doc);
        System.out.println("Document added to seance: " + doc);
    }

    public void displayDocuments() {
        List<String> mesTaches = new ArrayList<>(this.model.getListOfDocuments().values());

        for (String doc : this.model.getListOfDocuments().values()) {
            Button newTaskButton = createDocButton(doc);
            int colIndex = this.seanceAjouteView.getZoneDocuments().getChildren().size() % 6; // Calculating column
                                                                                              // index
            int rowIndex = this.seanceAjouteView.getZoneDocuments().getChildren().size() / 6; // Calculating row index
            this.seanceAjouteView.getZoneDocuments().add(newTaskButton, colIndex, rowIndex);
        }
    }

    private Button createDocButton(String doc) {
        Button newTaskButton = new Button(doc);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./Pictures/document.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }
}
