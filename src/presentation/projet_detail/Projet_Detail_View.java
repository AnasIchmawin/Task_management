package presentation.projet_detail;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Projet_Detail_View extends Application {
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private BorderPane root;
    private Label descriptionLabel;
    private ProjetDetailController controller ;

    // Constructor
    public Projet_Detail_View(ProjetDetailController controller) {
        this.controller = controller ; 
        init();
        style();
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
        primaryStage.setTitle("Tache details");
        primaryStage.show();
    }

    private BorderPane createBorderPane(VBox navbarContainer, StackPane container) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(navbarContainer, new Insets(0, 10, 0, 10));
        root.setTop(navbarContainer);
        BorderPane.setMargin(container, new Insets(10, 10, 10, 10));
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
        container.setPadding(new Insets(20, 20, 20, 25)); // 15px padding top, 70px padding right,
        // 20px padding bottom, 55px padding left

        // Créer un conteneur VBox pour contenir les éléments principaux
        HBox HeadBox = BoxHead("la tache de la famille des taches", "12/21/2021", "13/12/2023", "Encadrement", "PFE");
        HBox Descr_Seances = Descr_Seances();
        HBox Taches_doc = Taches_doc();
        HBox Buttons = Buttons();

        // Création du VBox
        VBox vbox = new VBox(30); // Espacement vertical entre les HBox
        vbox.getChildren().addAll(HeadBox, Descr_Seances,Taches_doc, Buttons);
        container.getChildren().add(vbox);
        return container;
    }

    private HBox Descr_Seances() {
        HBox hbox = new HBox(20);
        VBox vbox1 = BoxDescription("on cont  la bureautiquela bureautique informatique, sans que son cont  la bureautique informatique, sans que son cont  la bureautiquela bureautique informatique, sans que son cont  la bureautique informatique, sans que son cont  la bureautiquela bureautique");
        VBox Seances = createSeanceBox();
        hbox.getChildren().addAll(vbox1, Seances);
        return hbox;
    }

    private VBox createSeanceBox() {

        VBox contenaireSeances = new VBox();
        contenaireSeances.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        contenaireSeances.setSpacing(5);
        contenaireSeances.setAlignment(Pos.TOP_CENTER);

        GridPane zoneDesSeances = creatZoneDocs();
        ScrollPane scrollTache = createScrollPane(zoneDesSeances);
        scrollTache.getStyleClass().add("Docs-Style");

        Button ajouterSeanceButton = new Button();
        ajouterSeanceButton.getStyleClass().add("AjouterSeance-Style");

        // Créer une icône pour le bouton
        ImageView icon = new ImageView(new Image("file:./Pictures/addIcon.png"));
        icon.setFitWidth(20); // Ajuster la largeur de l'icône selon vos besoins
        icon.setFitHeight(20); // Ajuster la hauteur de l'icône selon vos besoins

        // Créer le texte pour le bouton
        Text buttonText = new Text("Ajouter Seance");
        buttonText.setFill(Color.WHITE);

        // Mettre l'icône et le texte dans une HBox
        HBox buttonContent = new HBox(buttonText, icon);
        buttonContent.setAlignment(Pos.CENTER); // Aligner le contenu à gauche
        buttonContent.setSpacing(30);

        // Ajouter la HBox au bouton
        ajouterSeanceButton.setGraphic(buttonContent);
        HBox.setHgrow(ajouterSeanceButton, Priority.ALWAYS);

        contenaireSeances.setPadding(new Insets(8, 8, 8, 8));

        // Action for AjouterButton
        ajouterSeanceButton.setOnAction(event -> {
            controller.handleAjouterButtonAction(zoneDesSeances);
        });

        contenaireSeances.getChildren().addAll(scrollTache, ajouterSeanceButton);

        return contenaireSeances;
    } 

    private GridPane creatZoneDocs() {
        GridPane grid = new GridPane();
        // grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        return grid;
    }




    private HBox Taches_doc() {
        HBox hbox = new HBox(20);
        VBox vbox1 = BoxDescription("la bureautique informatique, sans que son cont  la bureautiquela bureautique informatique, sans que son cont  la bureautique informatique, sans que son cont  la bureautiquela bureautique informatique, sans que son cont  la bureautique informatique, sans que son cont  la bureautiquela bureautique informatique, sans que son cont  la bureautique informatique");
        VBox DocSection = DocumentSection();
        hbox.getChildren().addAll(vbox1, DocSection);
        return hbox;
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

    private HBox Buttons() {
        HBox hbox = new HBox(20);
        Button addDocButton = new Button("Ajouter un document");
        Button addTacheButton = new Button("Ajouter une tache");
        Button addSeanceButton = new Button("Ajouter une seance");
        addDocButton.getStyleClass().add("ajout-style");
        addTacheButton.getStyleClass().add("ajout-style");
        addSeanceButton.getStyleClass().add("ajout-style");
        hbox.getChildren().addAll(addDocButton, addTacheButton, addSeanceButton);
        return hbox;
    }

    private VBox DocumentSection() {
        VBox DocSection = new VBox(20);
        HBox hboxContainer = new HBox(); // Utiliser HBox pour disposer les VBox horizontalement
        ScrollPane Documentsplat = createScrollPaneWithButton(hboxContainer); // Créer le ScrollPane avec le HBox
        Documentsplat.getStyleClass().add("scroll-pane-style");
        // container
        // definir la hauteur de la scrollPane
        Documentsplat.setPrefHeight(220);
        hboxContainer.setSpacing(10); // Espacement horizontal entre les VBox

        Button addDocButton = new Button("Ajouter un document");
        addDocButton.getStyleClass().add("ajout-style");

        // Gestionnaire d'événements pour le bouton
        addDocButton.setOnAction(event -> {
            VBox newVBox = new VBox(20);
            newVBox.setPrefSize(170, 200);
            newVBox.setStyle("-fx-border-color: #bdbdbd; -fx-border-width: 3px; -fx-border-radius: 15px; ");

            // Ajouter un Label de texte à la nouvelle VBox
            Button Systeme = new Button("Systeme d'exploitation 12.12.2023");
            // wrap text
            Systeme.setWrapText(true);
            Systeme.setAlignment(Pos.CENTER);

            Systeme.setOnAction(e -> {
                // Chemin vers le fichier PDF
                String cheminPDF = "C:\\Users\\imani\\OneDrive - Université Sultan Moulay Slimane\\Bureau\\ppt\\presentation\\tache_detail\\systeme.pdf";

                // Vérifier si Desktop est pris en charge par la plateforme
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(new File(cheminPDF));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // Si Desktop n'est pas pris en charge, afficher un message d'erreur
                    System.out.println("Desktop n'est pas pris en charge");
                }
            });

            newVBox.getChildren().add(Systeme);
            Label description = new Label(
                    "la bureautique informatique, sans que son cont  la bureautique informatique, sans que son cont  la bureautiquela bureautique informatiqu");
            newVBox.getChildren().add(description);

            newVBox.setPadding(new Insets(3, 5, 5, 5));
            description.setWrapText(true);
            // text aligne center
            description.setAlignment(Pos.CENTER);
            description.setPadding(new Insets(0, 0, 0, 10));
            // Ajouter des marges entre les VBox
            VBox.setMargin(newVBox, new Insets(0, 10, 0, 0)); // Marge droite de 10 pixels

            // Ajouter la nouvelle VBox à droite de l'ancienne dans le HBox container
            hboxContainer.getChildren().add(newVBox);
            Systeme.setStyle(
                    "-fx-min-height: 50px;-fx-font-size: 14px; -fx-max-height: 50px;-fx-min-width: 200px; -fx-max-width: 200px;-fx-font-weight: bold;-fx-background-color: #bdbdbd; -fx-background-radius: 10px;");
        });

        // Ajouter le ScrollPane et le bouton à la VBox principale
        DocSection.getChildren().addAll(Documentsplat, addDocButton);

        return DocSection;
    }

    private ScrollPane createScrollPaneWithButton(HBox hboxContainer) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hboxContainer); // Définir le contenu du ScrollPane

        // Vous pouvez également définir d'autres propriétés de ScrollPane ici selon vos
        // besoins

        return scrollPane;
    }

    private VBox BoxDescription(String description) {
        Label indexdescription = new Label("Description: ");
        indexdescription.getStyleClass().add("index-style");
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.getStyleClass().add("description-style");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(indexdescription, descriptionLabel);
        return vbox;
    }

    private HBox BoxHead(String title, String dateDebut, String dateFin, String categorie, String type) {

        // Titre
        Label titleabel = new Label(title);

        // labels
        Label dateDebutLabel = new Label(dateDebut);
        Label dateFinLabel = new Label(dateFin);
        Label categorieLabel = new Label(categorie);
        Label typeLabel = new Label(type);
        // Style
        titleabel.getStyleClass().add("index-style");
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
        HBox hbox2 = new HBox(30);

        // Create HBox for labels
        VBox vbox1 = new VBox(5);
        VBox vbox2 = new VBox(5);
        VBox vbox3 = new VBox(5);
        VBox vbox4 = new VBox(5);

        // centering
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox1.setPadding(new Insets(0, 0, 0, 30));
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        hbox2.setPadding(new Insets(0, 30, 0, 0));
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        vbox3.setAlignment(Pos.CENTER);
        vbox4.setAlignment(Pos.CENTER);

        // Add labels to VBoxes
        vbox1.getChildren().addAll(indexDebut, dateDebutLabel);
        vbox2.getChildren().addAll(indexFin, dateFinLabel);
        vbox3.getChildren().addAll(indexCategorie, categorieLabel);
        vbox4.getChildren().addAll(indexType, typeLabel);
        hbox1.getChildren().add(titleabel);

        HBox.setHgrow(hbox1, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(hbox2, javafx.scene.layout.Priority.ALWAYS);

        hboxHead.getChildren().addAll(hbox1, hbox2);

        // Add VBoxes to HBox
        hbox2.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
        return hboxHead;
    }

    public void init() {
        leftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        descriptionLabel = new Label();
    }

    private void style() {
        leftButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        descriptionLabel.getStyleClass().add("description-label");
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
}