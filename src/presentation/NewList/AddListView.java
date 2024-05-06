package presentation.NewList;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Stage;
import presentation.listes.ListeFormController;

public class AddListView {

    private static final Pos TOP_CENTER = Pos.TOP_CENTER;
    private static final Pos TOP_LEFT = Pos.TOP_LEFT;
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private Button Enregistrer;
    private Button ajouterTacheButton;
    private Button Annuler;
    private AddListController controleur;
    private BorderPane root;
    private TextField TitreField;
    private TextArea ZoneDescription;
    private GridPane ZoneTaches;
    private AddListModel addListModel;
    private ListeFormController listeFormController;

    public AddListView(ListeFormController listeFormController) {
        this.listeFormController = listeFormController;
        addListModel = new AddListModel("", "", new ArrayList<>());
        init();
        style();
        action();
    }

    public AddListView(AddListModel addmodel, ListeFormController listeFormController) {
        this.listeFormController = listeFormController;
        this.addListModel = new AddListModel(addmodel.getTitre(), addmodel.getDiscription(),
                addmodel.getTitreSelectionnes());

        init();
        style();
        action();
        this.controleur.afficherTaches(this.addListModel.getTitreSelectionnes(), this.ZoneTaches);
    }

    public void start(Stage primaryStage) {
        StackPane containerContent = createMainContent();
        root = createBorderPane(containerContent);

        Scene scene = new Scene(root, 550, 520);
        scene.getStylesheets().add(getClass().getResource("AddListStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Formulaire d'une liste");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void init() {
        leftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        ajouterTacheButton = createButtonWithIcon("Ajouter Tache", "file:./Pictures/add.png", 20, 20);
        Annuler = createButtonWithIcon("Annuler", "file:./Pictures/annuler.png", 20, 20);
        Enregistrer = createButtonWithIcon("Enregistrer", "file:./Pictures/save.png", 13, 13);
        TitreField = createTextField("");
        TitreField.setText(this.addListModel.getTitre());
        ZoneDescription = createTextArea("", "ZoneDescription-Style");
        ZoneDescription.setText(this.addListModel.getDiscription());
        ajouterTacheButton = createButtonWithIcon("Ajouter Tache", "file:./Pictures/addIcon.png", 20, 20);
        ZoneTaches = creatZoneTaches();
        this.controleur = new AddListController();

    }

    private void style() {
        leftButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        ajouterTacheButton.getStyleClass().add("AjouterTache-style");
        Annuler.getStyleClass().add("footBtn-style");
        Enregistrer.getStyleClass().add("footBtn-style");
    }

    private BorderPane createBorderPane(StackPane container) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");
        root.setCenter(container);
        return root;
    }

    private StackPane createMainContent() {
        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        container.setPadding(new Insets(15, 70, 20, 55));

        VBox mainContentContainer = CreateVbox(20, TOP_CENTER);

        VBox topContainer = CreateVbox(10, TOP_LEFT);

        Label labelTitre = createLabel("Titre de ma liste");

        HBox ContainerTitle = CreateHbox(0, TOP_LEFT);

        TitreField.setPadding(new Insets(4, 4, 4, 12));

        ContainerTitle.getChildren().addAll(TitreField);

        // Add elements to TopContainer
        topContainer.getChildren().addAll(labelTitre, ContainerTitle);

        mainContentContainer.getChildren().addAll(topContainer); // Add top container and

        // ------------------------------

        Label labelDescription = createLabel("Discription");
        VBox ContenaireDescription = CreateVbox(10, TOP_LEFT);

        ContenaireDescription.getChildren().addAll(labelDescription, ZoneDescription);

        mainContentContainer.getChildren().addAll(ContenaireDescription);

        // create vbox Contenaire Taches
        VBox CentenaireTaches = CreateVbox(15, TOP_LEFT);
        Label labelTachces = createLabel("Taches Ajoutés");
        VBox Taches = createTacheBox();

        CentenaireTaches.getChildren().addAll(labelTachces, Taches);

        mainContentContainer.getChildren().addAll(CentenaireTaches);

        // ---------------------------------------------------------------

        // Centenaire Buttons

        HBox ContenaireButtons = CreateHbox(8, TOP_CENTER);
        ContenaireButtons.getChildren().addAll(Enregistrer, Annuler);

        mainContentContainer.getChildren().addAll(ContenaireButtons);

        container.getChildren().addAll(mainContentContainer);

        return container;
    }

    private GridPane creatZoneTaches() {
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.getStyleClass().addAll("Zone-taches");
        return grid;
    }

    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar
        return scrollPane;
    }

    private Button createButtonWithIcon(String name, String path, int i, int j) {
        Button button = new Button(name);
        try {
            Image icon = new Image(path);
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

        // Créer la VBox contenant les documents
        VBox contenaireTaches = CreateVbox(5, Pos.TOP_LEFT);
        contenaireTaches.getStyleClass().add("contenaire-taches");

        // Ajouter les éléments à la VBox
        contenaireTaches.getChildren().addAll(scrollTask, ajouterTacheButton);
        contenaireTaches.setPadding(new Insets(10, 10, 5, 10));

        return contenaireTaches;
    }

    // actions :
    public void action() {
        Annuler.setOnAction(event -> {
            try {
                Stage stage = (Stage) Annuler.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                System.out.println("Erreur pendant la fermeture AddList  : " + e.getMessage());
            }
        });

        Enregistrer.setOnAction(event -> {
            try {

                addListModel.setTitre(TitreField.getText());
                addListModel.setDiscription(ZoneDescription.getText());
                ArrayList<String> Taches = new ArrayList<>();

                // Accéder aux boutons dans le GridPane
                for (Node node : ZoneTaches.getChildren()) {
                    if (node instanceof Button) {
                        Button button = (Button) node;
                        String tachesAssocie = button.getText();
                        Taches.add(tachesAssocie);
                    }
                }

                this.controleur.saveInfosListe(addListModel , event);
                this.listeFormController.afficheListes();


            } catch (Exception e) {
                System.out.println("Erreur pendant la fermeture du addlist : " + e.getMessage());
            }
        });
        ajouterTacheButton.setOnAction(event -> {
            addListModel.setTitre(TitreField.getText());
            addListModel.setDiscription(ZoneDescription.getText());

            this.controleur.getTasksView(event, addListModel, this.listeFormController);
        });

    }
}
