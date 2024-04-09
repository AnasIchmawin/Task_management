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
        ListesButton.getStyleClass().add("button-style");
        ProjetsButton.getStyleClass().add("button-style");
        ArchiveButton.getStyleClass().add("button-style");
        OrdonnerButton.getStyleClass().add("button-style");
        AjouterButton.getStyleClass().add("button-style");
        searchField.getStyleClass().add("search-field-style");

        // Appliquer une marge à gauche aux boutons sur le navbar 
        HBox.setMargin(ListesButton, new Insets(0, 0, 0, 40));
        HBox.setMargin(ProjetsButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(ArchiveButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));
        
        // Logo de la flèche gauche
        try {
            Image leftIcon = new Image("file:./Pictures/left-arrow.png");
            ImageView leftIconView = new ImageView(leftIcon);
            leftIconView.setFitWidth(33);
            leftIconView.setFitHeight(33);
            LeftButton.setGraphic(leftIconView);
        } catch (Exception e) {
            System.out.println("Error loading the left arrow icon");
        }
        
        // Logo de la boton ordonner
        try {
            Image OrdonnerIcon = new Image("file:./Pictures/folder.png");
            ImageView OrdonnerIconView = new ImageView(OrdonnerIcon);
            OrdonnerIconView.setFitWidth(20);
            OrdonnerIconView.setFitHeight(20);
            OrdonnerButton.setGraphic(OrdonnerIconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ordonner icon");
        }

        // Logo de la boton ajouter
        try {
            Image AjouterIcon = new Image("file:./Pictures/add.png");
            ImageView AjouterIconView = new ImageView(AjouterIcon);
            AjouterIconView.setFitWidth(15);
            AjouterIconView.setFitHeight(15);
            AjouterButton.setGraphic(AjouterIconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ajouter icon");
        }

        // Logo de la barre de recherche
        Image searchIcon = new Image("file:./Pictures/loupe.png");
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(20);
        searchIconView.setFitHeight(20);

        // background gray
        Rectangle BkGround = new Rectangle();
        BkGround.setWidth(screenWidth - 40); // Définir la largeur du rectangle
        BkGround.setHeight(590); // Définir la hauteur du rectangle
        BkGround.setStroke(Color.BLACK); // Définir la couleur de la bordure
        BkGround.setFill(Color.rgb(240, 240, 240)); // Remplissage avec la couleur #F0F0F0

        // Le contenir des listes
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10); // Appliquer un espacement vertical entre les éléments du GridPane
        gridPane.setHgap(10); // Appliquer un espacement horizontal entre les éléments du GridPane
        gridPane.setPrefWidth(screenWidth - 100);
        gridPane.setPrefHeight(450);
        gridPane.setStyle("-fx-background-color: #F0F0F0;");

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Désactiver la barre de défilement horizontale

        // Action du bouton Ajouter
        AjouterButton.setOnAction(event -> {
            ListFormController.handleAjouterButtonAction(gridPane);
        });
        
        

        // L'ajout des boutons à la barre de navigation
        navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);


        
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

        // la positions du scrollPane
        AnchorPane.setTopAnchor(scrollPane, 140.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(scrollPane, 75.0); // Spécifiez la distance depuis la gauche

       // la positions du gridPane
        AnchorPane.setTopAnchor(gridPane, 140.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(gridPane, 75.0); // Spécifiez la distance depuis la gauche

        // Ajout de la barre de navigation à la racine
        
        root.getChildren().add(BkGround);
     //   root.getChildren().add(gridPane);
        root.getChildren().add(scrollPane);
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        root.getChildren().addAll(navigationBar);
        root.getChildren().add(OrdonnerButton);
        root.getChildren().add(AjouterButton);
        root.getChildren().add(searchField);
        root.getChildren().add(searchIconView);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("ListStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
