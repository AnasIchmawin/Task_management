package presentation.GetDocument;

import java.io.PrintStream;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.seance_ajoute.SceanceAjouteController;

public class GetDocView {

    private GetDocController controller;
    private SceanceAjouteController sceanceAjouteController;
    private Label titleLabel;
    private BorderPane root;
    private Button confirmButton;
    private Button cancelButton;
    private ScrollPane scrollPane;
    GridPane ZoneDocuments;

    public GetDocView(SceanceAjouteController sceanceAjouteController) {
        this.sceanceAjouteController = sceanceAjouteController;
        this.controller = new GetDocController(this, this.sceanceAjouteController);
        init();
        style();
        action();
    }

    public void start(Stage primaryStage) {
        VBox taskContainer = createVBox(5, Pos.TOP_CENTER);
        taskContainer.setPadding(new Insets(20, 0, 0, 20));

        // Adding ScrollPane to the center of BorderPane
        root.setCenter(scrollPane);

        HBox buttonContainer = createHBox(25, Pos.CENTER);
        buttonContainer.getChildren().addAll(confirmButton, cancelButton);

        root.setTop(titleLabel);
        root.setBottom(buttonContainer);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        Insets buttonMargin = new Insets(0, 0, 20, 0);

        // Applying margins to button container
        BorderPane.setMargin(buttonContainer, buttonMargin);

        Scene scene = new Scene(root, 550, 520);
        scene.getStylesheets().add(getClass().getResource("GetDoc.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liste des documents");
        primaryStage.show();
    }

    private void init() {
        titleLabel = new Label("Liste des documents");
        root = new BorderPane();
        confirmButton = createButton("Confirmer", "file:./Pictures/confirmer.png", 16, 16);
        cancelButton = createButton("Annuler", "file:./Pictures/annuler.png", 20, 20);
        scrollPane = createScrollPane(createTaskGridPane());
    }

    private void style() {
        titleLabel.getStyleClass().add("titleLabel-style");
        root.getStyleClass().add("root-style");
        confirmButton.getStyleClass().add("footBtn-style");
        cancelButton.getStyleClass().add("footBtn-style");
        scrollPane.getStyleClass().add("scroll-pane-style");
    }

    public void showView(Stage primaryStage, ActionEvent event) {
        start(primaryStage);
    }

    private GridPane createTaskGridPane() {
        ZoneDocuments = new GridPane();
        ZoneDocuments.getStyleClass().add("scroll-pane-style");
        ZoneDocuments.setAlignment(Pos.TOP_CENTER);
        ZoneDocuments.setHgap(20);
        ZoneDocuments.setVgap(15);
        ZoneDocuments.setPadding(new Insets(20));
        this.controller.diplayDocs();

        return this.ZoneDocuments;
    }

    private VBox createVBox(int spacing, Pos position) {
        VBox vbox = new VBox();
        vbox.setSpacing(spacing);
        vbox.setAlignment(position);
        return vbox;
    }

    private HBox createHBox(int spacing, Pos position) {
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        hbox.setAlignment(position);
        return hbox;
    }

    private Button createButton(String name, String path, int width, int height) {
        Button newButton = new Button();

        ImageView icon = new ImageView(new Image(path));
        icon.setFitWidth(width);
        icon.setFitHeight(height);

        Text buttonText = new Text(name);
        buttonText.setFill(Color.WHITE);

        HBox buttonContent = new HBox(buttonText, icon);
        buttonContent.setAlignment(Pos.CENTER);
        buttonContent.setSpacing(10);

        newButton.setGraphic(buttonContent);

        return newButton;
    }

    private ScrollPane createScrollPane(GridPane content) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return scrollPane;
    }

    private void action() {
        confirmButton.setOnAction(event -> {
            this.controller.handleConfirmButton(event);
        });

        cancelButton.setOnAction(event -> {
            this.controller.handleCancelButtonAction(event);
        });

    }

    public GridPane getZoneDocuments() {
        return ZoneDocuments;
    }

}
