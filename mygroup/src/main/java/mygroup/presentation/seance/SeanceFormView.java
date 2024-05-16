package mygroup.presentation.seance;

import javafx.scene.control.Label;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

public class SeanceFormView extends Application {
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    @SuppressWarnings("unused")
    private SeanceFormController controller;
    private BorderPane root;
    GridPane ZoneListes;
    private HBox buttonsBar;
    private HBox leftButtonBox;
    private HBox navbar;
    private VBox navbarContainer;
    private VBox mainContentContainer;
    private StackPane container;
    private ScrollPane scrollPane;
    private VBox Listes;
    private HBox topContainer;
    private Region spacer;
    private HBox HeadBox;
    private Label titre;
    private Label dateDebut;
    private Label dateFin;
    private VBox descriptionContainer;
    private VBox noteContainer;
    private HBox centerContainer;
    private VBox leftside;
    private VBox rightside;
    private String labelNote;
    private String descNote;
    private VBox SectionDocs;
    private Button ajoutDocButton;
    private Button sauvegardButton;

    public SeanceFormView() {
        this.controller = new SeanceFormController(this);
        Initialiser();
        Styler();
        Dessiner();
        Action();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("SeanceStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Responsive Page with Navbar");
        primaryStage.show();
    }

    private void Initialiser() {
        leftButton = createButton("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        buttonsBar = new HBox(20, listesButton, projectsButton, archiveButton);
        leftButtonBox = new HBox(20, leftButton);
        navbar = new HBox(30, leftButtonBox, buttonsBar);
        navbarContainer = new VBox(navbar);
        mainContentContainer = new VBox();
        mainContentContainer.setSpacing(15);
        container = new StackPane();
        scrollPane = createScrollPane(ZoneListes);
        Listes = new VBox();
        Listes.setSpacing(15);
        topContainer = new HBox();
        spacer = new Region();
        root = new BorderPane();
        SurveillerButton(projectsButton, "100px", "40px", "#3F72AF");
        SurveillerButton(archiveButton, "100px", "40px", "#3F72AF");
        titre = new Label("Titre de la seance");
        dateDebut = new Label("21/12/2021");
        dateFin = new Label("21/12/2021");
        HeadBox = boxHead(titre, dateDebut, dateFin);
        descriptionContainer = BoxDescription("This is a description");
        labelNote = "Note";
        descNote = "This is a note";
        noteContainer = creareVBoxSpecial(labelNote, descNote);
        leftside = new VBox();
        leftside.setSpacing(20);
        rightside = new VBox();
        centerContainer = new HBox();
        ajoutDocButton = createButton("Ajouter un document", "file:./Pictures/addIcon.png", 20, 20);
        sauvegardButton = createButton("Sauvegarder", "file:./Pictures/save.png", 15, 15);
        SectionDocs = createSeancesBox();

    }

    private void Styler() {
        root.getStyleClass().add("root");
        leftButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-clicked-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        navbar.getStyleClass().add("navbar");
        navbarContainer.getStyleClass().add("navbar-container");
        container.getStyleClass().add("container");
        scrollPane.getStyleClass().add("scroll-pane");
        noteContainer.getStyleClass().add("noteContainer");
        HeadBox.getStyleClass().add("head-box");
        ajoutDocButton.getStyleClass().add("add-doc-button");
        descriptionContainer.getStyleClass().add("description-container");
        sauvegardButton.getStyleClass().add("button-style");

    }

    private void Dessiner() {
        navbar.setPadding(new Insets(10, 20, 10, 20));
        BorderPane.setMargin(navbarContainer, new Insets(0, 20, 0, 20));
        container.getChildren().addAll(mainContentContainer);
        BorderPane.setMargin(container, new Insets(20, 20, 20, 20));
        topContainer.setAlignment(Pos.TOP_LEFT);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topContainer.getChildren().add(HeadBox);
        leftside.getChildren().addAll(descriptionContainer, SectionDocs);
        rightside.getChildren().addAll(noteContainer);
        centerContainer.setSpacing(50);
        centerContainer.getChildren().addAll(leftside, rightside);
        HBox.setHgrow(rightside, Priority.ALWAYS);
        mainContentContainer.getChildren().addAll(topContainer, centerContainer, sauvegardButton);
        root.setTop(navbarContainer);
        root.setCenter(container);

    }

    private VBox creareVBoxSpecial(String string, String string2) {
        VBox SpecialContainer = new VBox();
        Label label = new Label(string);
        label.getStyleClass().add("head-label");
        VBox containerNotelabel = new VBox();
        containerNotelabel.getChildren().add(label);
        containerNotelabel.setAlignment(Pos.TOP_CENTER);
        Label label2 = new Label(string2);
        label2.getStyleClass().add("noteDesc-style");
        SpecialContainer.getChildren().addAll(containerNotelabel, label2);
        SpecialContainer.setSpacing(10);
        return SpecialContainer;

    }

    private HBox boxHead(Label title, Label dateDebut, Label dateFin) {
        HBox hboxHead = new HBox();
        hboxHead.setSpacing(400);

        Label[] labels = { new Label("Debut"), new Label("Fin") };
        for (Label label : labels) {
            label.getStyleClass().add("head-label");
        }

        Label labelDebutDate = new Label(dateDebut.getText());
        Label labelFinDate = new Label(dateFin.getText());
        labelDebutDate.getStyleClass().add("date-style");
        labelFinDate.getStyleClass().add("date-style");

        VBox[] containers = { new VBox(), new VBox() };
        containers[0].getChildren().addAll(labels[0], labelDebutDate);
        containers[1].getChildren().addAll(labels[1], labelFinDate);
        for (VBox container : containers) {
            container.setAlignment(Pos.TOP_CENTER);
        }

        HBox Dates = new HBox();
        Dates.getChildren().addAll(containers[0], containers[1]);
        Dates.setSpacing(50);

        hboxHead.getChildren().addAll(title, Dates);

        title.getStyleClass().add("head-label");
        title.setPadding(new Insets(20, 0, 0, 0));

        return hboxHead;
    }

    private VBox BoxDescription(String description) {
        Label indexdescription = new Label("Description ");
        indexdescription.getStyleClass().add("index-style");

        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        VBox vboxDesc = new VBox();
        vboxDesc.setAlignment(Pos.TOP_LEFT); // Align the VBox to the top left
        vboxDesc.getChildren().addAll(descriptionLabel);
        vboxDesc.getStyleClass().add("description-style");

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.TOP_LEFT); // Align the outer VBox to the top left
        vbox1.getChildren().addAll(indexdescription, vboxDesc);

        return vbox1;
    }

