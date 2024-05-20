package mygroup.presentation.seance;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import java.awt.Desktop;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireDocument;
import mygroup.metier.Gestionnaire.GestionnaireSeance;
import mygroup.presentation.NewDocument.AddDocumentView;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projet_detail.ProjetDetailController;
import mygroup.presentation.projets.ProjetsFormView;

public class SeanceFormController {
    private SeanceFormView seanceFormView;
    private SeanceModel seanceModel;
    private GestionnaireSeance gestionnaireSeance;
    private GestionnaireDocument gestionnaireDocument;

    private static final int MAX_COLUMNS = 5;
    private static final int BUTTON_WIDTH = 170;
    private static final int BUTTON_HEIGHT = 60;
    private static final String BUTTON_STYLE = "-fx-background-color: #112D4E; "
            + "-fx-background-radius: 10px; "
            + "-fx-min-width: " + BUTTON_WIDTH + "px; "
            + "-fx-min-height: " + BUTTON_HEIGHT + "px; "
            + "-fx-text-fill: #ffffff; "
            + "-fx-font-size: 18px;";

    public SeanceFormController(SeanceFormView seanceFormView, ProjetDetailController projetDetailController) {
        this.seanceFormView = seanceFormView;
        this.gestionnaireSeance = new GestionnaireSeance();
        this.gestionnaireDocument = new GestionnaireDocument();
        this.seanceModel = new SeanceModel();
        seanceModel.setId(projetDetailController.getSeanceClicked());
        FillChamps();
        displayAllInfos();
    }

    private void FillChamps() {
        Document doc = gestionnaireSeance.getSeanceById(seanceModel.getId());

        // Extraire les attributs du document
        String titre = doc.getString("titre");
        String description = doc.getString("description");
        String note = doc.getString("note");
        String dateDebut = doc.getString("dateDebut");
        String dateFin = doc.getString("dateFin");

        // Formater les dates
        if (dateDebut != null) {
            String[] dateDebutSplit = dateDebut.split("-");
            dateDebut = dateDebutSplit[2] + "/" + dateDebutSplit[1] + "/" + dateDebutSplit[0];
        }

        if (dateFin != null) {
            String[] dateFinSplit = dateFin.split("-");
            dateFin = dateFinSplit[2] + "/" + dateFinSplit[1] + "/" + dateFinSplit[0];
        }

        LinkedHashMap<String, String> documents = new LinkedHashMap<>(); // titre, url
        if (doc.get("documents") != null && doc.get("documents") instanceof List) {
            @SuppressWarnings("unchecked")
            List<String> documentsList = (List<String>) doc.get("documents");
            for (String documentId : documentsList) {
                Document docu = gestionnaireDocument.getDocumentById(documentId);
                if (docu != null) {
                    Object docId = docu.get("_id");
                    String Id_document = docId.toString();
                    String docTitle = docu.getString("titre");
                    documents.put(Id_document, docTitle);
                } else {
                    System.err.println("Erreur: Le document pour l'ID " + documentId + " est nul.");
                }
            }
        }

        seanceModel.setDocuments(documents);
        seanceModel.setTitre(titre);
        seanceModel.setDescription(description);
        seanceModel.setNote(note);
        seanceModel.setDateDebut(dateDebut);
        seanceModel.setDateFin(dateFin);
    }

    private void displayAllInfos() {
        seanceFormView.getTitre().setText(seanceModel.getTitre());
        seanceFormView.getDescription().setText(seanceModel.getDescription());
        seanceFormView.getNoteLabel().setText(seanceModel.getNote());
        seanceFormView.getDateDebut().setText(seanceModel.getDateDebut());
        seanceFormView.getDateFin().setText(seanceModel.getDateFin());
        displayAvailableDocuments();
    }

    public void goToListes(ActionEvent event) {
        ListeFormView nextView = new ListeFormView();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextView.start(stage);
    }

    public void goToProjects(ActionEvent event) {
        ProjetsFormView nextView = new ProjetsFormView();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextView.start(stage);
    }

    public void goToArchive(ActionEvent event) {
        ArchiveFormView nextView = new ArchiveFormView();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextView.start(stage);
    }

    public void goToHome() {
        // Implémenter la logique pour aller à l'accueil
    }

    public void goToAjoutDoc(ActionEvent event) {
        AddDocumentView nextView = new AddDocumentView(this);
        Stage stage = new Stage();
        nextView.start(stage);
    }

    public void addDocToSeance(String idLastDoc, String titre) {
        seanceModel.addDocToSeance(idLastDoc, titre);
    }

    public void displayAvailableDocuments() {
        if (seanceModel.getDocuments().isEmpty()) {
            seanceFormView.getZoneDocuments().getChildren().clear();
            return;
        }
        seanceFormView.getZoneDocuments().getChildren().clear();
        System.out.println("documents: " + seanceModel.getDocuments());
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : seanceModel.getDocuments().entrySet()) {
            Button newListButton = createDocButton(entry.getValue());
            newListButton.setOnAction(event -> {
                String docTitle = newListButton.getText();
                String docUrl = seanceModel.getDocuments().get(docTitle);
                System.out.println("Ouverture du document : " + docUrl);

                // Vérifier si Desktop est pris en charge par la plateforme
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        // Vérifiez si le docUrl est un fichier local ou une URL
                        if (docUrl.startsWith("http://") || docUrl.startsWith("https://")) {
                            URI uri = new URI(docUrl);
                            desktop.browse(uri);
                        } else {
                            File file = new File(docUrl);
                            desktop.open(file);
                        }
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // Si Desktop n'est pas pris en charge, afficher un message d'erreur
                    System.out.println("Desktop n'est pas pris en charge");
                }
            });
            addtaskbutton(newListButton, colCount, rowCount);

            if (++colCount == MAX_COLUMNS) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    private void addtaskbutton(Button newListButton, int colCount, int rowCount) {
        seanceFormView.getZoneDocuments().add(newListButton, colCount, rowCount);
    }

    private Button createDocButton(String title) {
        Button newListButton = new Button(title);
        setButtonStyle(newListButton);
        addMouseEvents(newListButton);
        addListIcon(newListButton);
        return newListButton;
    }

    private void setButtonStyle(Button button) {
        button.setStyle(BUTTON_STYLE);
    }

    private void addMouseEvents(Button button) {
        button.setOnMouseEntered(event -> {
            button.setCursor(javafx.scene.Cursor.HAND);
        });

        button.setOnMouseExited(event -> {
            button.setStyle(BUTTON_STYLE);
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
    }

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

    public void handleSaveButtonAction() {

    }
}
