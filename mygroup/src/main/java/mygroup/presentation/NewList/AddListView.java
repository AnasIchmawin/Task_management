package mygroup.presentation.NewList;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mygroup.presentation.listes.ListeFormController;

public class AddListView {
    private BorderPane root;
    private Button Enregistrer;
    private Button Annuler;
    private TextField TitreField;
    private TextArea ZoneDescription;
    private GridPane ZoneTaches;
    private AddListController addListController;
    private Button ajouterTacheButton;
    private StackPane containerContent;
    @SuppressWarnings("unused")
    private ListeFormController listeFormController;

    public AddListView(ListeFormController listeFormController) {
        init();
        style();
        action();
        this.addListController = new AddListController(this, listeFormController);
        this.listeFormController = listeFormController;
    }

    public AddListView(AddListController addListController) {
        init();
        style();
        action();
        this.addListController = new AddListController(this, addListController);
    }

    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 550, 520);
        scene.getStylesheets().add(getClass().getResource("AddListStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Formulaire d'une liste");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void init() {
        Enregistrer = createButton("Enregistrer", "file:./mygroup/src/main/java/Pictures/save.png",
                13, 13);
        Annuler = createButton("Annuler", "file:./mygroup/src/main/java/Pictures/annuler.png",
                20, 20);
        TitreField = createTextField("");
        ZoneDescription = createTextArea("", "ZoneDescription-Style");
        ZoneTaches = creatZoneTaches();
        ajouterTacheButton = createButton("Ajouter Tache", "file:./mygroup/src/main/java/Pictures/addIcon.png",
                20, 20);
        containerContent = createMainContent();
        root = createBorderPane(containerContent);
    }

    private void style() {
        Enregistrer.getStyleClass().add("footBtn-style");
        Annuler.getStyleClass().add("footBtn-style");
        ajouterTacheButton.getStyleClass().add("AjouterTache-style");
    }

    private BorderPane createBorderPane(StackPane container) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-style");
        root.setCenter(container);
        return root;
    }

    private StackPane createMainContent() {
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        container.setPadding(new Insets(15, 70, 20, 55));

        VBox mainContentContainer = createMainContentContainer();

        container.getChildren().addAll(mainContentContainer);

        return container;
    }

    private VBox createMainContentContainer() {
        VBox mainContentContainer = CreateVbox(20, Pos.TOP_CENTER);

        VBox topContainer = createTopContainer();
        VBox descriptionContainer = createDescriptionContainer();
        VBox tasksContainer = createTasksContainer();
        HBox buttonsContainer = createButtonsContainer();

        mainContentContainer.getChildren().addAll(topContainer, descriptionContainer,
                tasksContainer, buttonsContainer);

        return mainContentContainer;
    }

    private VBox createTopContainer() {
        VBox topContainer = CreateVbox(10, Pos.TOP_LEFT);
        Label labelTitre = createLabel("Titre de ma liste");
        HBox containerTitle = CreateHbox(0, Pos.TOP_LEFT);
        TitreField.setPadding(new Insets(4, 4, 4, 12));
        containerTitle.getChildren().addAll(TitreField);
        topContainer.getChildren().addAll(labelTitre, containerTitle);
        return topContainer;
    }

    private VBox createDescriptionContainer() {
        VBox descriptionContainer = CreateVbox(10, Pos.TOP_LEFT);
        Label labelDescription = createLabel("Discription");
        VBox containerDescription = CreateVbox(10, Pos.TOP_LEFT);
        containerDescription.getChildren().addAll(labelDescription, ZoneDescription);
        descriptionContainer.getChildren().addAll(containerDescription);
        return descriptionContainer;
    }

    private VBox createTasksContainer() {
        VBox tasksContainer = CreateVbox(15, Pos.TOP_LEFT);
        Label labelTaches = createLabel("Taches Ajoutées");
        VBox taches = createTacheBox();
        tasksContainer.getChildren().addAll(labelTaches, taches);
        return tasksContainer;
    }

    private HBox createButtonsContainer() {
        HBox buttonsContainer = CreateHbox(8, Pos.TOP_CENTER);
        buttonsContainer.getChildren().addAll(Enregistrer, Annuler);
        return buttonsContainer;
    }

    private GridPane creatZoneTaches() {
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.getStyleClass().addAll("Zone-taches");
        return grid;
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

    private TextArea createTextArea(String promptText, String style) {
        TextArea Zone = new TextArea();
        Zone.setWrapText(true);
        Zone.setPromptText(promptText);
        Zone.getStyleClass().add(style);
        return Zone;
    }

    private TextField createTextField(String prompt) {
        TextField text = new TextField();
        text.getStyleClass().add("ZoneTitre-Style");
        text.setPromptText(prompt);
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

    private VBox createTacheBox() {
        ScrollPane scrollTask = createScrollPane(ZoneTaches);
        scrollTask.getStyleClass().add("scroll-Style");
        VBox contenaireTaches = CreateVbox(5, Pos.TOP_LEFT);
        contenaireTaches.getStyleClass().add("contenaire-taches");
        contenaireTaches.getChildren().addAll(scrollTask, ajouterTacheButton);
        contenaireTaches.setPadding(new Insets(10, 10, 5, 10));
        return contenaireTaches;
    }

    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    public String getTitre() {
        return TitreField.getText();
    }

    public void setTitre(String newTitle) {
        TitreField.setText(newTitle);
    }

    public String getDescription() {
        return ZoneDescription.getText();
    }

    public void setDescription(String newDescription) {
        ZoneDescription.setText(newDescription);
    }

    public GridPane getZoneTaches() {
        return ZoneTaches;
    }

    public void setZoneTaches(GridPane newZoneTaches) {
        ZoneTaches = newZoneTaches;
    }

    // get root
    public BorderPane getRoot() {
        return root;
    }

    private void action() {
        Annuler.setOnAction(event -> {
            try {
                this.addListController.closerWindow(event);
            } catch (Exception e) {
                System.out.println("Erreur pendant la fermeture AddList  : " + e.getMessage());
            }
        });

        Enregistrer.setOnAction(event -> {
            try {
                this.addListController.saveInfosListe(event);
            } catch (Exception e) {
                System.out.println("Erreur pendant la fermeture du addlist : " + e.getMessage());
            }
        });

        ajouterTacheButton.setOnAction(event -> {
            this.addListController.getTasksView(event);
        });
    }

    // getStage
    public Stage getStage(){
        return (Stage) root.getScene().getWindow();
      }
}