    private Button createButton(String name, String path, int width, int height) {
        Button newButton = new Button();
        ImageView icon = new ImageView(new Image(path));
        icon.setFitWidth(width);
        icon.setFitHeight(height);
        Text buttonText = new Text(name);
        buttonText.setFill(Color.WHITE);
        HBox buttonContent = new HBox(buttonText, icon);
        buttonContent.setAlignment(Pos.CENTER);
        buttonContent.setSpacing(4);
        newButton.setGraphic(buttonContent);
        return newButton;
    }

    public GridPane getZoneListes() {
        return ZoneListes;
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

    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    private VBox createSeancesBox() {
        ScrollPane scrollTask = createScrollPane(ZoneListes);
        scrollTask.getStyleClass().add("scroll-Style");
        VBox contenaireTaches = new VBox();
        contenaireTaches.setSpacing(5);
        contenaireTaches.setAlignment(Pos.TOP_LEFT);
        contenaireTaches.getStyleClass().add("contenaire-taches");
        VBox contenaireAddDoc = new VBox();
        contenaireAddDoc.getChildren().add(ajoutDocButton);
        contenaireAddDoc.setAlignment(Pos.BOTTOM_CENTER);
        contenaireTaches.getChildren().addAll(scrollTask, contenaireAddDoc);
        contenaireTaches.setPadding(new Insets(10, 10, 5, 10));
        return contenaireTaches;
    }

    // Action des boutons
    private void Action() {
        leftButton.setOnAction(event -> {
          //  controller.goToHome();
        });
        listesButton.setOnAction(event -> {
            //controller.goToListes(event);
        });
        projectsButton.setOnAction(event -> {
           // controller.goToProjects(event);
        });
        archiveButton.setOnAction(event -> {
           // controller.goToArchive(event);
        });
        ajoutDocButton.setOnAction(event -> {
          //  controller.goToAjoutDoc(event);
        });
    }

    public void setTitre(String nouveauTitre) {
        titre.setText(nouveauTitre);
    }

    public void setDateDebut(String nouvelleDateDebut) {
        dateDebut.setText(nouvelleDateDebut);
    }

    public void setDateFin(String nouvelleDateFin) {
        dateFin.setText(nouvelleDateFin);
    }

    public void setDescription(String nouvelleDescription) {
        descriptionContainer.getChildren().clear();
        descriptionContainer.getChildren().add(BoxDescription(nouvelleDescription));
    }

    public void setNoteLabel(String nouvelleNoteLabel) {
        labelNote = nouvelleNoteLabel;
    }

    public void setNoteDescription(String nouvelleNoteDescription) {
        descNote = nouvelleNoteDescription;
    }
    // getters

    public String getTitre() {
        return titre.getText();
    }

    public String getDateDebut() {
        return dateDebut.getText();
    }

    public String getDateFin() {
        return dateFin.getText();
    }

    public String getDescription() {
        return ((Label) ((VBox) descriptionContainer.getChildren().get(1)).getChildren().get(0)).getText();
    }

    public String getNoteLabel() {
        return labelNote;
    }

    public VBox getNoteContainer() {
        return noteContainer;
    }

   

 



}
