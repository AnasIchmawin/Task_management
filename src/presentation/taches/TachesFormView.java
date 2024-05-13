package presentation.taches;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    private GridPane ZoneListes;
    private String IdList;
    private VBox ContainerGoogleCalendar;
    private Button confirmerButton;
    private DatePicker dateTask;

    // Constructor
    public TachesFormView() {
        init();
        style();
        this.controller.displayTaches(false);
    }

    public TachesFormView(String IdList) {
        this.IdList = IdList;
        init();
        style();
        this.controller.displayTaches(true);
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
        // Le conteneur principal qui contient tous les éléments
        VBox mainContentContainer = new VBox();

        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");

        // Create GridPane for list items
        ScrollPane scrollPane = createScrollPane(ZoneListes);

        VBox Tasks = new VBox();
        Tasks.getChildren().addAll(scrollPane); // Add description area
        Tasks.setMargin(Tasks, new Insets(30, 0, 0, 80));
        Tasks.setAlignment(Pos.TOP_CENTER);

        StackPane searchPane = new StackPane();
        searchPane.setAlignment(Pos.TOP_RIGHT);
        searchPane.getChildren().addAll(searchField, searchButton);

        HBox topContainer = new HBox();
        topContainer.setAlignment(Pos.TOP_RIGHT); // Align elements at top left
        topContainer.setMinHeight(80);
        topContainer.getChildren().addAll(ordonnerButton, searchPane); // Add "Ordonner" button

        Label title = createLabel(controller.getListTitle());
        HBox titleContainer = new HBox();
        titleContainer.getChildren().add(title);
        HBox.setMargin(title, new Insets(0, 0, 10, 40));
        titleContainer.setAlignment(Pos.TOP_LEFT);
        HBox descriptionContainer = new HBox();
        VBox description = BoxDescription(controller.getListDescription());
        ScrollPane descriptionScrollPane = new ScrollPane(description);
        descriptionScrollPane.setFitToWidth(true);
        descriptionScrollPane.setStyle("-fx-background-color: transparent;");
        descriptionScrollPane.setPadding(new Insets(0, 10, 0, 40));
        // descriptionScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide vertical scrollbar
        descriptionScrollPane.setFitToHeight(true);
        descriptionContainer.getChildren().addAll(descriptionScrollPane);
        HBox.setMargin(descriptionContainer, new Insets(0, 20, 0, 40));
        descriptionContainer.setAlignment(Pos.TOP_LEFT);

        // Create HBox for adding the "Ajouter" button
        HBox bottomContainer = new HBox();
        bottomContainer.setAlignment(Pos.CENTER); // Align elements at bottom left

        ContainerGoogleCalendar = new VBox();
        ContainerGoogleCalendar.setPadding(new Insets(10, 10, 10, 10));
        ContainerGoogleCalendar.setSpacing(5);
        ContainerGoogleCalendar.getStyleClass().add("google-calendar-style");
        Label labelGoogleCalender = new Label("  Importer depuis  Google Calendrier");
        labelGoogleCalender.getStyleClass().add("google-calendar-label-style");
        labelGoogleCalender.setWrapText(true);

        HBox dateGoogleCalendar = new HBox();
        dateGoogleCalendar.setAlignment(Pos.TOP_LEFT);
        dateGoogleCalendar.setSpacing(5);

        dateTask = new DatePicker();
        dateTask.setPromptText("YYYY-MM-DD");
        dateTask.setPrefWidth(130);

        HBox.setMargin(confirmerButton, new Insets(2, 0, 0, 0));
        dateGoogleCalendar.getChildren().addAll(dateTask, confirmerButton);

        ContainerGoogleCalendar.getChildren().addAll(labelGoogleCalender, dateGoogleCalendar);

        HBox.setMargin(ajouterButton, new Insets(0, 0, 0, 70));
        HBox.setMargin(ContainerGoogleCalendar, new Insets(0, 70, 40, 0));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        bottomContainer.getChildren().addAll(ajouterButton, spacer, ContainerGoogleCalendar); // Add "Ajouter" button
        bottomContainer.getStyleClass().add("bottom-container-style");

        mainContentContainer.getChildren().addAll(topContainer, titleContainer, descriptionContainer, Tasks,
                bottomContainer); // Add top container and
        // Listes

        // Add les éléments à la StackPane
        container.getChildren().addAll(mainContentContainer);

        HBox.setMargin(ordonnerButton, new Insets(30, 20, 20, 70));
        HBox.setMargin(searchPane, new Insets(30, 70, 20, 20));

        // Action for AjouterButton
        ajouterButton.setOnAction(event -> {
            controller.handleAjouterButtonAction(ZoneListes, "Task" + (ZoneListes.getRowCount()));
        });

        ordonnerButton.setOnAction(event -> {
            controller.handleOrdonnerButtonAction();
        });

        listesButton.setOnAction(event -> {
            this.controller.handleListesButtonAction();
        });

        archiveButton.setOnAction(event -> {
            this.controller.handleArchiveButtonAction();
        });

        projectsButton.setOnAction(event -> {
            this.controller.handleProjectsButtonAction();
        });
        confirmerButton.setOnAction(event -> {
            controller.handleConfirmerButtonAction();
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
        // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide vertical scrollbar
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
        SurveillerButton(projectsButton, "100px", "40px", "#3F72AF");
        SurveillerButton(archiveButton, "100px", "40px", "#3F72AF");
        SurveillerButton(ordonnerButton, "100px", "40px", "#3F72AF");
        SurveillerButton(ajouterButton, "150px", "40px", "#3F72AF");
        ZoneListes = createGridPane();
        ContainerGoogleCalendar = new VBox();
        ContainerGoogleCalendar.setPadding(new Insets(2, 2, 2, 2));
        ContainerGoogleCalendar.setSpacing(5);
        confirmerButton = createButtonWithIcon("", "file:./Pictures/confirmer.png", 29, 29);

        this.controller = new TachesFormController(this);
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
        ContainerGoogleCalendar.getStyleClass().add("google-calendar-style");
        confirmerButton.getStyleClass().add("confirm-btn-style");

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

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("Label-style");
        return label;
    }

    private VBox BoxDescription(String description) {
        TextArea descriptionLabel = new TextArea(description);
        descriptionLabel.getStyleClass().add("description-style");
        descriptionLabel.setWrapText(true);
        VBox vbox = new VBox();
        vbox.setPrefHeight(330);
        vbox.setPrefWidth(1000);
        vbox.getChildren().addAll(descriptionLabel);
        return vbox;
    }

    // getZoneTaches
    public GridPane getZoneTaches() {
        return ZoneListes;
    }

    public String getIdList() {
        return IdList;
    }

    public void SurveillerButton(Button button, String width, String height, String color) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: #8E9EB2; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: " + color + ";" +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                controller.searchTache(newValue);
            }
        });
    }

    public String getDateTask() {
        return dateTask.getValue().toString();
    }
}
