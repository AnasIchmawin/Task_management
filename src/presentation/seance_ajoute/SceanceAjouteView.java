package presentation.seance_ajoute;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceanceAjouteView extends Application {
    private static final Pos TOP_CENTER = Pos.TOP_CENTER;
    private static final Pos TOP_LEFT = Pos.TOP_LEFT;
    private static final Pos CENTER_LEFT = Pos.CENTER_LEFT;
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button ajouterDocButton;
    private Button sauvegarderButton;
    private SceanceAjouteController controller;
    private BorderPane root;
    private TextField titreField;
    private TextField TempsDebut;
    private TextField TempsFin;
    private TextArea ZoneDescription;
    private DatePicker dateDebut;
    private DatePicker dateFin;
    private GridPane ZoneDocuments;
    private TextArea ZoneNote;

    // Constructor
    public SceanceAjouteView() {
        this.controller = new SceanceAjouteController(this);
        init();
        style();
        Action();
    }

    @Override
    public void start(Stage primaryStage) {
        VBox navbarContainer = createNavbarContainer();
        StackPane container = createMainContent();
        root = createBorderPane(navbarContainer, container);
        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("SeanceStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Formulaire de Séance");
        primaryStage.show();
    }

    public void init() {
        leftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        titreField = new TextField();
        titreField.setPromptText("Entrer le titre de la séance");
        ajouterDocButton = createButtonWithIcon("Ajouter Document", "file:./Pictures/addIcon.png", 20, 20);
        sauvegarderButton = new Button("Sauvegarder");

    }

    private void style() {
        leftButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ajouterDocButton.getStyleClass().add("AjoutDoc-style");
        SurveillerButton(listesButton, "100px", "40px", "#3F72AF");
        SurveillerButton(projectsButton, "100px", "40px", "#3F72AF");
        SurveillerButton(archiveButton, "100px", "40px", "#3F72AF");
        SurveillerButton(leftButton, "60", "40px", "#3F72AF");
        titreField.getStyleClass().add("Titre-style");
        sauvegarderButton.getStyleClass().add("footBtn-style");
    }

    private BorderPane createBorderPane(VBox navbarContainer, StackPane container) {
        root = new BorderPane();
        root.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(navbarContainer, new Insets(0, 20, 0, 20));
        root.setTop(navbarContainer);
        BorderPane.setMargin(container, new Insets(20, 20, 20, 20));
        root.setCenter(container);
        return root;
    }

    private VBox createNavbarContainer() {
        HBox buttonsBar = new HBox(20, listesButton, projectsButton, archiveButton);
        HBox leftButtonBox = new HBox(20, leftButton);
        HBox navbar = new HBox(30, leftButtonBox, buttonsBar);
        navbar.setPadding(new Insets(10, 20, 10, 20));
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    private StackPane createMainContent() {
        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        container.setPadding(new Insets(15, 70, 20, 55));

        // Créer un TextField pour l'heure de début et de fin
        TempsDebut = createTextField();
        TempsFin = createTextField();

        // Créer un conteneur VBox pour contenir les éléments principaux
        VBox mainContentContainer = CreateVbox(5, TOP_CENTER);

        // Top Container
        HBox topContainer = CreateHbox(5, TOP_LEFT);

        // Créer un espaceur entre les conteneurs Dates
        Region Space_Dates = CreateSPace(30);

        // Créer un espaceur entre le conteneur Dates et le bouton de séance
        Region Space_Btn_Dates = CreateSPace(30);

        // Date picker pour la date de début
        HBox DateDebutComplet = CreateHbox(5, TOP_CENTER);
        dateDebut = DateSeance();
        Label labelDebut = createLabel("Date de début");
        VBox dateDebutContainer = createVboxDates(DateDebutComplet, dateDebut, TempsDebut, labelDebut, Space_Dates);
        HBox DateFinComplet = CreateHbox(5, TOP_CENTER);
        dateFin = DateSeance();
        Label labelFin = createLabel("Date de fin");
        VBox dateFinContainer = createVboxDates(DateFinComplet, dateFin, TempsFin, labelFin, Space_Dates);
        HBox Dates = new HBox();
        Dates.getChildren().addAll(dateDebutContainer, Space_Dates, dateFinContainer);
        HBox.setMargin(Dates, new Insets(30, 50, 20, 0));

        VBox containerTitle = CreateVbox(0, TOP_LEFT);
        HBox.setMargin(containerTitle, new Insets(30, 50, 20, 0));
        Label labelTitle = createLabel("Titre");
        containerTitle.getChildren().addAll(labelTitle, titreField);
        topContainer.getChildren().addAll(containerTitle, Space_Btn_Dates, Dates);
        topContainer.getStyleClass().add("topContainer-style");
        mainContentContainer.getChildren().addAll(topContainer);
        HBox centerContainer = CreateHbox(20, CENTER_LEFT);
        VBox leftBox = CreateVbox(15, TOP_LEFT);
        VBox ContainerDescription = CreateVbox(1, TOP_LEFT);
        Label labelDescription = createLabel("Discription");
        ZoneDescription = createTextArea("Description", "ZoneDescription-Style");

        VBox contenaireDocuments = CreateVbox(1, TOP_LEFT);
        Label labelDocs = createLabel("Documents Ajoutés");
        VBox ZoneDocuments = createDocumentsSection();
        contenaireDocuments.getChildren().addAll(labelDocs, ZoneDocuments);

        ContainerDescription.getChildren().addAll(labelDescription, ZoneDescription);
        leftBox.getChildren().addAll(ContainerDescription);
        leftBox.getChildren().addAll(contenaireDocuments, sauvegarderButton);
        VBox rightBox = CreateVbox(0, TOP_CENTER);
        Label labelNote = createLabel("Note");
        ZoneNote = createTextArea("Ajouter une note", "ZoneNote-Style");
        rightBox.getChildren().addAll(labelNote, ZoneNote);
        rightBox.getStyleClass().add("rightBox-style");
        centerContainer.getChildren().addAll(leftBox, rightBox);
        mainContentContainer.getChildren().add(centerContainer);
        container.getChildren().addAll(mainContentContainer);
        return container;
    }

    private GridPane creatZoneDocs() {
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        return grid;
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

    private Button createButtonWithIcon(String name, String string, int i, int j) {
        Button button = new Button(name);
        try {
            Image icon = new Image(string);
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(i);
            iconView.setFitHeight(j);
            button.setGraphic(iconView);
        } catch (Exception e) {
            System.out.println("Error loading the icon: " + e.getMessage());
        }
        return button;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("Label-style");
        return label;
    }

    private DatePicker DateSeance() {
        DatePicker date = new DatePicker();
        date.getStyleClass().add("date-picker");
        date.promptTextProperty().set("YYYY-MM-DD");
        return date;
    }

    private TextArea createTextArea(String promptText, String style) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPromptText(promptText);
        textArea.getStyleClass().add(style);

        return textArea;
    }

    private TextField createTextField() {
        TextField text = new TextField();
        text.setPromptText("HH:MM");
        text.getStyleClass().add("Hour-Minute-Style");
        return text;
    }

    private VBox CreateVbox(int Spacing, Pos position) {
        VBox vbox = new VBox();
        vbox.setSpacing(Spacing);
        vbox.setAlignment(position);
        return vbox;
    }

    private HBox CreateHbox(int Spacing, Pos position) {
        HBox hbox = new HBox();
        hbox.setSpacing(Spacing);
        hbox.setAlignment(position);
        return hbox;
    }

    private Region CreateSPace(int width) {
        Region spacer = new Region();
        spacer.setPrefWidth(width);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private VBox createVboxDates(HBox myBox, DatePicker date, TextField temps, Label label, Region Space_Dates) {
        myBox.getChildren().addAll(date, temps);
        VBox vbox = new VBox();
        HBox.setHgrow(Space_Dates, Priority.ALWAYS); // Make spacer grow as much as possible
        vbox.getChildren().addAll(label, myBox);
        return vbox;
    }

    private VBox createDocumentsSection() {
        ZoneDocuments = creatZoneDocs();
        ScrollPane scrollDocs = createScrollPane(ZoneDocuments);
        scrollDocs.getStyleClass().add("Docs-Style");
        VBox contenaireDocuments = CreateVbox(5, Pos.TOP_LEFT);
        contenaireDocuments.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        contenaireDocuments.getChildren().addAll(scrollDocs, ajouterDocButton);
        contenaireDocuments.setPadding(new Insets(10, 10, 10, 10));

        return contenaireDocuments;
    }

    public void SurveillerButton(Button button, String width, String height, String color) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: #8E9EB2; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: " + color + ";" +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
    }

    private void Action() {
        listesButton.setOnAction(event -> {
            this.controller.handleListesButton();
        });
        // Action for AjouterButton
        projectsButton.setOnAction(event -> {
            this.controller.handleProjetsButton();
        });

        // Action for AjouterButton
        archiveButton.setOnAction(event -> {
            this.controller.handleArchiveButton();
        });
        leftButton.setOnAction(event -> {
            System.out.println("Ajouter Document button clicked left ");
        });
        ajouterDocButton.setOnAction(event -> {
            this.controller.handleAjouterButtonAction();
        });
        sauvegarderButton.setOnAction(event -> {
            this.controller.handleSauvegarderButtonAction();
        });
    }
    public String getTitre() {
        return titreField.getText();
    }

    public String getDescription() {
        return ZoneDescription.getText();
    }

    public String getDateDebut() {
        return dateDebut.getValue().toString();
    }

    public String getTimeDebut() {
        return TempsDebut.getText();
    }

    public String getTimeFin() {
        return TempsFin.getText();
    }

    public String getDateFin() {
        return dateFin.getValue().toString();
    }

    public GridPane getZoneDocuments() {
        return ZoneDocuments;
    }

    public String getZoneNote() {
        return ZoneNote.getText();
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }

}