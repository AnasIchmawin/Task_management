package presentation.listes;

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




public class screenList extends Application {

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
        Button AjouterButton = new Button("Ajouter");
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher");


        // Appliquer le style de bouton depuis le fichier CSS
        LeftButton.getStyleClass().add("button-left-style");
        ListesButton.getStyleClass().add("button-clicked-style");
        ProjetsButton.getStyleClass().add("button-style");
        ArchiveButton.getStyleClass().add("button-style");
        OrdonnerButton.getStyleClass().add("button-style");
        AjouterButton.getStyleClass().add("button-style");
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
        try {
            Image AjouterIcon = new Image("file:./Pictures/add.png");
            ImageView AjouterIconView = new ImageView(AjouterIcon);
            AjouterIconView.setFitWidth(15);
            AjouterIconView.setFitHeight(15);
            AjouterButton.setGraphic(AjouterIconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ajouter icon");
        }

        Image searchIcon = new Image("file:./Pictures/loupe.png");
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(20);
        searchIconView.setFitHeight(20);

        // rectangle de background gray
        Rectangle BkGround = new Rectangle();
        BkGround.setWidth(screenWidth - 40); // Définir la largeur du rectangle
        BkGround.setHeight(590); // Définir la hauteur du rectangle
        BkGround.setStroke(Color.web("#112D4E")); // Définir la couleur de la bordure
        BkGround.setStrokeWidth(2.0); // Définir la largeur de la bordure
        BkGround.setFill(Color.rgb(240, 240, 240)); // Remplissage avec la couleur #F0F0F0
        BkGround.setArcWidth(15); // Définir le rayon horizontal des coins
        BkGround.setArcHeight(15); // Définir le rayon vertical des coins

        // L'ajout des boutons à la barre de navigation
        navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);


 // probleme du style externe donc je vais le mettre ici
        Button Liste1 = new Button("Liste 1");
        Liste1.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-pref-width: 170px; " +
                "-fx-pref-height: 60px;"+
                "-fx-text-fill: #ffffff;"+
                "-fx-font-size: 15px;");

        try {
            Image Liste1Icon = new Image("file:./Pictures/to-do.png");
            ImageView Liste1IconView = new ImageView(Liste1Icon);
            Liste1IconView.setFitWidth(20);
            Liste1IconView.setFitHeight(20);
            Liste1.setGraphic(Liste1IconView);
        } catch (Exception e) {
            System.out.println("Error loading the Liste1 icon");
        }
        // navbar position
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 20.0);

        // la positions du boton ordonner
        AnchorPane.setTopAnchor(OrdonnerButton, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(OrdonnerButton, 96.0);

        // la positions du boton ajouter
        AnchorPane.setTopAnchor(AjouterButton, 600.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(AjouterButton, 96.0);

        // la positions du search field
        AnchorPane.setTopAnchor(searchField, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchField, screenWidth - 300);

        // la positions du cadre gris
        AnchorPane.setTopAnchor(BkGround, 55.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(BkGround, 20.0);

        // la positions du search icon
        AnchorPane.setTopAnchor(searchIconView, 80.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchIconView, screenWidth - 127);

        // la positions du liste 1
        AnchorPane.setTopAnchor(Liste1, 150.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(Liste1, 85.0); // Spécifiez la distance depuis la gauche


        // Ajout de la barre de navigation à la racine
        root.getChildren().add(BkGround);
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        root.getChildren().addAll(navigationBar);
        root.getChildren().add(OrdonnerButton);
        root.getChildren().add(AjouterButton);
        root.getChildren().add(searchField);
        root.getChildren().add(searchIconView);
        root.getChildren().add(Liste1);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("ListStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
