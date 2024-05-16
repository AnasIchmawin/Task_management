package presentation.NewDocument;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentation.NewProjet.AddProjetController;
import presentation.seance_ajoute.SceanceAjouteController;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.tache_detail.tacheDetailController;

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
    private VBox mainBox;
    private Scene scene;
    private AddDocumentController controller;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private tacheDetailController controllerFromTacheDetail;
    private SceanceAjouteController SceanceAjouteController;
    private AddProjetController addProjetController;

    @Override
    public void start(Stage primaryStage) {
        Dessiner(primaryStage);
        Actions();
        style();
    }

    public AddDocumentView(ControllerFromTacheAjout controllerFromTacheAjout) {
        this.controller = new AddDocumentController(this, controllerFromTacheAjout);
    }

    public AddDocumentView(AddProjetController addProjetController) {
        this.controller = new AddDocumentController(this, addProjetController);
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
        save = createButton("Enregistrer", "file:./Pictures/save.png", 10, 10);
        mainBox.getChildren().addAll(labelTitre, TitreField, labelURL, createURLBox(), labelDescription, ZoneDescription, save);
        return mainBox;
    }

    private void Dessiner(Stage primaryStage) {
        root = new BorderPane();
        mainBox = createMainBox();
        root.setCenter(mainBox);
        scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ajouter un document");
        primaryStage.setOnCloseRequest(e -> controller.close());
        primaryStage.show();
    }

    private HBox createURLBox() {
        HBox URLBox = new HBox(5);
        URLBox.getChildren().addAll(URL, createBrowseButton());
        return URLBox;
    }

    private Button createBrowseButton() {
        browseButton = new Button("Browse");
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

    private Button createButton(String name, String path, int width, int height) {
        Button newButton = new Button();
        try {
            ImageView icon = new ImageView(new Image(path));
            icon.setFitWidth(width);
            icon.setFitHeight(height);
            Text buttonText = new Text(name);
            buttonText.setFill(Color.WHITE);
            HBox buttonContent = new HBox(buttonText, icon);
            buttonContent.setAlignment(Pos.CENTER);
            buttonContent.setSpacing(4);
            newButton.setGraphic(buttonContent);
            return newButton;
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du bouton : " + e.getMessage());
            return newButton;
        }
    }

    private void style() {
        save.getStyleClass().add("button-style");
        browseButton.getStyleClass().add("button-style-2");
        root.getStyleClass().add("main-box-style");
        ZoneDescription.getStyleClass().add("ZoneDescription-style");
        labelTitre.getStyleClass().add("label-style");
        labelURL.getStyleClass().add("label-style");
        labelDescription.getStyleClass().add("label-style");
        mainBox.setStyle("-fx-background-color: #112D4E;");
        scene.getStylesheets().add(getClass().getResource("AddDocumentStyle.css").toExternalForm());
    }

    public void Actions() {
        browseButton.setOnAction(e -> browseFile());
        //rendre cette == different
        //``````````````````````````````````````````````````````````````````````````````````
        if(controllerFromTacheAjout == null){
            save.setOnAction(e -> this.controller.saveDocumentFromeTacheAjout());
        }
        else if(controllerFromTacheDetail != null)
            save.setOnAction(e -> this.controller.saveDocumentFromTacheDetail());
        else if(SceanceAjouteController != null)
            save.setOnAction(e -> this.controller.saveDocumentFromeSeanceAjout());
        else if(addProjetController != null)
            save.setOnAction(e -> this.controller.saveDocumentFromProjet());
    }

    public String getTitreField() {
        return TitreField.getText();
    }

    public String getURL() {
        return URL.getText();
    }

    public String getDescription() {
        return ZoneDescription.getText();
    }

    public void close() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}