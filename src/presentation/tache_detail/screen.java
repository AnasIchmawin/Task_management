package presentation.tache_detail;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class screen extends Application {
    private Button leftButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private BorderPane root;
    private Label descriptionLabel;
    private controleur controller;
    Button addDocButton;
    Label titleabel ;
    Label indexDebut;
    Label indexFin;
    Label indexCategorie;
    Label indexType ;
    Label dateDebutLabel;
    Label dateFinLabel;
    Label indexdescription;
    Label categorieLabel;
    Label typeLabel;
    public Label getTitleabel() {
        return titleabel;
    }

    public Label getDateDebutLabel() {
        return dateDebutLabel;
    }

    public Label getDateFinLabel() {
        return dateFinLabel;
    }

    public Label getCategorieLabel() {
        return categorieLabel;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    // Constructor
    public screen() {
        init();
        style();
        controller = new controleur(this, new tacheDetailModele());
    }

    public HBox Designe(){
        

        // Titre labels
        Label indexDebut = new Label("Debut");
        Label indexFin = new Label("Fin");
        Label indexCategorie = new Label("Categorie");
        Label indexType = new Label();

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

    @Override
    public void start(Stage primaryStage) {
        // Create the nnavigation bar
        VBox navbarContainer = createNavbarContainer();
        // Create the main content
        StackPane container = Designer();
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

    private StackPane Designer(){
        StackPane container = new StackPane();
        container.getStyleClass().add("container");
        container.setPadding(new Insets(20, 20, 20, 25)); 
        HBox HeadBox = new HBox();
        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(30);
         // Create HBox for labels
         VBox vbox1 = new VBox(5);
         VBox vbox2 = new VBox(5);
         VBox vbox3 = new VBox(5);
         VBox vbox4 = new VBox(5);

        //centring
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox1.setPadding(new Insets(0, 0, 0, 30));
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        hbox2.setPadding(new Insets(0, 30, 0, 0));
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        vbox3.setAlignment(Pos.CENTER);
        vbox4.setAlignment(Pos.CENTER);


        vbox1.getChildren().addAll(indexDebut, dateDebutLabel);
        vbox2.getChildren().addAll(indexFin, dateFinLabel);
        vbox3.getChildren().addAll(indexCategorie, categorieLabel);
        vbox4.getChildren().addAll(indexType, typeLabel);
        hbox1.getChildren().add(titleabel);

        HBox.setHgrow(hbox1, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(hbox2, javafx.scene.layout.Priority.ALWAYS);

        HeadBox.getChildren().addAll(hbox1, hbox2);

        hbox2.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
        VBox DescriptionBox = new VBox();
        descriptionLabel.setWrapText(true);
        DescriptionBox.getChildren().addAll(indexdescription, descriptionLabel);
        VBox DocSection = DocumentSection();
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(HeadBox,DescriptionBox, DocSection);
        container.getChildren().add(vbox);

        return container;
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
                String cheminPDF = "C:\\Users\\hp\\Desktop\\projet java 2024\\Task_management\\src\\presentation\\tache_detail\\systeme.pdf";

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
        scrollPane.setContent(hboxContainer); 
        return scrollPane;
    }
//----------------------------------------------------------------------------------
    public void init() {
        leftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        descriptionLabel = new Label();
        titleabel = new Label();
        descriptionLabel = new Label();
        dateDebutLabel = new Label();
        dateFinLabel = new Label();
        categorieLabel = new Label();
        typeLabel = new Label();
        indexDebut = new Label("Debut");
        indexFin = new Label("Fin");
        indexCategorie = new Label("Categorie");
        indexType = new Label("Type");
        indexdescription = new Label("Description");
        descriptionLabel = new Label();
    }

    private void style() {
        leftButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        descriptionLabel.getStyleClass().add("description-label");
        titleabel.getStyleClass().add("index-style");
        dateDebutLabel.getStyleClass().add("title-style");
        dateFinLabel.getStyleClass().add("title-style");
        categorieLabel.getStyleClass().add("title-style");
        typeLabel.getStyleClass().add("title-style");
        indexDebut.getStyleClass().add("index-style");
        indexFin.getStyleClass().add("index-style");
        indexCategorie.getStyleClass().add("index-style");
        indexType.getStyleClass().add("index-style");
        indexdescription.getStyleClass().add("index-style");
        descriptionLabel.getStyleClass().add("description-style");
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