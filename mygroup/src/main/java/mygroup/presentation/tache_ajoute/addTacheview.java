package mygroup.presentation.tache_ajoute;


import javafx.geometry.Insets;
import javafx.scene.Cursor;
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
import mygroup.presentation.taches.TachesFormController;
import javafx.scene.control.ComboBox;
//mod
public class addTacheview {

    private static final Pos TOP_CENTER = Pos.TOP_CENTER;
    private static final Pos TOP_LEFT = Pos.TOP_LEFT;
    private static final Pos CENTER_LEFT = Pos.CENTER_LEFT;
    private TextField titreField;
    private TextArea ZoneDescription;
    private ComboBox<String>  categorie;
    private DatePicker dateDebut;
    private DatePicker dateFin;
    private TextField TempsDebut;
    private TextField TempsFin;
    // private Button Enregistrer;
    private Button Annuler;
    private Button Sauvegarder;
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button ajouterDocButton;
    private GridPane ZoneDocuments;
    private ControllerFromTacheAjout controller;
    private BorderPane root;


    public addTacheview() {
        this.controller = new ControllerFromTacheAjout(this);
        init();
        style();
        action();
    }

    public addTacheview(TachesFormController controller) {
        this.controller = new ControllerFromTacheAjout(this, controller);
        init();
        style();
        action();
    }
    
    public void start(Stage primaryStage) {
        // Create the nnavigation bar
        VBox navbarContainer = createNavbarContainer();
        // Create the main content
        StackPane container = createMainContent();
        // Create the root layout
        root = createBorderPane(navbarContainer, container);
        // Create the scene
        Scene scene = new Scene(root, 1160, 652);
        // Add the CSS file
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Responsive Page with Navbar");
        primaryStage.show();

    }

    
    public void init() {
        // Enregistrer = createButtonWithIcon("Enregistrer", "file:./Pictures/save.png", 20, 20);
        Annuler = createButtonWithIcon("Annuler", "file:./Pictures/annuler.png", 20, 20);
        Sauvegarder = createButtonWithIcon("Ajouter", "file:./Pictures/add.png", 20, 20);
        leftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = createButton("Listes");
        projectsButton = createButton("Projets");
        archiveButton = createButton("Archive");
        ajouterDocButton = createButtonWithIcon("Ajouter Document", "file:./Pictures/add.png", 20, 20);
        titreField = new TextField();
        titreField.setPromptText("Entrer le titre de la tache");
    }

    private void style() {
        Sauvegarder.getStyleClass().add("footBtn-style");
        Annuler.getStyleClass().add("footBtn-style");
        // Enregistrer.getStyleClass().add("footBtn-style");
        leftButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ajouterDocButton.getStyleClass().add("AjouterTache-style");
        titreField.getStyleClass().add("Titre-style");
        
    }

