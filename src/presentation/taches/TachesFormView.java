package presentation.taches;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import presentation.listes.ListeFormController;
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
    private GridPane ZoneTaches;
    private VBox ContainerGoogleCalendar;
    private Button confirmerButton;
    private DatePicker dateTask;
    private StackPane container;
    private VBox navbarContainer;
    private Label title;
    private VBox description;

    public TachesFormView(ListeFormController listeFormController) {
        init();
        style();
        Action();
        this.controller = new TachesFormController(this, listeFormController);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("TachesStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Responsive Page with Navbar");
        primaryStage.show();
    }

    // Initialisation
    public void init() {
        leftButton = createButton("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        ordonnerButton = createButton("Ordonner", "file:./Pictures/folder.png", 20, 20);
        searchButton = createButton("", "file:./Pictures/loupe.png", 20, 20);
        ajouterButton = createButton("Ajouter une tache", "file:./Pictures/add.png", 20, 20);
        searchField = new TextField();
        searchField.setPromptText("Rechercher");
        ZoneTaches = createGridPane();
        ContainerGoogleCalendar = new VBox();
        ContainerGoogleCalendar.setPadding(new Insets(2, 2, 2, 2));
        ContainerGoogleCalendar.setSpacing(5);
        confirmerButton = createButton("", "file:./Pictures/confirmer.png", 29, 29);
        navbarContainer = createNavbarContainer();
        container = createMainContent();
        root = createBorderPane(navbarContainer, container);

    }

    // Style
    private void style() {
        leftButton.getStyleClass().add("left-btn-style");
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ordonnerButton.getStyleClass().add("ordonner-btn-style");
        ajouterButton.getStyleClass().add("ajouter-btn-style");
        searchField.getStyleClass().add("search-field-style");
        ContainerGoogleCalendar.getStyleClass().add("google-calendar-style");
        confirmerButton.getStyleClass().add("confirm-btn-style");

    }
    // Méthodes de création des éléments de la vue

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
        navbar.setPadding(new Insets(10, 20, 10, 20));
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    private StackPane createMainContent() {
        VBox mainContentContainer = createMainContentContainer();
        StackPane container = new StackPane();

        // Création du contenu de la vue
        ScrollPane scrollPane = createScrollPane(ZoneTaches);
        VBox tasks = createTasksContainer(scrollPane);
        StackPane searchPane = createSearchPane();
        HBox topContainer = createTopContainer(searchPane);
        title = createLabel("");
        HBox titleContainer = createTitleContainer(title);
        HBox descriptionContainer = createDescriptionContainer();
        HBox bottomContainer = createBottomContainer();

        // Ajout des éléments au conteneur principal
        mainContentContainer.getChildren().addAll(topContainer, titleContainer, descriptionContainer, tasks,
                bottomContainer);
        container.getChildren().addAll(mainContentContainer);
        setMarginsForElements();

        return container;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("Label-style");
        return label;
    }

    private VBox createMainContentContainer() {
        VBox mainContentContainer = new VBox();
        mainContentContainer.getStyleClass().add("container");
        return mainContentContainer;
    }

    @SuppressWarnings("static-access")
    private VBox createTasksContainer(ScrollPane scrollPane) {
        VBox tasks = new VBox();
        tasks.getChildren().addAll(scrollPane);
        tasks.setMargin(tasks, new Insets(30, 0, 0, 80));
        tasks.setAlignment(Pos.TOP_CENTER);
        return tasks;
    }

    private StackPane createSearchPane() {
        StackPane searchPane = new StackPane();
        searchPane.setAlignment(Pos.TOP_RIGHT);
        searchPane.getChildren().addAll(searchField, searchButton);
        HBox.setMargin(searchPane, new Insets(30, 70, 0, 0));
        return searchPane;
    }

    private HBox createTopContainer(StackPane searchPane) {
        HBox topContainer = new HBox();
        topContainer.setAlignment(Pos.TOP_RIGHT);
        topContainer.setMinHeight(80);
        topContainer.getChildren().addAll(ordonnerButton, searchPane);
        return topContainer;
    }

    private HBox createTitleContainer(Label title) {
        HBox titleContainer = new HBox();
        titleContainer.getChildren().add(title);
        HBox.setMargin(title, new Insets(0, 0, 10, 40));
        titleContainer.setAlignment(Pos.TOP_LEFT);
        return titleContainer;
    }

    private HBox createDescriptionContainer() {
        HBox descriptionContainer = new HBox();
        description = BoxDescription("");
        ScrollPane descriptionScrollPane = createDescriptionScrollPane(description);
        descriptionContainer.getChildren().addAll(descriptionScrollPane);
        HBox.setMargin(descriptionContainer, new Insets(0, 20, 0, 40));
        descriptionContainer.setAlignment(Pos.TOP_LEFT);
        return descriptionContainer;
    }

    private HBox createBottomContainer() {
        HBox bottomContainer = new HBox();
        bottomContainer.setAlignment(Pos.CENTER);
        ContainerGoogleCalendar = createGoogleCalendarContainer();
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bottomContainer.getChildren().addAll(ajouterButton, spacer, ContainerGoogleCalendar);
        bottomContainer.getStyleClass().add("bottom-container-style");
        HBox.setMargin(ContainerGoogleCalendar, new Insets(0, 50, 30, 0));
        return bottomContainer;
    }

    // Méthodes de création d'éléments spécifiques

    private VBox createGoogleCalendarContainer() {
        VBox containerGoogleCalendar = new VBox();
        containerGoogleCalendar.setPadding(new Insets(10, 10, 10, 10));
        containerGoogleCalendar.setSpacing(5);
        containerGoogleCalendar.getStyleClass().add("google-calendar-style");
        Label labelGoogleCalender = new Label("  Importer depuis  Google Calendrier");
        labelGoogleCalender.getStyleClass().add("google-calendar-label-style");
        labelGoogleCalender.setWrapText(true);
        HBox dateGoogleCalendar = createDateGoogleCalendar();
        containerGoogleCalendar.getChildren().addAll(labelGoogleCalender, dateGoogleCalendar);
        HBox.setMargin(ContainerGoogleCalendar, new Insets(-100, 70, 0, 0));
        return containerGoogleCalendar;
    }

    private HBox createDateGoogleCalendar() {
        HBox dateGoogleCalendar = new HBox();
        dateGoogleCalendar.setAlignment(Pos.TOP_LEFT);
        dateGoogleCalendar.setSpacing(5);
        dateTask = new DatePicker();
        dateTask.setPromptText("YYYY-MM-DD");
        dateTask.setPrefWidth(130);
        HBox.setMargin(confirmerButton, new Insets(2, 0, 0, 0));
        dateGoogleCalendar.getChildren().addAll(dateTask, confirmerButton);
        return dateGoogleCalendar;
    }

    private ScrollPane createDescriptionScrollPane(VBox description) {
        ScrollPane descriptionScrollPane = new ScrollPane(description);
        descriptionScrollPane.setFitToWidth(true);
        descriptionScrollPane.setStyle("-fx-background-color: transparent;");
        descriptionScrollPane.setPadding(new Insets(0, 10, 0, 40));
        descriptionScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return descriptionScrollPane;
    }

    // Méthode pour définir les marges pour certains éléments
    private void setMarginsForElements() {
        HBox.setMargin(ordonnerButton, new Insets(30, 20, 20, 70));
        HBox.setMargin(searchButton, new Insets(30, 70, 20, 20));
        HBox.setMargin(ajouterButton, new Insets(35, 0, 0, 70));
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
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du bouton : " + e.getMessage());
        }
        return newButton;
    }

    private VBox BoxDescription(String description) {
        Label descriptionLabel = new Label(description);
        descriptionLabel.getStyleClass().add("description-style");
        descriptionLabel.setWrapText(true);
        VBox vbox = new VBox();
        vbox.setPrefHeight(250);
        vbox.getChildren().addAll(descriptionLabel);
        return vbox;
    }

    // getZoneTaches
    public GridPane getZoneTaches() {
        return ZoneTaches;
    }

    public Button getAjouterButton() {
        return ajouterButton;
    }

    public Button getOrdonnerButton() {
        return ordonnerButton;
    }

    public Button getLeftButton() {
        return leftButton;
    }

    public Button getListesButton() {
        return listesButton;
    }

    public Button getProjectsButton() {
        return projectsButton;
    }

    public Button getArchiveButton() {
        return archiveButton;
    }

    public String getDateTask() {
        return dateTask.getValue().toString();
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getDescription() {
        return description.getChildren().get(0).toString();
    }

    public void setDescription(String description) {
        this.description.getChildren().clear();
        this.description.getChildren().add(BoxDescription(description));
    }

    // Action
    private void Action() {
        ajouterButton.setOnAction(event -> {
            controller.handleAjouterButtonAction();
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
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                controller.searchTache(newValue);
            }
        });
    }
}