package presentation.listes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class screenList extends Application {
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

        Button OrdonnerButton = createButtonWithIcon("Ordonner", "file:./Pictures/folder.png", 20, 20, 75, 96);
        Button AjouterButton = createButtonWithIcon("Ajouter", "file:./Pictures/add.png", 15, 15, 600, 96);

        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher");

        searchField.getStyleClass().add("search-field-style");

        // Logo de la barre de recherche
        ImageView searchIconView = createSearchIcon(screenWidth);
        // background gray
        Rectangle BkGround = createBackgroundRectangle(screenWidth);
        // Le contenir des listes
        GridPane gridPane = createGridPane(screenWidth);

        ScrollPane scrollPane = createScrollPane(gridPane);

        // Action du bouton Ajouter
        AjouterButton.setOnAction(event -> {
            ListFormController.handleAjouterButtonAction(gridPane);
        });

        // la positions du search field
        AnchorPane.setTopAnchor(searchField, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchField, screenWidth - 300);

        // Ajout de la barre de navigation à la racine

        root.getChildren().addAll(BkGround, navigationBar, OrdonnerButton, AjouterButton, searchField, searchIconView, scrollPane);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("ListStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox();
        navigationBar.getStyleClass().add("navigationBar");

        Button ListesButton = new Button("Listes");
        Button ProjetsButton = new Button("Projets");
        Button ArchiveButton = new Button("Archive");
        Button LeftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 33, 33, 0, 0);

        ListesButton.getStyleClass().add("button-style");
        ProjetsButton.getStyleClass().add("button-style");
        ArchiveButton.getStyleClass().add("button-style");
        LeftButton.getStyleClass().add("button-left-style");

        HBox.setMargin(ListesButton, new Insets(0, 0, 0, 40));
        HBox.setMargin(ProjetsButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(ArchiveButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));

        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 20.0);

        navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);

        return navigationBar;
    }

    private Button createButtonWithIcon(String name, String string, int i, int j, double k, double l) {
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

    private ImageView createSearchIcon(double width) {
        Image searchIcon = new Image("file:./Pictures/loupe.png");
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(20);
        searchIconView.setFitHeight(20);
        AnchorPane.setTopAnchor(searchIconView, 80.0);
        AnchorPane.setLeftAnchor(searchIconView, width - 127);
        return searchIconView;
    }
}
