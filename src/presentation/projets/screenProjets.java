package presentation.projets;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class screenProjets extends Application {
    @Override
    public void start(Stage primaryStage) {
        // le racine
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background:#ffffff"); // la color de background

        // Barre de navigation
        HBox navigationBar = createNavigationBar();

        // recuperer la taille de l'ecran
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        double screenWidth = primaryScreenBounds.getWidth();
        navigationBar.setPrefHeight(40); // Hauteur de la barre de navigation
        navigationBar.setPrefWidth(screenWidth - 40); // Largeur de la barre de navigation

        Button OrdonnerButton = createButtonWithIcon("Ordonner","file:./Pictures/folder.png", 20, 20, 75, 96);
        Button AjouterButton = createButtonWithIcon("Ajouter","file:./Pictures/add.png", 15, 15, 600, 96);

        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher");

        ComboBox<String> comboBox = createComboBox("Type", "These", "PFE", "Cours", "Examen", "Autres");

        ComboBox<String> comboBox1 = createComboBox("Categorie", "Enseignement", "Encadrement", "Autres");

        Label filterLabel = new Label("     Filtrer");

        StackPane stackPane = new StackPane();

        // Appliquer le style de bouton depuis le fichier CSS
        searchField.getStyleClass().add("search-field-style");
        filterLabel.getStyleClass().add("filter-label-style");

        HBox.setMargin(comboBox, new Insets(0, 0, 0, 88));

        ImageView searchIconView = createSearchIcon();

        HBox filterBox = new HBox();
        filterBox.getChildren().addAll(comboBox, comboBox1);
        filterBox.setSpacing(10); // Espace entre les éléments    
        filterBox.setAlignment(Pos.CENTER); // Centrer les éléments horizontalement

        stackPane.getChildren().addAll(filterLabel,filterBox);

        // rectangle de background
        Rectangle BkGround = createBackgroundRectangle(screenWidth);

        GridPane gridPane = createGridPane(screenWidth);
        
        ScrollPane scrollPane = createScrollPane(gridPane);

        // Action du bouton Ajouter
        AjouterButton.setOnAction(event -> {
            ProjetsFormController.handleAjouterButtonAction(gridPane);
        });

        AnchorPane.setTopAnchor(searchField, 75.0);
        AnchorPane.setLeftAnchor(searchField, 221.0);

        AnchorPane.setTopAnchor(stackPane, 75.0);
        AnchorPane.setRightAnchor(stackPane, 100.0);

        root.getChildren().addAll(BkGround,navigationBar,OrdonnerButton,AjouterButton,searchField,searchIconView,stackPane,scrollPane);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("projetsStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButtonWithIcon(String name,String string, int i, int j, double k, double l) {
        Button Button = new Button(name);
        try {
            Image Icon = new Image(string);
            ImageView IconView = new ImageView(Icon);
            IconView.setFitWidth(i);
            IconView.setFitHeight(j);
            Button.setGraphic(IconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ordonner icon");
        }
        Button.getStyleClass().add("button-style");

        AnchorPane.setTopAnchor(Button, k);
        AnchorPane.setLeftAnchor(Button, l);
        return Button;
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox();
        navigationBar.getStyleClass().add("navigationBar");

        Button listesButton = new Button("Listes");
        Button projetsButton = new Button("Projets");
        Button archiveButton = new Button("Archive");
        Button LeftButton = createButtonWithIcon("","file:./Pictures/left-arrow.png", 33, 33, 0, 0);

        listesButton.getStyleClass().add("button-style");
        projetsButton.getStyleClass().add("button-clicked-style");
        archiveButton.getStyleClass().add("button-style");
        LeftButton.getStyleClass().add("button-left-style");

        HBox.setMargin(listesButton, new Insets(0, 0, 0, 40));
        HBox.setMargin(projetsButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(archiveButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));

        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 20.0);

        navigationBar.getChildren().addAll(LeftButton, listesButton, projetsButton, archiveButton);

        return navigationBar;
    }

    private ComboBox<String> createComboBox(String prompt, String... items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setPromptText(prompt);
        comboBox.getStyleClass().add("comboBox-style");
        return comboBox;
    }

    private Rectangle createBackgroundRectangle(double width) {
        Rectangle background = new Rectangle();
        background.setWidth(width - 40);
        background.setHeight(590);
        background.setStroke(Color.web("#112D4E"));
        background.setStrokeWidth(2.0);
        background.setFill(Color.rgb(240, 240, 240));
        background.setArcWidth(15);
        background.setArcHeight(15);
        AnchorPane.setTopAnchor(background, 55.0);
        AnchorPane.setLeftAnchor(background, 20.0);
        return background;
    }

    private ImageView createSearchIcon() {
        Image searchIcon = new Image("file:./Pictures/loupe.png");
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(20);
        searchIconView.setFitHeight(20);
        AnchorPane.setTopAnchor(searchIconView, 80.0);
        AnchorPane.setLeftAnchor(searchIconView, 394.0);
        return searchIconView;
    }

    // Créer un GridPane pour afficher les projets
    private GridPane createGridPane(double width) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPrefWidth(width - 100);
        gridPane.setPrefHeight(450);
        gridPane.setStyle("-fx-background-color: #F0F0F0;");
        AnchorPane.setTopAnchor(gridPane, 140.0);
        AnchorPane.setLeftAnchor(gridPane, 75.0);
        return gridPane;
    }

    // Créer un ScrollPane pour afficher les projets
    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        AnchorPane.setTopAnchor(scrollPane, 140.0);
        AnchorPane.setLeftAnchor(scrollPane, 75.0);
        return scrollPane;
    }
}
