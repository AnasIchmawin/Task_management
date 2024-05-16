package mygroup.presentation.GetProjets;

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
import mygroup.presentation.projets.ProjetsFormController;

public class GetProjetsView {

    private GetProjetsController controller;
    private ProjetsFormController projetFormController ;
    private Label Question;
    private BorderPane root;
    private Button confirmButton;
    private Button cancelButton;
    private ScrollPane scrollPane;
    GridPane ZoneProjets;

    public GetProjetsView(ProjetsFormController projetFormController ) {
        this.projetFormController = projetFormController ;
        init();
        style();
        action();
    }

    public void start(Stage primaryStage) {
        VBox projetContainer = createVBox(5, Pos.TOP_CENTER);
        projetContainer.setPadding(new Insets(20, 0, 0, 20));

        // Adding ScrollPane to the center of BorderPane
        root.setCenter(scrollPane);

        HBox buttonContainer = createHBox(25, Pos.CENTER);
        buttonContainer.getChildren().addAll(confirmButton, cancelButton);

        VBox TopContainer = createVBox(5, Pos.TOP_CENTER);
        TopContainer.getChildren().addAll(Question);
        TopContainer.setPadding(new Insets(15));

        root.setTop(TopContainer);
        root.setBottom(buttonContainer);

        Insets buttonMargin = new Insets(0, 0, 20, 0);

        // Applying margins to button container
        BorderPane.setMargin(buttonContainer, buttonMargin);

        Scene scene = new Scene(root, 550, 520);
        scene.getStylesheets().add(getClass().getResource("GetProjet-Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Les projets");
        primaryStage.show();
    }

    private void init() {
        Question = new Label("quelle liste voulez-vous supprimer ?");
        root = new BorderPane();
        confirmButton = createButton("Confirmer", "file:./mygroup/src/main/java/Pictures/confirmer.png", 16, 16);
        cancelButton = createButton("Annuler", "file:./mygroup/src/main/java/Pictures/annuler.png", 20, 20);
        this.controller = new GetProjetsController(this , this.projetFormController);
        scrollPane = createScrollPane(createProjetGridPane(this.controller));

    }

    private void style() {
        Question.getStyleClass().add("titleLabel-style");
        root.getStyleClass().add("root-style");
        confirmButton.getStyleClass().add("footBtn-style");
        cancelButton.getStyleClass().add("footBtn-style");
        scrollPane.getStyleClass().add("scroll-pane-style");
    }

    public void showView(Stage primaryStage, ActionEvent event) {
        start(primaryStage);
    }

    public GridPane createProjetGridPane(GetProjetsController controller1) {
        ZoneProjets = new GridPane();
        ZoneProjets.getStyleClass().add("scroll-pane-style");
        ZoneProjets.setAlignment(Pos.TOP_CENTER);
        ZoneProjets.setHgap(35);
        ZoneProjets.setVgap(15);
        ZoneProjets.setPadding(new Insets(20));
        controller1.displayProjets(ZoneProjets);

        return this.ZoneProjets;
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
    public GridPane getZoneProjets() {
        return ZoneProjets;
    }

    private void action() {
        confirmButton.setOnAction(event -> {
            this.controller.handleConfirmButton(event);
        });

        cancelButton.setOnAction(event -> {
            this.controller.handleCancelButton(event);
        });

    }

   

}
