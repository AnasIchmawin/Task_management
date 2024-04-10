package presentation.seance;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class screenSeance extends Application {

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

        // la taille de la barre de navigation
        navigationBar.setPrefHeight(40); // Hauteur de la barre de navigation
        navigationBar.setPrefWidth(screenWidth - 40); // Largeur de la barre de navigation

        // les Boutons :
        Button LeftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 33, 33, "button-left-style");
        Button ListesButton = createButtonWithIcon("Listes", "file:./Pictures/list.png", 20, 20, "button-style");
        Button ProjetsButton = createButtonWithIcon("Projets", "file:./Pictures/project.png", 20, 20, "button-style");
        Button ArchiveButton = createButtonWithIcon("Archive", "file:./Pictures/archive.png", 20, 20, "button-style");
        Button SeanceButton = createButtonWithIcon("Séance", "file:./Pictures/seance.png", 20, 20, "button-style");
        Button AjouterdDocButton = createButtonWithIcon("Ajouter Document", "file:./Pictures/add.png", 15, 15,
                "Ajouter-doc-style");
        Button AjouterTacheButton = createButtonWithIcon("Ajouter Tache", "file:./Pictures/addIcon.png", 15, 15,
                "Ajouter-tache-style");

        // Appliquer une marge à gauche aux boutons
        HBox.setMargin(ListesButton, new Insets(0, 0, 0, 40));
        HBox.setMargin(ProjetsButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(ArchiveButton, new Insets(0, 0, 0, 15));
        HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));

        // rectangle de background
        Rectangle BkGround = createBackgroundRectangle(screenWidth);

        // Ajout des boutons à la barre de navigation
        navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);

        // Labels
        Label TitreDescription = createLabel("Description");
        Label DateDebutLabel = createLabel("Date de début");
        Label NoteLabel = createLabel("Note");
        Label DateFinLabel = createLabel("Date de fin");
        Label DocLabel = createLabel("Documents ajoutes");

        // Ajout du texte de description et de la note
        TextArea ZoneDescription = createTextArea(screenWidth - 600, 200, "Ajouter une description", "Text-Area-Style");
        TextArea Note = createTextArea(350, 240, "Ajouter une note", "Text-Area-Style");
        //Le temps de debut et de fin de la seance en HH:MM
        TextField TempsDebut = createTextField(70, 16, "HH:MM", "Time");
        TextField TempsFin = createTextField(70, 16, "HH:MM", "Time");

        // buttons special editer
        Button Editer_Seance = EditerButton(0, 0);
        Button Editer_Note = EditerButton(0, 0);

         //Les actions des boutons
        Editer_Seance.setOnAction(e -> {
            SceanceFormController.handleEditerButton(ZoneDescription);
        });
        Editer_Note.setOnAction(e -> {
            SceanceFormController.handleEditerButton(Note);
        });

        // La date du debut et du fin YYYY-MM-DD
        DatePicker DateDebut = DateSeance();
        DatePicker DateFin = DateSeance();

        GridPane ZoneDesTaches = Grid(screenWidth - 600, 200);
        GridPane.setConstraints(AjouterTacheButton, 0, 20, 1, 1);
        ZoneDesTaches.getChildren().add(AjouterTacheButton);
        GridPane ZoneDocuments = Grid(screenWidth - 920, 150);

        // position de ZoneDocuments et DocLabel
        AnchorPane.setBottomAnchor(ZoneDocuments, 40.0);
        AnchorPane.setRightAnchor(ZoneDocuments, 75.0);
        AnchorPane.setBottomAnchor(DocLabel, 200.0);
        AnchorPane.setRightAnchor(DocLabel, 160.0);

        // Définir la position de la barre de navigation
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 20.0);

        // Définir la position du bouton de séance
        AnchorPane.setTopAnchor(SeanceButton, 75.0); 
        AnchorPane.setLeftAnchor(SeanceButton, 80.0);

        // Définir la position du bouton Ajouter Document
        AnchorPane.setTopAnchor(AjouterdDocButton, 600.0); 
        AnchorPane.setLeftAnchor(AjouterdDocButton, 80.0);

        // Définir la position du rectangle de fond
        AnchorPane.setTopAnchor(BkGround, 55.0); 
        AnchorPane.setLeftAnchor(BkGround, 20.0);

        // Définir la position du titre de la description
        AnchorPane.setTopAnchor(TitreDescription, 120.0);
        AnchorPane.setLeftAnchor(TitreDescription, 80.0);

        // Définir la position de la zone de texte
        ZoneDescription.setLayoutX(75); 
        ZoneDescription.setLayoutY(160); 

        // Définir la position du grid
        AnchorPane.setTopAnchor(ZoneDesTaches, 380.0);
        AnchorPane.setLeftAnchor(ZoneDesTaches, 80.0);

        // position de Label DateDebut
        DateDebutLabel.setLayoutX(screenWidth - 655);
        DateDebutLabel.setLayoutY(70);

        // Définir la position du date debut picker
        DateDebut.setLayoutX(screenWidth - 660);
        DateDebut.setLayoutY(105);

        //Definir la position du text field TempsDebut en HH:MM
        TempsDebut.setLayoutX(screenWidth - 500);
        TempsDebut.setLayoutY(105);

        // position de Label DateFin
        DateFinLabel.setLayoutX(screenWidth - 360);
        DateFinLabel.setLayoutY(70);

        // Définir la position du date fin picker
        DateFin.setLayoutX(screenWidth - 360);
        DateFin.setLayoutY(105);

        //Definir la position du text field TempsFin en HH:MM
        TempsFin.setLayoutX(screenWidth - 200);
        TempsFin.setLayoutY(105);

        // la position de la note
        Note.setLayoutX(screenWidth - 430);
        Note.setLayoutY(180);

        // la position de la note label
        NoteLabel.setLayoutX(screenWidth - 300);
        NoteLabel.setLayoutY(150);
        
        // position des boutons editer
        Editer_Seance.setLayoutX(730);
        Editer_Seance.setLayoutY(163);

        Editer_Note.setLayoutX(1178);
        Editer_Note.setLayoutY(185);

        // Ajout de la barre de navigation à la racine
        root.getChildren().add(BkGround);
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        root.getChildren().addAll(navigationBar);
        root.getChildren().add(SeanceButton);
        root.getChildren().add(AjouterdDocButton);
        root.getChildren().add(TitreDescription);
        root.getChildren().add(ZoneDescription);
        root.getChildren().add(ZoneDesTaches);
        root.getChildren().add(DateDebutLabel);
        root.getChildren().add(DateDebut);
        root.getChildren().addAll(TempsDebut);
        root.getChildren().add(DateFinLabel);
        root.getChildren().add(DateFin);
        root.getChildren().addAll(TempsFin);
        root.getChildren().add(Note);
        root.getChildren().add(NoteLabel);
        root.getChildren().add(Editer_Seance);
        root.getChildren().add(Editer_Note);
        root.getChildren().add(ZoneDocuments);
        root.getChildren().add(DocLabel);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("SeanceStyle.css").toExternalForm()); // Charger le fichier
                                                                                                // CSS
        primaryStage.setTitle("TO DO LIST");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // methodes de creation :

    private Button createButtonWithIcon(String name, String path, int WidthImage, int HeightImage, String style) {
        Button Button = new Button(name);
        Button.setOnMouseEntered(event -> Button.setCursor(javafx.scene.Cursor.HAND));
        Button.setOnMouseExited(event -> Button.setCursor(javafx.scene.Cursor.DEFAULT));
        Button.getStyleClass().add(style);
        try {
            Image Icon = new Image(path);
            ImageView IconView = new ImageView(Icon);
            IconView.setFitWidth(WidthImage);
            IconView.setFitHeight(HeightImage);
            Button.setGraphic(IconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ordonner icon");
        }
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

    private TextArea createTextArea(double width, double height, String promptText, String style) {
        TextArea ZoneDescription = new TextArea();
        ZoneDescription.setPrefWidth(width);
        ZoneDescription.setPrefHeight(height);
        ZoneDescription.setWrapText(true);
        ZoneDescription.setPromptText(promptText);
        ZoneDescription.setEditable(false);
        ZoneDescription.getStyleClass().add(style);

        return ZoneDescription;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("Label-style");
        return label;
    }

    private GridPane Grid(double width, double height) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPrefWidth(width);
        grid.setPrefHeight(height);
        grid.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        return grid;
    }

    private Button EditerButton(double X, double Y) {
        Button newButton = new Button();
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

    private DatePicker DateSeance() {
        DatePicker date = new DatePicker();
        date.getStyleClass().add("date-picker");
        date.promptTextProperty().set("YYYY-MM-DD");
        return date;
    }
    private TextField createTextField(double width, double height, String promptText, String style) {
        TextField text = new TextField();
        text.setPrefWidth(width);
        text.setPrefHeight(height);
        text.setPromptText(promptText);
        text.setEditable(false);
        text.getStyleClass().add(style);
        return text;
    }
}
