package mygroup.presentation.projet_detail;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mygroup.presentation.NewProjet.AddProjetController;
import mygroup.presentation.projets.ProjetsFormController;
import mygroup.presentation.taches.TachesFormView;


public class ProjetDetailView extends Application {

    private static final Pos RIGHT = Pos.BOTTOM_RIGHT;
    private static final Pos BOTTOM = Pos.BOTTOM_CENTER;

    @SuppressWarnings("unused")
    private Button addDocButton;
    private Button ajouterTacheButton;
    private Button ajouterSeanceButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private BorderPane root;
    private GridPane ZoneListes;
    private GridPane ZoneTaches;
    private Button ajouterDocButton;
    private Label descriptionLabel;
    private String Title;
    private GridPane ZoneDocuments;
    private VBox ContainerGoogleCalendar;
    private VBox description;
    private Button confirmerButton;
    private ProjetDetailController controller ;
    private ScrollPane scrollPane;
    private String dateDebut;
    private String dateFin;
    private String categorie;
    private String type;
    private TachesFormView tacheView;
    private AddProjetController addProjetController;
    private GridPane ZoneSeances;
    private VBox contenaireSeances;


    public ProjetDetailView() {
        init();
        style();
        action();    
        // this.controller = new ProjetDetailController(this);
    }

    public ProjetDetailView(ProjetsFormController projetsFormController) {
        init();
        style();
        action();    
        this.controller = new ProjetDetailController(this , projetsFormController);
    }
    
    @Override
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
        scene.getStylesheets().add(getClass().getResource("TacheStyle.css").toExternalForm());
        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Projet details");
        primaryStage.show();
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
        HBox navbar = new HBox(30, buttonsBar);
        navbar.setPadding(new Insets(10, 20, 10, 80)); // 20px padding left and right, 10px padding top and bottom
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    private StackPane createMainContent() {
        // le background de la page gris
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        // 20px padding bottom, 55px padding left

        // Créer un conteneur VBox pour contenir les éléments principaux
        HBox HeadBox = BoxHead(Title,dateDebut,dateFin, categorie,type);
        HBox Descr_Seances = Descr_Seances();
        Descr_Seances.setPadding(new Insets(0, 20, 0, 20));
        HBox Taches_doc = Taches_doc();
        Taches_doc.setPadding(new Insets(0, 20, 20, 20));

        // Création du VBox
        VBox vbox = new VBox(30); // Espacement vertical entre les HBox
        vbox.getChildren().addAll(HeadBox, Descr_Seances,Taches_doc);
        container.getChildren().add(vbox);
        return container;
    }

    private HBox Descr_Seances() {
        HBox hbox = new HBox();
        HBox descriptionContainer = createDescriptionContainer();
        VBox Seances = createSeancesBox();
        hbox.getChildren().addAll(descriptionContainer, Seances);
        return hbox;
    }

    private VBox createSeancesBox() {
        VBox contenaire = new VBox();
        contenaire.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");

        contenaireSeances = new VBox(30);    
        contenaireSeances.setSpacing(20);

        VBox contenaireButton = new VBox();

        ScrollPane scrollSeance = createScrollPane(ZoneSeances);
        scrollSeance.getStyleClass().add("Docs-Style");

        HBox.setHgrow(ajouterSeanceButton, Priority.ALWAYS);

        contenaireButton.getChildren().add(ajouterSeanceButton);
        contenaireButton.setAlignment(Pos.CENTER);

        contenaireSeances.setPadding(new Insets(8, 8, 8, 8));

        // Action for AjouterButton
        ajouterSeanceButton.setOnAction(event -> {
            controller.handleAjouterSeanceButton(event);
        });

        contenaireSeances.getChildren().addAll(scrollSeance);
        contenaireSeances.setPrefHeight(120);

        contenaire.setMinWidth(300);
        contenaire.setMinHeight(160);

        contenaire.getChildren().addAll(contenaireSeances, contenaireButton);

        return contenaire;
    } 

