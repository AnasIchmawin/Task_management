package presentation.archive;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ArchiveFormView extends Application {

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
        Button FiltrerButton = createButtonWithIcon("Filtrer", "file:./Pictures/folder.png", 20, 20, 75, 221);
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher");

        OrdonnerButton.getStyleClass().add("button-style");
        searchField.getStyleClass().add("search-field-style");


        ImageView searchIconView = createIcon("file:./Pictures/loupe.png", 20, 20, 80, 519);

        ImageView RecycleIconView = createIcon("file:./Pictures/recycle.png", 130, 54, 70, screenWidth - 150);
        
        Rectangle BkGround = createBackgroundRectangle(screenWidth);

        AnchorPane.setTopAnchor(searchField, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchField, 346.0);

        root.getChildren().addAll(BkGround, navigationBar, OrdonnerButton, FiltrerButton, searchField, searchIconView, RecycleIconView);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("ArchiveStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    private ImageView createIcon(String string, int i, int j, double k, double l) {
        Image searchIcon = new Image(string);
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(i);
        searchIconView.setFitHeight(j);
        AnchorPane.setTopAnchor(searchIconView, k);
        AnchorPane.setLeftAnchor(searchIconView, l);
        return searchIconView;
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
}