    private BorderPane createBorderPane(VBox navbarContainer, StackPane container) {
        BorderPane root = new BorderPane();
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
        navbar.setPadding(new Insets(10, 20, 10, 20)); // 20px padding left and right, 10px padding top and bottom
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    private StackPane createMainContent() {
        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        container.setPadding(new Insets(15, 70, 20, 55)); // 15px padding top, 70px padding right,
                                                          // 20px padding bottom, 55px padding left
        TempsDebut = createTextField();
        TempsFin = createTextField();

        VBox mainContentContainer = CreateVbox(10, TOP_CENTER);

        HBox topContainer = CreateHbox(10, TOP_LEFT);

        Region Space_Dates = CreateSPace(50);
        Region Space_element = CreateSPace(50);

        Region Space_Btn_Dates = CreateSPace(50);
        Region verticalSpace = createVerticalSpace(50);
        Region verticalSpace1 = createVerticalSpace(20);
        Region verticalSpace2 = createVerticalSpace(20);

        HBox DateDebutComplet = CreateHbox(5, TOP_CENTER);
        dateDebut = DateTache();
        Label labelDebut = createLabel("Date de début");

        VBox dateDebutContainer = createVboxDates(DateDebutComplet, dateDebut, TempsDebut, labelDebut, Space_Dates);

        // Date picker pour la date de fin
        HBox DateFinComplet = CreateHbox(5, TOP_CENTER);
        dateFin = DateTache();
        Label labelFin = createLabel("Date de fin");

        // Creer un conteneur VBox pour la date de fin
        VBox dateFinContainer = createVboxDates(DateFinComplet, dateFin, TempsFin, labelFin, Space_Dates);

        Label labelCategorie = createLabel("Catégorie");

        categorie = new ComboBox<>();
        categorie.getItems().addAll("Enseignement", "Encadrement", "Autre");
        categorie.setPromptText("select");
        categorie.getStyleClass().add("category-combobox-style");

        VBox catgContainer = new VBox();
        catgContainer.getChildren().addAll(labelCategorie,categorie);

        // Créer un conteneur HBox pour les deux dates
        HBox Dates = new HBox();
      //  Dates.getChildren().addAll(dateDebutContainer, Space_Dates, dateFinContainer, Space_Dates, categoryComboBox);
        Dates.getChildren().addAll(dateDebutContainer, Space_Dates, dateFinContainer,Space_element, catgContainer);


        HBox.setMargin(Dates, new Insets(0, 50, 0, 0)); // Margin (top, right, bottom, left)

        topContainer.getChildren().addAll(Space_Btn_Dates, Dates);

        mainContentContainer.getChildren().addAll(topContainer); // Add top container and

        HBox centerContainer = CreateHbox(50, CENTER_LEFT);

        VBox leftBox = CreateVbox(3, TOP_LEFT);

        Label labelTitle = createLabel("Titre");

        Label labelDescription = createLabel("Discription");

        ZoneDescription = createTextArea("Description", "ZoneDescription-Style");

        VBox contenaireDocuments = CreateVbox(1, TOP_LEFT);
        Label labelDocs = createLabel("Documents Ajoutés");
        VBox ZoneDocuments = createDocumentsSection();
        contenaireDocuments.getChildren().addAll(labelDocs, ZoneDocuments);
        HBox buttons = new HBox(10, Sauvegarder, Annuler);

        leftBox.getChildren().add(labelTitle);
        leftBox.getChildren().add(titreField);
        leftBox.getChildren().add(verticalSpace2);
        leftBox.getChildren().add(labelDescription);
        leftBox.getChildren().add(ZoneDescription);
        leftBox.getChildren().add(verticalSpace1);
        leftBox.getChildren().add(labelDocs);
        leftBox.getChildren().addAll(contenaireDocuments,verticalSpace, buttons);

        centerContainer.getChildren().addAll(leftBox);

        mainContentContainer.getChildren().add(centerContainer);

        container.getChildren().addAll(mainContentContainer);

        return container;
    }

    private GridPane creatZoneDocs() {
        GridPane grid = new GridPane();
        // grid.setPadding(new Insets(10, 10, 10, 10));
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
            button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
            button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
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

    private DatePicker DateTache() {
        DatePicker date = new DatePicker();
        date.getStyleClass().add("date-picker");
        date.promptTextProperty().set("YYYY-MM-DD");
        return date;
    }

    private TextArea createTextArea(String promptText, String style) {
        TextArea Zone = new TextArea();
        Zone.setWrapText(true);
        Zone.setPromptText(promptText);
        Zone.getStyleClass().add(style);

        return Zone;
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
    private Region createVerticalSpace(int height) {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        spacer.setMinHeight(height);
        return spacer;
    }

    private VBox createVboxDates(HBox myBox, DatePicker date, TextField temps, Label label, Region Space_Dates) {
        myBox.getChildren().addAll(date, temps);
        VBox vbox = new VBox();
        HBox.setHgrow(Space_Dates, Priority.ALWAYS);
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
    private Button createButton(String text) {
        Button button = new Button(text);

        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));

        return button;
    }

    public void action() {
        

        listesButton.setOnAction(event -> {
            this.controller.handleListesButton();
        });
       
        projectsButton.setOnAction(event -> {
            this.controller.handleProjetsButton();
        });

        archiveButton.setOnAction(event -> {
            this.controller.handleArchiveButton();
        }); 
        leftButton.setOnAction(event -> {
            System.out.println("Ajouter Document button clicked left ");
        });
        ajouterDocButton.setOnAction(event -> {
            this.controller.handleAjouterButtonAction();
        });
        Sauvegarder.setOnAction(event -> {
            this.controller.handleSauvegarderButtonAction();
        });
        // Enregistrer.setOnAction(event -> {
        //     this.controller.handleUpdateButtonAction();
        // });
        Annuler.setOnAction(event -> {
            try {
                this.controller.closerWindow(event);
            } catch (Exception e) {
                System.out.println("Erreur pendant la fermeture AddList  : " + e.getMessage());
            }
        });
    }

    public String getDateDebut() {
        return dateDebut.getValue().toString();
    }

    public String getDateFin() {
        return dateFin.getValue().toString();
    }

    public String gettitre() {
        return titreField.getText();
    }

    public String getDescription() {
        return ZoneDescription.getText();
    }

    public String getCategorie() {
        return categorie.getValue();
    }


    public String getTempsDebut() {
        return TempsDebut.getText();
    }

    public String getTempsFin() {
        return TempsFin.getText();
    }

    public GridPane getZoneDocuments() {
        return ZoneDocuments;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }


    

    

}