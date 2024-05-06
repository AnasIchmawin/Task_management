package presentation.listes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ListeFormView extends Application {
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button ordonnerButton;
    private Button searchButton;
    private Button ajouterButton;
    private TextField searchField;
    private ListeFormController controller;
    private BorderPane root;
    GridPane ZoneListes;
    private HBox buttonsBar;
    private HBox leftButtonBox;
    private HBox navbar;
    private VBox navbarContainer;
    private VBox mainContentContainer;
    private StackPane container;
    private ScrollPane scrollPane;
    private VBox Listes;
    private StackPane searchPane;
    private HBox topContainer;
    private Region spacer;
    private HBox buttonContainer;
   

    public ListeFormView() {
        Initialiser();
        Styler();
        Dessiner();
        Action();
        this.controller.afficheListes();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("ListStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Responsive Page with Navbar");
        primaryStage.show();
    }

    private void Initialiser() {
        leftButton = createButton("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        ordonnerButton = createButton("Ordonner", "file:./Pictures/folder.png", 20, 20);
        searchButton = createButton("", "file:./Pictures/loupe.png", 20, 20);
        ajouterButton = createButton("Ajouter une Liste", "file:./Pictures/add.png", 20, 20);
        buttonsBar = new HBox(20, listesButton, projectsButton, archiveButton);
        leftButtonBox = new HBox(20, leftButton);
        navbar = new HBox(30, leftButtonBox, buttonsBar);
        navbarContainer = new VBox(navbar);
        mainContentContainer = new VBox();
        mainContentContainer.setSpacing(10);
        container = new StackPane();
        ZoneListes = createGridPane();
        scrollPane = createScrollPane(ZoneListes);
        Listes = new VBox();
        Listes.setSpacing(15);
        searchPane = new StackPane();
        topContainer = new HBox();
        spacer = new Region();
        buttonContainer = new HBox();
        searchField = new TextField();
        searchField.setPromptText("Rechercher");
        root = new BorderPane();
        this.controller = new ListeFormController(this);
    }

    private void Styler() {
        root.getStyleClass().add("root");
        leftButton.getStyleClass().add("left-btn-style");
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ordonnerButton.getStyleClass().add("ordonner-btn-style");
        ajouterButton.getStyleClass().add("button-style");
        ajouterButton.setStyle("-fx-pref-width : 150px ;");
        searchField.getStyleClass().add("search-field-style");
        navbar.getStyleClass().add("navbar");
        navbarContainer.getStyleClass().add("navbar-container");
        container.getStyleClass().add("container");
        scrollPane.getStyleClass().add("scroll-pane");
    }

    private void Dessiner() {
        navbar.setPadding(new Insets(10, 20, 10, 20));
        BorderPane.setMargin(navbarContainer, new Insets(0, 20, 0, 20));
        container.getChildren().addAll(mainContentContainer);
        BorderPane.setMargin(container, new Insets(20, 20, 20, 20));
        scrollPane.setPadding(new Insets(0, 50, 0, 50));
        Listes.getChildren().addAll(scrollPane);
        Listes.setPadding(new Insets(0, 40, 0, 40));
        searchPane.setAlignment(Pos.TOP_RIGHT);
        searchPane.getChildren().addAll(searchField, searchButton);
        topContainer.setAlignment(Pos.TOP_LEFT);
        topContainer.getChildren().add(ordonnerButton);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topContainer.getChildren().add(spacer);
        topContainer.getChildren().add(searchPane);
        buttonContainer.getChildren().add(ajouterButton);
        HBox.setMargin(ajouterButton, new Insets(50, 0, 0, 70));
        mainContentContainer.getChildren().addAll(topContainer, Listes, buttonContainer);
        HBox.setMargin(ordonnerButton, new Insets(30, 20, 20, 70));
        HBox.setMargin(searchPane, new Insets(30, 70, 20, 20));
        root.setTop(navbarContainer);
        root.setCenter(container);

    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(30);
        gridPane.setHgap(20);
        gridPane.setPrefHeight(350);
        gridPane.setStyle("-fx-background-color: #F0F0F0;");
        return gridPane;
    }

    private ScrollPane createScrollPane(GridPane gridPane) {
        scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        return scrollPane;
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
        buttonContent.setSpacing(4);
        newButton.setGraphic(buttonContent);
        return newButton;
    }


    // Action des boutons
    private void Action() {
        ajouterButton.setOnAction(event -> {
            this.controller.handleAjouterButtonAction();
        });
        ordonnerButton.setOnAction(event -> {
            this.controller.handleOrdonnerButton() ;
        });
    }
}
