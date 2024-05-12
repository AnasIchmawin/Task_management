package presentation.projets;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private ComboBox<String> TypeBox;
    private ComboBox<String> CategorieBox;
    private Label filterLabel;
    private HBox navbar;
    private VBox navbarContainer;
    private VBox mainContentContainer;
    private StackPane container;
    private ScrollPane scrollPane;
    private VBox Projets;
    private StackPane searchPane;
    private HBox topContainer;
    private Region spacer;
    private HBox buttonContainer;
    private GridPane ZoneProjets;
    private StackPane stackPane;
    private HBox filterBox;
    private Button supprimerButton;

    public ProjetsFormView() {
        Initialiser();
        Styler();
        Dessiner();
        Action();
        this.controller.displayProjets(false);
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
        leftButton = createButton("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        ordonnerButton = createButton("Ordonner", "file:./Pictures/folder.png", 20, 20);
        searchButton = createButton("", "file:./Pictures/loupe.png", 20, 20);
        ajouterButton = createButton("Ajouter", "file:./Pictures/add.png", 20, 20);
        supprimerButton = createButton("Supprimer un Projet", "file:./Pictures/delete.png", 20, 20);
        searchField = new TextField();
        searchField.setPromptText("Rechercher");
        TypeBox = createComboBox("Type", "Tous","These", "PFE", "Cours", "Examen", "Autres");
        CategorieBox = createComboBox("Categorie", "Tous","Enseignement", "Encadrement", "Autres");
        filterLabel = new Label("     Filtrer");
        navbar = new HBox(30, leftButton, listesButton, projectsButton, archiveButton);
        navbarContainer = new VBox(navbar);
        mainContentContainer = new VBox();
        container = new StackPane();
        ZoneProjets = createGridPane();
        scrollPane = createScrollPane(ZoneProjets);
        stackPane = new StackPane();
        Projets = new VBox();
        searchPane = new StackPane();
        topContainer = new HBox();
        spacer = new Region();
        buttonContainer = new HBox();
        filterBox = new HBox();
        SurveillerButton(listesButton,"100px","40px","#3F72AF");
        SurveillerButton(archiveButton,"100px","40px","#3F72AF");
        SurveillerButton(ordonnerButton,"100px","40px","#3F72AF");
        SurveillerButton(ajouterButton,"150px","40px","#3F72AF");
        SurveillerButton(supprimerButton,"150px","40px","#3F72AF");
        this.controller = new ProjetsFormController(this);
    }

    private void Styler() {
        leftButton.getStyleClass().add("left-btn-style");
        searchButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-clicked-style");
        archiveButton.getStyleClass().add("button-style");
        ordonnerButton.getStyleClass().add("ordonner-btn-style");
        ajouterButton.getStyleClass().add("button-style");
        supprimerButton.getStyleClass().add("button-style");
        supprimerButton.setStyle("-fx-pref-width : 150px ;");
        searchField.getStyleClass().add("search-field-style");
        filterLabel.getStyleClass().add("filter-label-style");
        navbar.getStyleClass().add("navbar");
        navbarContainer.getStyleClass().add("navbar-container");
        container.getStyleClass().add("container");
        scrollPane.getStyleClass().add("scroll-pane");
        Projets.getStyleClass().add("listes");
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
    
        Projets.getChildren().addAll(scrollPane);
        Projets.setPadding(new Insets(0, 40, 0, 40));
        Projets.setSpacing(15); 
        HBox.setMargin(ajouterButton, new Insets(50, 0, 0, 70));
    
        buttonContainer.getChildren().addAll(ajouterButton, supprimerButton);
        HBox.setMargin(ajouterButton, new Insets(50, 0, 0, 70));
        HBox.setMargin(supprimerButton, new Insets(50, 0, 0, 25));
        mainContentContainer.getChildren().addAll(topContainer, Projets, buttonContainer);
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
            HBox.setMargin(TypeBox, new Insets(0, 0, 0, 88));
            filterBox.getChildren().addAll(TypeBox, CategorieBox);
            filterBox.setSpacing(10);
            filterBox.setPadding(new Insets(0, 20, 0, 0));
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

    private ComboBox<String> createComboBox(String prompt, String... items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setPromptText(prompt);
        comboBox.getStyleClass().add("comboBox-style");
        return comboBox;
    }

    private void Action() {
        ajouterButton.setOnAction(event -> {
            this.controller.handleAjouterButtonAction();
        });
        ordonnerButton.setOnAction(event -> {
            this.controller.handleOrdonnerButton();
        });
        listesButton.setOnAction(event -> {
            this.controller.handleListesButton();
        });
        supprimerButton.setOnAction(event -> {
            this.controller.handleSupprimerButtonAction();
        });

        archiveButton.setOnAction(event -> {
            this.controller.handleArchiveButton();
        });

        TypeBox.setOnAction(event -> {
            this.controller.handleBoxsAction();
        });

        CategorieBox.setOnAction(event -> {
            this.controller.handleBoxsAction();
        });
    }
    
    public void SurveillerButton(Button button ,String width ,String heitht ,String color) {
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

        searchField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    controller.SearchProjet(newValue);
            }
        });
    }

    public GridPane getZoneProjets() {
        return ZoneProjets;
    }

    //getTypeBox
    public String getTypeBoxValue() {
        return TypeBox.getValue();
    }

    //getCategorieBox
    public String getCategorieBoxValue() {
        return CategorieBox.getValue();
    }

    public String getSearchFieldText() {
        return searchField.getText();
    }

    //setTypeBoxsValue
    public void setTypeBoxValue(String value) {
        TypeBox.setValue(value);
    }

    //setCategorieBoxsValue
    public void setCategorieBoxValue(String value) {
        CategorieBox.setValue(value);
    }

    //getSearchField
    public TextField getSearchField() {
        return searchField;
    }
}
