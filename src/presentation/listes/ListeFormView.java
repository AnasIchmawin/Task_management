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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    private Button supprimerButton ;

    public ListeFormView() {
        Initialiser();
        Styler();
        Dessiner();
        Action();
        this.controller.displayLists(false);
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
        supprimerButton = createButton("Supprimer une Liste", "file:./Pictures/delete.png", 20, 20);
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
        SurveillerButton(projectsButton , "100px" , "40px" , "#3F72AF");
        SurveillerButton(archiveButton, "100px" , "40px" , "#3F72AF");
        SurveillerButton(ordonnerButton , "100px" , "40px" , "#3F72AF");
        SurveillerButton(ajouterButton , "150px" , "40px" , "#3F72AF");
        SurveillerButton(supprimerButton , "150px" , "40px" , "#3F72AF");
        this.controller = new ListeFormController(this);
    }

    private void Styler() {
        root.getStyleClass().add("root");
        leftButton.getStyleClass().add("left-btn-style");
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-clicked-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ordonnerButton.getStyleClass().add("ordonner-btn-style");
        ajouterButton.getStyleClass().add("button-style");
        ajouterButton.setStyle("-fx-pref-width : 150px ;");
        supprimerButton.getStyleClass().add("button-style");
        supprimerButton.setStyle("-fx-pref-width : 150px ;");
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
        buttonContainer.getChildren().addAll(ajouterButton , supprimerButton);
        HBox.setMargin(ajouterButton, new Insets(50, 0, 0, 70));
        HBox.setMargin(supprimerButton, new Insets(50, 0, 0, 25));
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
            this.controller.handleOrdonnerButton();
        });
        projectsButton.setOnAction(event -> {
            this.controller.handleProjectsButton();
        });
        supprimerButton.setOnAction(event -> {
            this.controller.handleSupprimerButtonAction();
        });
        archiveButton.setOnAction(event -> {
            this.controller.handleArchiveButton();
        }
        );
    }

    public void SurveillerButton(Button button ,String width , String height , String color) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:"+width+"; " +
                    "-fx-background-color: #8E9EB2; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.HAND);
        });   
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:"+width+"; " +
                    "-fx-background-color: "+color+";"+
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
        searchField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    controller.SearchList(newValue);
                }
            });
    }

    public GridPane getZoneListes() {
        return ZoneListes;
    }

    public String getSearchFieldText() {
        return searchField.getText();
    }

    
}
