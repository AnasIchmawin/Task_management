package presentation.NewDocument;

import java.io.File;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentation.tache_detail.controleur;
import presentation.projet_detail.ProjetDetailController;

public class AddDocumentView extends Application {
    private BorderPane root;
    private TextField TitreField;
    private TextArea ZoneDescription;
    private TextField URL;
    private Button save;
    private Button browseButton;
    private Label labelTitre;
    private Label labelURL;
    private Label labelDescription;
    private Scene scene;
    private AddDocumentController controller;
    private LocalDate dateInsertion;
    private String idProjet;
    private String idTache;

    @Override
    public void start(Stage primaryStage) {
        controller = new AddDocumentController("MyDatabas", "document");

        root = new BorderPane();
        VBox mainBox = createMainBox();
        mainBox.setStyle("-fx-background-color: #112D4E;");
        root.setCenter(mainBox);
        scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("AddDocumentStyle.css").toExternalForm());
        primaryStage.setTitle("Ajouter un document");
        primaryStage.setOnCloseRequest(e -> controller.close());
        primaryStage.show();
    }

    public AddDocumentView(ProjetDetailController controller, String idProjet) {
        this.controller = new AddDocumentController("MyDatabas", "document");
        this.idProjet = idProjet;
    }

    public AddDocumentView(controleur controller, String idTache) {
        this.controller = new AddDocumentController("MyDatabas", "document");
        this.idTache = idTache;
    }

    public AddDocumentView() {
        this.controller = new AddDocumentController("MyDatabas", "document");
    }

    private VBox createMainBox() {
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(20));

        labelTitre = new Label("Titre Document");
        TitreField = new TextField();

        labelURL = new Label("URL Document");
        URL = new TextField();
        browseButton = new Button("Browse");
        browseButton.setOnAction(e -> browseFile());

        labelDescription = new Label("Description Document");
        ZoneDescription = new TextArea();

        save = createButtonWithIcon("Enregistrer", "file:./Pictures/save.png", 10, 10);
        save.setOnAction(e -> saveDocument());
        mainBox.getChildren().addAll(
                labelTitre, TitreField,
                labelURL, createURLBox(),
                labelDescription, ZoneDescription,
                save);
        style();
        return mainBox;
    }

    private HBox createURLBox() {
        HBox URLBox = new HBox(5);
        URLBox.getChildren().addAll(URL, createBrowseButton());
        return URLBox;
    }

    private Button createBrowseButton() {
        browseButton = new Button("Browse");
        browseButton.setOnAction(e -> browseFile());
        return browseButton;
    }

    private void browseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier");
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (selectedFile != null) {
            URL.setText(selectedFile.getAbsolutePath());
        }
    }

    private Button createButtonWithIcon(String name, String string, int i, int j) {
        Button button = new Button(name);
        try {
            Image icon = new Image(string);
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(i);
            iconView.setFitHeight(j);
            button.setGraphic(iconView);
            button.setOnMousePressed(event -> button.getStyleClass().add("button-clicked-style"));

            button.setOnMouseReleased(event -> button.getStyleClass().remove("button-clicked-style"));

            button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
            button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));

            button.setOnMouseEntered(event -> {
                button.getStyleClass().add("button-clicked-style");
                button.setCursor(Cursor.HAND);
            });

            button.setOnMouseExited(event -> {
                button.getStyleClass().remove("button-clicked-style");
                button.setCursor(Cursor.DEFAULT);
            });
        } catch (Exception e) {
            System.out.println("Error loading the icon: " + e.getMessage());
        }
        return button;
    }

    private void saveDocument() {
        dateInsertion = LocalDate.now();
        String titre = TitreField.getText();
        String url = URL.getText();
        String description = ZoneDescription.getText();

        DocumentModel documentModel = new DocumentModel(titre, url, description, dateInsertion, idProjet, idTache);
        controller.saveDocument(documentModel);
    }

    private void style() {
        save.getStyleClass().add("button-style");
        browseButton.getStyleClass().add("button-style-2");
        root.getStyleClass().add("main-box-style");
        ZoneDescription.getStyleClass().add("ZoneDescription-style");
        labelTitre.getStyleClass().add("label-style");
        labelURL.getStyleClass().add("label-style");
        labelDescription.getStyleClass().add("label-style");
    }
}
