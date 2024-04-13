package presentation.listes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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

        Button OrdonnerButton = createButtonWithIcon("Ordonner", "button-style", "file:./Pictures/folder.png", 20, 20,
                75, 96, true);
        Button AjouterButton = createButtonWithIcon("Ajouter", "button-style", "file:./Pictures/add.png", 15, 15, 600,
                96, false);
        Button saveButton = createButtonWithIcon("Sauvegarder", "button-save-style", "file:./Pictures/save.png", 20, 20,
                600, 220, false);
        Button modifierButton = createButtonWithIcon("Modifier", "button-style", "file:./Pictures/modifier.png", 15, 15,
                600, 96, true);

        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher");

        searchField.getStyleClass().add("search-field-style");

        // Label description :
        Label descriptionLabel = new Label("Description :");
        descriptionLabel.getStyleClass().add("description-label-style");

        // description de la liste
        TextArea description = new TextArea();
        description.setPromptText("Description");
        description.setWrapText(true);
        description.getStyleClass().add("description-style");
        description.setEditable(false);

        // Button editer
        Button editerButton = EditerButton(1145, 165, false);

        // action du bouton editer
        editerButton.setOnAction(e -> {
            ListFormController.handleEditerButton(saveButton, description);
        });
        saveButton.setOnAction(e -> {
            ListFormController.handleSaveButton(modifierButton, saveButton, AjouterButton, editerButton, description);
        });

        modifierButton.setOnAction(e -> {
            ListFormController.handleModifierButton(modifierButton, saveButton, AjouterButton, editerButton);
        });

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

        // la position du label description
        AnchorPane.setTopAnchor(descriptionLabel, 130.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(descriptionLabel, 100.0);

        // la position du description
        AnchorPane.setTopAnchor(description, 160.0); // Spécifiez la distance depuis le haut
        AnchorPane.setLeftAnchor(description, 100.0);

        // Ajout de la barre de navigation à la racine

        root.getChildren().addAll(BkGround, navigationBar, OrdonnerButton, AjouterButton, searchField, searchIconView,
                scrollPane, descriptionLabel, description, editerButton, saveButton, modifierButton);

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
        Button LeftButton = createButtonWithIcon("", "button-left-style", "file:./Pictures/left-arrow.png", 33, 33, 0,
                0, true);

        ListesButton.getStyleClass().add("button-style");
        ProjetsButton.getStyleClass().add("button-style");
        ArchiveButton.getStyleClass().add("button-style");

        HBox.setMargin(ListesButton, new Insets(0, 0, 0, 40));
        HBox.setMargin(ProjetsButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(ArchiveButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));

        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 20.0);

        navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);

        return navigationBar;
    }

    public Button createButtonWithIcon(String name, String Style, String string, int i, int j, double k, double l,
            boolean isVisible) {
        Button Button = new Button(name);
        Button.setOnMouseEntered(event -> Button.setCursor(javafx.scene.Cursor.HAND));
        Button.setOnMouseExited(event -> Button.setCursor(javafx.scene.Cursor.DEFAULT));
        try {
            Image Icon = new Image(string);
            ImageView IconView = new ImageView(Icon);
            IconView.setFitWidth(i);
            IconView.setFitHeight(j);
            Button.setGraphic(IconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ordonner icon");
        }
        Button.getStyleClass().add(Style);
        Button.setVisible(isVisible);

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
        gridPane.setPrefWidth(width - 200);
        gridPane.setPrefHeight(280);
        gridPane.setStyle("-fx-background-color: #F0F0F0;");
        AnchorPane.setTopAnchor(gridPane, 310.0);
        AnchorPane.setLeftAnchor(gridPane, 100.0);
        return gridPane;
    }

    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        AnchorPane.setTopAnchor(scrollPane, 310.0);
        AnchorPane.setLeftAnchor(scrollPane, 100.0);
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

    private Button EditerButton(double X, double Y, boolean isVisible) {
        Button newButton = new Button();
        newButton.setVisible(isVisible);
        newButton.setOnMouseEntered(event -> newButton.setCursor(javafx.scene.Cursor.HAND));
        newButton.setOnMouseExited(event -> newButton.setCursor(javafx.scene.Cursor.DEFAULT));
        newButton.setShape(new Rectangle(20, 20));
        newButton.setMinSize(20, 20);
        newButton.setMaxSize(20, 20);
        newButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
        newButton.setLayoutX(X);
        newButton.setLayoutY(Y);

        try {
            Image careeIcon = new Image("file:./Pictures/edit.png");
            ImageView careeIconView = new ImageView(careeIcon);
            careeIconView.setFitWidth(15);
            careeIconView.setFitHeight(15);
            newButton.setGraphic(careeIconView);
        } catch (Exception e) {
            System.out.println("Error loading the caree icon");
        }
        return newButton;
    }
}
