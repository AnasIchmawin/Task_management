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

public class screenArchive extends Application {

    @Override
    public void start(Stage primaryStage) {

        // le racine
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background:#ffffff"); // la color de background

        // Barre de navigation

        HBox navigationBar = new HBox();
        navigationBar.getStyleClass().add("navigationBar"); // Appliquer le style depuis le fichier CSS

        // recuperer la taille de l'ecran
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        double screenWidth = primaryScreenBounds.getWidth();
        navigationBar.setPrefHeight(40); // Hauteur de la barre de navigation
        navigationBar.setPrefWidth(screenWidth - 40); // Largeur de la barre de navigation

        // Ajout de boutons à la barre de navigation
        Button LeftButton = new Button();
        Button ListesButton = new Button("Listes");
        Button ProjetsButton = new Button("Projets");
        Button ArchiveButton = new Button("Archive");
        Button OrdonnerButton = new Button("Ordonner");
        Button FiltrerButton = new Button("Filtrer");
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher");

        // Appliquer le style de bouton depuis le fichier CSS
        LeftButton.getStyleClass().add("button-left-style");
        ListesButton.getStyleClass().add("button-style");
        ProjetsButton.getStyleClass().add("button-style");
        ArchiveButton.getStyleClass().add("button-clicked-style");
        OrdonnerButton.getStyleClass().add("button-style");
        FiltrerButton.getStyleClass().add("button-style");
        searchField.getStyleClass().add("search-field-style");

        // Appliquer une marge à gauche aux boutons
        HBox.setMargin(ListesButton, new Insets(0, 0, 0, 40));
        HBox.setMargin(ProjetsButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(ArchiveButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));

        try {
            Image leftIcon = new Image("file:./Pictures/left-arrow.png");
            ImageView leftIconView = new ImageView(leftIcon);
            leftIconView.setFitWidth(33);
            leftIconView.setFitHeight(33);
            LeftButton.setGraphic(leftIconView);
        } catch (Exception e) {
            System.out.println("Error loading the left arrow icon");
        }

        try {
            Image OrdonnerIcon = new Image("file:./Pictures/folder.png");
            ImageView OrdonnerIconView = new ImageView(OrdonnerIcon);
            OrdonnerIconView.setFitWidth(20);
            OrdonnerIconView.setFitHeight(20);
            OrdonnerButton.setGraphic(OrdonnerIconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ordonner icon");
        }

        Image searchIcon = new Image("file:./Pictures/loupe.png");
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(20);
        searchIconView.setFitHeight(20);

        Image RecycleIcon = new Image("file:./Pictures/recycle.png");
        ImageView RecycleIconView = new ImageView(RecycleIcon);
        RecycleIconView.setFitWidth(130);
        RecycleIconView.setFitHeight(54);

        // rectangle de background
        Rectangle BkGround = new Rectangle();
        BkGround.setWidth(screenWidth - 40); // Définir la largeur du rectangle
        BkGround.setHeight(590); // Définir la hauteur du rectangle
        BkGround.setStroke(Color.web("#112D4E")); // Définir la couleur de la bordure
        BkGround.setStrokeWidth(2.0); // Définir la largeur de la bordure
        BkGround.setFill(Color.rgb(240, 240, 240)); // Remplissage avec la couleur #F0F0F0
        BkGround.setArcWidth(15); // Définir le rayon horizontal des coins
        BkGround.setArcHeight(15); // Définir le rayon vertical des coins

        // Ajout des boutons à la barre de navigation
        navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);

        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 20.0);

        AnchorPane.setTopAnchor(OrdonnerButton, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(OrdonnerButton, 96.0);

        AnchorPane.setTopAnchor(FiltrerButton, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(FiltrerButton, 221.0); // 15 entre les deux boutons

        AnchorPane.setTopAnchor(searchField, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchField, 346.0);

        AnchorPane.setTopAnchor(BkGround, 55.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(BkGround, 20.0);

        AnchorPane.setTopAnchor(searchIconView, 80.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchIconView, 519.0);

        AnchorPane.setTopAnchor(RecycleIconView, 70.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(RecycleIconView, screenWidth - 150);

        // Ajout de la barre de navigation à la racine
        root.getChildren().add(BkGround);
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        root.getChildren().addAll(navigationBar);
        root.getChildren().add(OrdonnerButton);
        root.getChildren().add(FiltrerButton);
        root.getChildren().add(searchField);
        root.getChildren().add(searchIconView);
        root.getChildren().add(RecycleIconView);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("ArchiveStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

