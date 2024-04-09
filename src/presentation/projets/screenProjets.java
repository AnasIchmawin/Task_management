package presentation.projets;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
        comboBox.setPromptText("Sélectionnez une option");

        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll("Option 6", "Option 7", "Option 9");
        comboBox1.setPromptText("option");

        Label filterLabel = new Label("     Filtrer");

        StackPane stackPane = new StackPane();

        // Appliquer le style de bouton depuis le fichier CSS
        LeftButton.getStyleClass().add("button-left-style");
        ListesButton.getStyleClass().add("button-style");
        ProjetsButton.getStyleClass().add("button-clicked-style");
        ArchiveButton.getStyleClass().add("button-style");
        OrdonnerButton.getStyleClass().add("button-style");
        AjouterButton.getStyleClass().add("button-style");
        searchField.getStyleClass().add("search-field-style");
        comboBox.getStyleClass().add("comboBox-style");
        comboBox1.getStyleClass().add("comboBox-style");

        filterLabel.getStyleClass().add("filter-label-style");

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

        HBox filterBox = new HBox();
        filterBox.getChildren().addAll(comboBox, comboBox1);
        filterBox.setSpacing(10); // Espace entre les éléments

        stackPane.getChildren().addAll(filterLabel,filterBox);


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

        AnchorPane.setTopAnchor(AjouterButton, 600.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(AjouterButton, 96.0);

        AnchorPane.setTopAnchor(searchField, 75.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchField, 221.0);

        AnchorPane.setTopAnchor(BkGround, 55.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(BkGround, 20.0);

        AnchorPane.setTopAnchor(searchIconView, 80.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(searchIconView, 394.0);

        AnchorPane.setTopAnchor(stackPane, 75.0);
        AnchorPane.setRightAnchor(stackPane, 100.0);


        // Ajout de la barre de navigation à la racine
        root.getChildren().add(BkGround);
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        root.getChildren().addAll(navigationBar);
        root.getChildren().add(OrdonnerButton);
        root.getChildren().add(AjouterButton);
        root.getChildren().add(searchField);
        root.getChildren().add(searchIconView);
        root.getChildren().add(stackPane);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("projetsStyle.css").toExternalForm()); // Charger le fichier CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
