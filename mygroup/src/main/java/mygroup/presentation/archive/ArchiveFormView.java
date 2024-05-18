package mygroup.presentation.archive ;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArchiveFormView extends Application {
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button searchButton;
    private TextField searchField;
    private ArchiveFormController controller;
    private BorderPane root;
    private ImageView RecycleIconView;
    private GridPane ZoneProjets;

    // Constructor
    public ArchiveFormView() {
        init();
        style();
        this.controller = new ArchiveFormController(this);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the navigation bar
        VBox navbarContainer = createNavbarContainer();
        // Create the main content
        StackPane container = createMainContent();
        // Create the root layout
        root = createBorderPane(navbarContainer, container);
        // Create the scene
        Scene scene = new Scene(root, 1160, 652);
        // Add the CSS file
        scene.getStylesheets().add(getClass().getResource("ArchiveStyle.css").toExternalForm());
        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Responsive Page with Navbar");
        primaryStage.show();
    }

    private BorderPane createBorderPane(VBox navbarContainer, StackPane container) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(navbarContainer, new Insets(0, 20, 0, 20));
        root.setTop(navbarContainer);
        BorderPane.setMargin(container, new Insets(20, 20, 20, 20));
        root.setCenter(container);
        return root;
    }

    private VBox createNavbarContainer() {
        HBox buttonsBar = new HBox(20, listesButton, projectsButton, archiveButton);
        buttonsBar.setPadding(new Insets(0, 0, 0, 55));
        HBox navbar = new HBox(30, buttonsBar);
        navbar.setPadding(new Insets(10, 20, 10, 20)); // 20px padding left and right, 10px padding top and bottom
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    private StackPane createMainContent() {
        //Le conteneur principal qui contient tous les éléments 
        VBox mainContentContainer = new VBox();
        mainContentContainer.setSpacing(10);

        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        
        VBox Recycle = new VBox();
        Recycle.getChildren().add(RecycleIconView);
        Recycle.setPadding(new Insets(20, 50, 0, 0));

        // Create GridPane for projet items
        ScrollPane scrollPane = createScrollPane(ZoneProjets);

        VBox Projets = new VBox();
        Projets.setSpacing(15);
        Projets.getChildren().addAll(scrollPane); // Add description area
        Projets.setPadding(new Insets(0, 40, 0, 40)); // Set padding for description area

        StackPane searchPane = new StackPane();
        searchPane.setAlignment(Pos.TOP_RIGHT);
        searchPane.getChildren().addAll(searchField, searchButton);
        searchPane.setPadding(new Insets(0, 0, 0, 300));

        HBox topContainer = new HBox();
        topContainer.setAlignment(Pos.TOP_RIGHT); // Align elements at top left
        topContainer.getChildren().addAll(searchPane); // Add "Ordonner" button

        topContainer.getChildren().add(Recycle); // Add StackPane containing search field and search button

        mainContentContainer.getChildren().addAll(topContainer, Projets); // Add top container and
                                                                                          // Listes

        // Add les éléments à la StackPane
        container.getChildren().addAll(mainContentContainer);

        HBox.setMargin(searchPane, new Insets(30, 70, 20, 20));

        listesButton.setOnAction(event -> {
            this.controller.handleListesButtonAction();
        });

        projectsButton.setOnAction(event -> {
            this.controller.handleProjectsButtonAction();
        });

        searchField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    controller.SearchProjet(newValue);
            }
        });

        return container;
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
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide vertical scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar
        scrollPane.setPadding(new Insets(0, 50, 0, 50));
        return scrollPane;
    }

    public void init() {
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        searchButton = createButtonWithIcon("", "file:./mygroup/src/main/java/Pictures/loupe.png", 20, 20);
        searchField = new TextField();
        searchField.setPromptText("Rechercher");
        RecycleIconView = createIcon("file:./mygroup/src/main/java/Pictures/recycle.png", 130, 54);
        ZoneProjets = createGridPane();
    }

    private void style() {
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-clicked-style");
        searchField.getStyleClass().add("search-field-style");
    }

    private Button createButtonWithIcon(String name, String string, int i, int j) {
        Button button = new Button(name);
        try {
            Image icon = new Image(string);
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(i);
            iconView.setFitHeight(j);
            button.setGraphic(iconView);
        } catch (Exception e) {
            System.out.println("Error loading the icon: " + e.getMessage());
        }
        return button;
    }

    private ImageView createIcon(String string, int i, int j) {
        Image searchIcon = new Image(string);
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(i);
        searchIconView.setFitHeight(j);
        return searchIconView;
    }

    // getZoneListes
    public GridPane getZoneProjets() {
        return ZoneProjets;
    }

}

