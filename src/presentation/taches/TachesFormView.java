package presentation.taches;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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

public class TachesFormView extends Application {
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button ordonnerButton;
    private Button searchButton;
    private Button ajouterButton;
    private TextField searchField;
    private TachesFormController controller;
    private BorderPane root;
    private ComboBox<String> comboBox1;
    private ComboBox<String> comboBox2;
    private Label filterLabel;

    // Constructor
    public TachesFormView(TachesFormController controller) {
        this.controller = controller;
        init();
        style();
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
        scene.getStylesheets().add(getClass().getResource("TachesStyle.css").toExternalForm());
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
        HBox leftButtonBox = new HBox(20, leftButton);
        HBox navbar = new HBox(30, leftButtonBox, buttonsBar);
        navbar.setPadding(new Insets(10, 20, 10, 20)); // 20px padding left and right, 10px padding top and bottom
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    private StackPane createMainContent() {
        //Le conteneur principal qui contient tous les éléments 
        VBox mainContentContainer = new VBox();

        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        

        // Create GridPane for list items
        GridPane ZoneListes = createGridPane();
        ScrollPane scrollPane = createScrollPane(ZoneListes);

        VBox Tasks = new VBox();
        Tasks.getChildren().addAll(scrollPane); // Add description area
        Tasks.setMargin(Tasks, new Insets(0, 0, 0, 80));

        StackPane searchPane = new StackPane();
        searchPane.setAlignment(Pos.TOP_RIGHT);
        searchPane.getChildren().addAll(searchField, searchButton);

        StackPane stackPane = new StackPane();
        HBox.setMargin(comboBox1, new Insets(0, 0, 0, 88));
        HBox filterBox = new HBox();
        filterBox.getChildren().addAll(comboBox1, comboBox2);
        filterBox.setSpacing(10); // Espace entre les éléments    
        filterBox.setAlignment(Pos.CENTER); // Centrer les éléments horizontalement

        stackPane.getChildren().addAll(filterLabel,filterBox);
        HBox.setMargin(stackPane, new Insets(25, 40, 50, 70));

        HBox topContainer = new HBox();
        topContainer.setAlignment(Pos.TOP_LEFT); // Align elements at top left
        topContainer.setPrefHeight(300);
        topContainer.getChildren().addAll(ordonnerButton,searchPane); // Add "Ordonner" button
        

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Make spacer grow as much as possible
        topContainer.getChildren().add(spacer);
        topContainer.getChildren().add(stackPane); // Add StackPane containing search field and search button

        //title
        Label title = createLabel("Listes des tâches");
        HBox titleContainer = new HBox();
        titleContainer.getChildren().add(title);
        HBox.setMargin(title, new Insets(0, 0, 0, 40));
        titleContainer.setAlignment(Pos.TOP_LEFT);
        // Create HBox for description
        HBox descriptionContainer = new HBox();
        VBox description = BoxDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque viverra nisl eget sapien hendrerit semper. Integer a dictum massa. Vivamus id sapien eu orci bibendum ornare non sed mauris. Donec sagittis odio ut sem aliquam rutrum. Duis auctor dignissim quam, ac vulputate tortor ultricies et. Interdum et malesuada fames ac ante ipsum primis in faucibus.consectetur adipiscing elit. Quisque viverra nisl eget sapien hendrerit semper. Integer a dictum massa. Vivamus id sapien eu orci bibendum ornare non sed mauris. Donec sagittis odio ut sem aliquam rutrum. Duis auctor dignissim quam, ac vulputate tortor ultricies et. Interdum et malesuada fames ac ante ipsum primis in faucibus.");
        descriptionContainer.getChildren().addAll(description);
        HBox.setMargin(description, new Insets(0, 20,30, 40));
        descriptionContainer.setAlignment(Pos.TOP_LEFT);

        // Create HBox for adding the "Ajouter" button
        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().add(ajouterButton);
        HBox.setMargin(ajouterButton, new Insets(0, 0, 20, 70));

        mainContentContainer.getChildren().addAll(topContainer,titleContainer,descriptionContainer, Tasks, buttonContainer); // Add top container and
                                                                                          // Listes

        // Add les éléments à la StackPane
        container.getChildren().addAll(mainContentContainer);

        HBox.setMargin(ordonnerButton, new Insets(30, 20, 20, 70));
        HBox.setMargin(searchPane, new Insets(30, 70, 20, 20));

        // Action for AjouterButton
        ajouterButton.setOnAction(event -> {
            controller.handleAjouterButtonAction(ZoneListes,"Task"+(ZoneListes.getRowCount()));
        });

        searchButton.setOnAction(event -> {
            controller.handleSearchButtonAction(ZoneListes,searchField.getText());
        });

        return container;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(10);
        gridPane.setPrefHeight(500);
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
    }

    private void style() {
        leftButton.getStyleClass().add("left-btn-style");
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ordonnerButton.getStyleClass().add("ordonner-btn-style");
        ajouterButton.getStyleClass().add("button-style");
        searchField.getStyleClass().add("search-field-style");
        filterLabel.getStyleClass().add("filter-label-style");

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

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("Label-style");
        return label;
    }

    private VBox BoxDescription(String description) {
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.getStyleClass().add("description-style");
        descriptionLabel.setTextOverrun(OverrunStyle.CLIP);
        descriptionLabel.setEllipsisString(""); // Pas de troncature
        VBox vbox = new VBox();
        vbox.getChildren().addAll(descriptionLabel);
        return vbox;
    }
}
