package mygroup.presentation.seance_ajoute;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mygroup.presentation.NewProjet.AddProjetController;
import mygroup.presentation.projet_detail.ProjetDetailController;

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
    private VBox ContainerGoogleCalendar;
    private DatePicker dateSeance;
    Button confirmerButton;

    // Constructor
    // public SceanceAjouteView(AddProjetController addProjetController) {
    //     this.controller = new SceanceAjouteController(this, addProjetController);
    //     init();
    //     style();
    //     Action();
    // }

    public SceanceAjouteView(ProjetDetailController ProjetDetailController) {
        this.controller = new SceanceAjouteController(this, ProjetDetailController);
        init();
        style();
        Action();
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane container = createMainContent();
        root = createBorderPane(container);
        Scene scene = new Scene(root, 850, 500);
        scene.getStylesheets().add(getClass().getResource("SeanceStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Formulaire de Séance");
        //set position of show in screen
        primaryStage.setX(180);
        primaryStage.setY(70);
        primaryStage.show();
    }

    public void init() {
        leftButton = createButton("", "file:./mygroup/src/main/java/Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        titreField = new TextField();
        titreField.setPromptText("Entrer le titre de la séance");
        ajouterDocButton = createButton("Ajouter Document", "file:./mygroup/src/main/java/Pictures/addIcon.png", 20, 20);
        sauvegarderButton = new Button("Sauvegarder");
        ContainerGoogleCalendar = new VBox();
        ContainerGoogleCalendar.setPadding(new Insets(2, 2, 2, 2));
        ContainerGoogleCalendar.setSpacing(5);
        confirmerButton = createButton("", "file:./mygroup/src/main/java/Pictures/confirmer.png", 29, 29);

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
        ContainerGoogleCalendar.getStyleClass().add("google-calendar-style");
        confirmerButton.getStyleClass().add("confirm-btn-style");
    }

    private BorderPane createBorderPane( StackPane container) {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #112D4E;");
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setCenter(container);
        return root;
    }

    
    private StackPane createMainContent() {
        // le background de la page gris
        StackPane container = new StackPane();

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
        HBox.setMargin(Dates, new Insets(0, 50, 20, 0));

        VBox containerTitle = CreateVbox(0, TOP_LEFT);
        HBox.setMargin(containerTitle, new Insets(0, 50, 20, 0));
        Label labelTitle = createLabel("Titre");
        containerTitle.getChildren().addAll(labelTitle, titreField);
        topContainer.getChildren().addAll(containerTitle, Space_Btn_Dates, Dates);
        topContainer.getStyleClass().add("topContainer-style");
        mainContentContainer.getChildren().addAll(topContainer);
        HBox centerContainer = CreateHbox(20, CENTER_LEFT);
        VBox leftBox = CreateVbox(15, TOP_CENTER);
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
        VBox rightBox = CreateVbox(30, TOP_CENTER);
        Label labelNote = createLabel("Note");
        ZoneNote = createTextArea("Ajouter une note", "ZoneNote-Style");
        ContainerGoogleCalendar = createGoogleCalendarContainer();
        VBox ContainerNote = CreateVbox(0, TOP_CENTER);
        ContainerNote.getChildren().addAll(labelNote, ZoneNote);
        rightBox.getChildren().addAll(ContainerNote, ContainerGoogleCalendar);
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

    private VBox createGoogleCalendarContainer() {
        VBox containerGoogleCalendar = new VBox();
        containerGoogleCalendar.setPadding(new Insets(10, 10, 10, 10));
        containerGoogleCalendar.setSpacing(5);
        containerGoogleCalendar.getStyleClass().add("google-calendar-style");
        Label labelGoogleCalender = new Label("  Importer depuis  Google Calendrier");
        labelGoogleCalender.getStyleClass().add("google-calendar-label-style");
        labelGoogleCalender.setWrapText(true);
        HBox dateGoogleCalendar = createDateGoogleCalendar();
        containerGoogleCalendar.getChildren().addAll(labelGoogleCalender, dateGoogleCalendar);
        HBox.setMargin(ContainerGoogleCalendar, new Insets(100, 70, 0, 0));
        return containerGoogleCalendar;
    }

    private HBox createDateGoogleCalendar() {
        HBox dateGoogleCalendar = new HBox();
        dateGoogleCalendar.setAlignment(Pos.TOP_LEFT);
        dateGoogleCalendar.setSpacing(5);
        dateSeance = new DatePicker();
        dateSeance.setPromptText("YYYY-MM-DD");
        dateSeance.setPrefWidth(130);
        HBox.setMargin(confirmerButton, new Insets(2, 0, 0, 0));
        dateGoogleCalendar.getChildren().addAll(dateSeance, confirmerButton);
        return dateGoogleCalendar;
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

      private Button createButton(String name, String path, int width, int height) {
        Button newButton = new Button();
        try {
            ImageView icon = new ImageView(new Image(path));
            icon.setFitWidth(width);
            icon.setFitHeight(height);
            Text buttonText = new Text(name);
            buttonText.setFill(Color.WHITE);
            HBox buttonContent = new HBox(buttonText, icon);
            buttonContent.setAlignment(Pos.CENTER);
            buttonContent.setSpacing(4);
            newButton.setGraphic(buttonContent);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du bouton : " + e.getMessage());
        }
        return newButton;
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
        confirmerButton.setOnAction(event -> {
            this.controller.handleConfirmerButtonAction();
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

    public String getDateSeance() {
        return dateSeance.getValue().toString();
    }

    public void close() {
        getStage().close();
    }

}