    private GridPane creatZoneDocs() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(1);
        grid.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        return grid;
    }




    private HBox Taches_doc() {
        VBox Zonevertical = CreateVbox(0, RIGHT);
        VBox ZoneDocuments = createDocumentsSection();
        ScrollPane scrollPane = createScrollPane(ZoneTaches);
        VBox tasks = createTasksContainer(scrollPane);
        
        Zonevertical.getChildren().addAll(ZoneDocuments);
        HBox hbox = new HBox(200);
        hbox.getChildren().addAll(tasks, Zonevertical);
        return hbox;
    }
    

    private VBox createDocumentsSection() {
        ZoneDocuments = creatZoneDocs();
        ScrollPane scrollDocs = createScrollPane(ZoneDocuments);
        scrollDocs.getStyleClass().add("Docs-Style");

        VBox contenaireDocuments = CreateVbox(5, Pos.CENTER);
        contenaireDocuments.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        ajouterDocButton.setAlignment(BOTTOM);
        contenaireDocuments.getChildren().addAll(scrollDocs, ajouterDocButton);
        contenaireDocuments.setSpacing(20);
        contenaireDocuments.setMinWidth(300);
        contenaireDocuments.setMinHeight(160);
        return contenaireDocuments;
    }

    private VBox CreateVbox(int Spacing, Pos position) {
        VBox vbox = new VBox();
        vbox.setSpacing(Spacing);
        vbox.setAlignment(position);
        return vbox;
    }

    private VBox createTasksContainer(ScrollPane scrollPane) {
        VBox tasks = new VBox();
        tasks.getChildren().addAll(scrollPane, ajouterTacheButton); // Add the ajouterTacheButton to the tasks VBox
    
        // Set the same styles as the contenaire VBox
        tasks.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        tasks.setPadding(new Insets(8, 8, 8, 8));
        tasks.setSpacing(20);
        tasks.setMinWidth(580);
        tasks.setMinHeight(160);
    
        // Set alignment and margin
        tasks.setAlignment(Pos.CENTER);
        VBox.setMargin(tasks, new Insets(30, 0, 0, 0));
    
        return tasks;
    }
    

    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide vertical scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar
        return scrollPane;
    }
    

    private HBox createDescriptionContainer() {
        HBox descriptionContainer = new HBox();
        ScrollPane descriptionScrollPane = createDescriptionScrollPane(description);
        descriptionContainer.getChildren().addAll( descriptionScrollPane);
        descriptionContainer.setAlignment(Pos.TOP_RIGHT);
        return descriptionContainer;
    }

    private ScrollPane createDescriptionScrollPane(VBox description) {
        ScrollPane descriptionScrollPane = new ScrollPane(description);
        descriptionScrollPane.setFitToWidth(true);
        descriptionScrollPane.setStyle("-fx-background-color: transparent;");
        descriptionScrollPane.setPadding(new Insets(0, 40, 0, 10));
        descriptionScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return descriptionScrollPane;
    }
    private VBox BoxDescription(String description) {
        TextArea descriptionLabel = new TextArea(description);
        descriptionLabel.getStyleClass().add("description-style");
        descriptionLabel.setWrapText(true);
        VBox vbox = new VBox();
        vbox.setPrefHeight(340);
        vbox.setPrefWidth(1000);
        vbox.getChildren().addAll(descriptionLabel);
        return vbox;
    }

    private HBox BoxHead(String title, String dateDebut, String dateFin, String categorie, String type) {

        // Titre
        Label titre = new Label(Title);
        settitle("titre de mon projet");
        titre.setPadding(new Insets(0, 0, 0, 10));

        // labels
        Label dateDebutLabel = new Label(dateDebut);
        setDateDebut("titre de mon projet");
        Label dateFinLabel = new Label(dateFin);
        setDateFin("titre de mon projet");
        Label categorieLabel = new Label(categorie);
        setCategorie("titre de mon projet");
        Label typeLabel = new Label(type);
        setType("titre de mon projet");
        
        // Style


        titre.getStyleClass().add("index1-style");
        dateDebutLabel.getStyleClass().add("title-style");
        dateFinLabel.getStyleClass().add("title-style");
        categorieLabel.getStyleClass().add("title-style");
        typeLabel.getStyleClass().add("title-style");

        // Titre labels
        Label indexDebut = new Label("Debut");
        Label indexFin = new Label("Fin");
        Label indexCategorie = new Label("Categorie");
        Label indexType = new Label("Type");

        // Style
        indexDebut.getStyleClass().add("index-style");
        indexFin.getStyleClass().add("index-style");
        indexCategorie.getStyleClass().add("index-style");
        indexType.getStyleClass().add("index-style");

        // Create Head Box
        HBox hboxHead = new HBox(10);

        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(20);

        // Create HBox for labels
        VBox vbox1 = new VBox(5);
        VBox vbox2 = new VBox(5);
        VBox vbox3 = new VBox(5);
        VBox vbox4 = new VBox(5);

        // centering
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox1.setPadding(new Insets(0, 0, 0, 0));
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        hbox2.setPadding(new Insets(0, 8, 0, 0));
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        vbox3.setAlignment(Pos.CENTER);
        vbox4.setAlignment(Pos.CENTER);

        // Add labels to VBoxes
        vbox1.getChildren().addAll(indexDebut, dateDebutLabel);
        vbox2.getChildren().addAll(indexFin, dateFinLabel);
        vbox3.getChildren().addAll(indexCategorie, categorieLabel);
        vbox4.getChildren().addAll(indexType, typeLabel);
        hbox1.getChildren().addAll(titre);

        HBox.setHgrow(hbox1, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(hbox2, javafx.scene.layout.Priority.ALWAYS);

        hboxHead.getChildren().addAll(hbox1, hbox2);

        // Add VBoxes to HBox
        hbox2.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
        return hboxHead;
    }

    public void init() {
        ajouterDocButton = createButtonWithIcon("Ajouter Document", "file:./mygroup/src/main/java/Pictures/add.png", 20, 20);
        ajouterSeanceButton = createButtonWithIcon("Ajouter une Seance", "file:./mygroup/src/main/java/Pictures/add.png", 20, 20);
        ajouterTacheButton = createButtonWithIcon("Ajouter une Tache", "file:./mygroup/src/main/java/Pictures/add.png", 20, 20);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        ZoneListes = createGridPane();
        ZoneTaches = createGridPane();
        ZoneSeances = creatZoneDocs();
        scrollPane = createScrollPane(ZoneListes);
        descriptionLabel = new Label();
        ContainerGoogleCalendar = new VBox();
        ContainerGoogleCalendar.setPadding(new Insets(2, 2, 2, 2));
        ContainerGoogleCalendar.setSpacing(5);
        confirmerButton =  createButtonWithIcon("", "file:./mygroup/src/main/java/Pictures/confirmer.png", 29, 29);
        description = new VBox();
        
    }

    private void style() {
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-clicked-style");
        archiveButton.getStyleClass().add("button-style");
        descriptionLabel.getStyleClass().add("description-label");
        ajouterDocButton.getStyleClass().add("ajout-style");
        ajouterSeanceButton.getStyleClass().add("ajout-style");
        ajouterTacheButton.getStyleClass().add("ajout-style");
        scrollPane.getStyleClass().add("scroll-pane");
        ContainerGoogleCalendar.getStyleClass().add("google-calendar-style");
        confirmerButton.getStyleClass().add("confirm-btn-style");
    }

    private void action() {
        ajouterDocButton.setOnAction(event -> {
            this.controller.handleAjouterDocButtonAction();
        });
        // confirmerButton.setOnAction(event -> {
        //     controller.handleConfirmerButtonAction();
        // });
        listesButton.setOnAction(event -> {
            this.controller.handleListesButtonAction();
        });

        archiveButton.setOnAction(event -> {
            this.controller.handleArchiveButtonAction();
        });

        projectsButton.setOnAction(event -> {
            this.controller.handleProjectsButtonAction();
        });
        ajouterTacheButton.setOnAction(event -> {
            controller.getTasksView(event);
        });
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(30);
        gridPane.setHgap(20);
        gridPane.setPrefHeight(350);
        gridPane.setStyle("-fx-background-color: #8E9EB2;");
        return gridPane;
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
    // Getters

    public GridPane getZoneTaches() {
        return ZoneTaches;
    }

public Button getAddDocButton() {
    return addDocButton;
}

public Button getAjouterTacheButton() {
    return ajouterTacheButton;
}

public Button getAjouterSeanceButton() {
    return ajouterSeanceButton;
}

public Button getListesButton() {
    return listesButton;
}

public Button getProjectsButton() {
    return projectsButton;
}

public Button getArchiveButton() {
    return archiveButton;
}

public BorderPane getRoot() {
    return root;
}

public GridPane getZoneListes() {
    return ZoneListes;
}

public Button getAjouterDocButton() {
    return ajouterDocButton;
}

public Label getDescriptionLabel() {
    return descriptionLabel;
}

public String gettitle() {
    return Title;
}

public String getcategorie() {
    return categorie;
}

public String gettype() {
    return type;
}

public GridPane getZoneDocuments() {
    return ZoneDocuments;
}

public VBox getContainerGoogleCalendar() {
    return ContainerGoogleCalendar;
}

public String getDescription() {
    return description.getChildren().get(0).toString();
}

public Button getConfirmerButton() {
    return confirmerButton;
}

public ProjetDetailController getController() {
    return controller;
}

public ScrollPane getScrollPane() {
    return scrollPane;
}

public String getDateDebut() {
    return dateDebut;
}

public String getDateFin() {
    return dateFin;
}

public TachesFormView getTacheView() {
    return tacheView;
}

public AddProjetController getAddProjetController() {
    return addProjetController;
}

// Setters
public void setAddDocButton(Button addDocButton) {
    this.addDocButton = addDocButton;
}


public void setAjouterTacheButton(Button ajouterTacheButton) {
    this.ajouterTacheButton = ajouterTacheButton;
}

public void setAjouterSeanceButton(Button ajouterSeanceButton) {
    this.ajouterSeanceButton = ajouterSeanceButton;
}

public void setListesButton(Button listesButton) {
    this.listesButton = listesButton;
}

public void setProjectsButton(Button projectsButton) {
    this.projectsButton = projectsButton;
}

public void setArchiveButton(Button archiveButton) {
    this.archiveButton = archiveButton;
}

public void setRoot(BorderPane root) {
    this.root = root;
}

public void setZoneListes(GridPane zoneListes) {
    this.ZoneListes = zoneListes;
}

public void setAjouterDocButton(Button ajouterDocButton) {
    this.ajouterDocButton = ajouterDocButton;
}

public void setDescription(String description) {
    this.description.getChildren().clear();
    this.description.getChildren().add(BoxDescription(description));
}

public void setDateDebut(String dateDebut) {
    this.dateDebut = dateDebut;
}

public void setDateFin(String dateFin) {
    this.dateFin = dateFin;
}

public void setCategorie(String categorie) {
    this.categorie = categorie;
}

public void setType(String type) {
    this.type = type;
}

public void setZoneDocuments(GridPane zoneDocuments) {
    this.ZoneDocuments = zoneDocuments;
}

public void setContainerGoogleCalendar(VBox containerGoogleCalendar) {
    this.ContainerGoogleCalendar = containerGoogleCalendar;
}


public void setConfirmerButton(Button confirmerButton) {
    this.confirmerButton = confirmerButton;
}

public void setController(ProjetDetailController controller) {
    this.controller = controller;
}

public void setScrollPane(ScrollPane scrollPane) {
    this.scrollPane = scrollPane;
}

public void settitle(String Title) {
    this.Title = Title;
}



public void setTacheView(TachesFormView tacheView) {
    this.tacheView = tacheView;
}

public void setAddProjetController(AddProjetController addProjetController) {
    this.addProjetController = addProjetController;
}

public GridPane getZoneSeances() {
    return ZoneSeances;
}



}