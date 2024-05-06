package presentation.projets;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ProjetsFormView extends Application {
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button ordonnerButton;
    private Button searchButton;
    private Button ajouterButton;
    private TextField searchField;
    private ProjetsFormController controller;
    private BorderPane root;
    private ComboBox<String> comboBox1;
    private ComboBox<String> comboBox2;
    private Label filterLabel;
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
    private GridPane ZoneListes;
    private StackPane stackPane;
    private HBox filterBox;

    public ProjetsFormView() {
        this.controller = new ProjetsFormController();
        Initialiser();
        Styler();
        Dessiner();
        Action();
        this.controller.AfficheProjets(this.ZoneListes);        
    }

    @Override
    public void start(Stage primaryStage) {
        root = createBorderPane(navbarContainer, container);
        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("projetsStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Responsive Page with Navbar");
        primaryStage.show();
    }

    private void Initialiser() {
        leftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        ordonnerButton = createButtonWithIcon("Ordonner", "file:./Pictures/folder.png", 20, 20);
        searchButton = createButtonWithIcon("", "file:./Pictures/loupe.png", 20, 20);
        ajouterButton = createButtonWithIcon("Ajouter", "file:./Pictures/add.png", 20, 20);
        searchField = new TextField();
        searchField.setPromptText("Rechercher");
        comboBox1 = createComboBox("Type", "These", "PFE", "Cours", "Examen", "Autres");
        comboBox2 = createComboBox("Categorie", "Enseignement", "Encadrement", "Autres");
        filterLabel = new Label("     Filtrer");
        navbar = new HBox(30, leftButton, listesButton, projectsButton, archiveButton);
        navbarContainer = new VBox(navbar);
        mainContentContainer = new VBox();
        container = new StackPane();
        ZoneListes = createGridPane();
        scrollPane = createScrollPane(ZoneListes);
        stackPane = new StackPane();
        Listes = new VBox();
        searchPane = new StackPane();
        topContainer = new HBox();
        spacer = new Region();
        buttonContainer = new HBox();
        filterBox = new HBox();

        
    }

    private void Styler() {
        leftButton.getStyleClass().add("left-btn-style");
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ordonnerButton.getStyleClass().add("ordonner-btn-style");
        ajouterButton.getStyleClass().add("button-style");
        searchField.getStyleClass().add("search-field-style");
        filterLabel.getStyleClass().add("filter-label-style");
        navbar.getStyleClass().add("navbar");
        navbarContainer.getStyleClass().add("navbar-container");
        container.getStyleClass().add("container");
        scrollPane.getStyleClass().add("scroll-pane");
        Listes.getStyleClass().add("listes");
        searchPane.getStyleClass().add("search-pane");
        topContainer.getStyleClass().add("top-container");
        buttonContainer.getStyleClass().add("button-container");
    }

    private void Dessiner() {
        navbar.setPadding(new Insets(10, 20, 10, 20));
        HBox.setMargin(leftButton, new Insets(0, 0, 0, 0));
        BorderPane.setMargin(navbarContainer, new Insets(0, 20, 0, 20));
        topContainer.setAlignment(Pos.TOP_LEFT); 
        scrollPane.setPadding(new Insets(0, 50, 0, 50));
    
        Listes.getChildren().addAll(scrollPane);
        Listes.setPadding(new Insets(0, 40, 0, 40));
        Listes.setSpacing(15); 
        HBox.setMargin(ajouterButton, new Insets(50, 0, 0, 70));
    
        buttonContainer.getChildren().add(ajouterButton);
        mainContentContainer.getChildren().addAll(topContainer, Listes, buttonContainer);
        mainContentContainer.setSpacing(10);
        container.getChildren().addAll(mainContentContainer);
        BorderPane.setMargin(container, new Insets(20, 20, 20, 20));
    
        searchPane.getChildren().addAll(searchField, searchButton);
        HBox.setMargin(searchPane, new Insets(30, 70, 20, 20));
        searchPane.setAlignment(Pos.TOP_RIGHT);
        HBox.setMargin(ordonnerButton, new Insets(30, 20, 20, 70));
        topContainer.getChildren().addAll(ordonnerButton, searchPane);
    
        // Add stackPane with filterBox only if it hasn't been added already
        if (!topContainer.getChildren().contains(stackPane)) {
            HBox.setHgrow(spacer, Priority.ALWAYS);
            topContainer.getChildren().add(spacer);
            HBox.setMargin(comboBox1, new Insets(0, 0, 0, 88));
            filterBox.getChildren().addAll(comboBox1, comboBox2);
            filterBox.setSpacing(10);
            filterBox.setAlignment(Pos.CENTER);
            stackPane.getChildren().addAll(filterLabel, filterBox);
            HBox.setMargin(stackPane, new Insets(30, 40, 30, 70));
            topContainer.getChildren().add(stackPane);
        }
    }
    


    private BorderPane createBorderPane(VBox navbarContainer, StackPane container) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");
        root.setTop(navbarContainer);
        root.setCenter(container);
        return root;
    }

    private VBox createNavbarContainer() {
        HBox buttonsBar = new HBox(20, listesButton, projectsButton, archiveButton);
        HBox leftButtonBox = new HBox(20, leftButton);
        HBox navbar = new HBox(30, leftButtonBox, buttonsBar);
        VBox navbarContainer = new VBox(navbar);
        return navbarContainer;
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
        return scrollPane;
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

    private ComboBox<String> createComboBox(String prompt, String... items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setPromptText(prompt);
        comboBox.getStyleClass().add("comboBox-style");
        return comboBox;
    }

    private void Action() {
        ajouterButton.setOnAction(event -> {
            this.controller.handleAjouterButtonAction(ZoneListes);
        });
    }
    
}
