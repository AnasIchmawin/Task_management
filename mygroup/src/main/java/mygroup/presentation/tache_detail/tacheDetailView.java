package mygroup.presentation.tache_detail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.awt.Desktop;
// import java.awt.TextField; // Remove this line

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField; // Add this line
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mygroup.presentation.taches.TachesFormController;

public class tacheDetailView extends Application {
    private VBox vbox1;
    private VBox vbox2;
    private VBox vbox3;
    private VBox vbox4;
    private Button returnButton;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private BorderPane root;
    private tacheDetailController controller;
    private Button addDocButton;
    private Label indexdescription;
    private Label indexDebut;
    private Label indexFin;
    private Label indexCategorie;
    private Label dateFinLabel;
    private Label categorieLabel;
    private Label typeLabel;
    private Label dateDebutLabel;
    private TextField titleabel;
    private TextField descriptionLabel;
    private HBox HeadBox;
    private HBox hbox1;
    private HBox hbox2;
    private VBox DescriptionBox;
    private VBox DocSection;
    private VBox vbox;
    private StackPane container;
    private Button Update;
    private HBox hboxContainer;

    private StackPane Designer() {
        container = new StackPane();
        HBox.setHgrow(hbox1, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(hbox2, javafx.scene.layout.Priority.ALWAYS);
        container.getStyleClass().add("container");
        container.setPadding(new Insets(20, 20, 20, 25));        
        hbox1.setPadding(new Insets(0, 0, 0, 30));
        hbox2.setPadding(new Insets(0, 30, 0, 0));
        DocumentSection();
        return container;
    }

    private void DocumentSection() {
        DocSection = new VBox(20);
        hboxContainer = new HBox(); // Initialiser ici
        ScrollPane Documentsplat = createScrollPaneWithButton(hboxContainer);
        Documentsplat.getStyleClass().add("scroll-pane-style");
        Documentsplat.setPrefHeight(220);
        hboxContainer.setSpacing(10);

        addDocButton = new Button("Ajouter un document");
        addDocButton.getStyleClass().add("ajout-style");
        // Ajouter le bouton dans le gestionnaire d'événements
        addDocButton.setOnAction(event -> {
            controller.addDocButtonAction();
        });

        HBox h = new HBox(10);
        h.getChildren().addAll(addDocButton, Update);
        DocSection.getChildren().addAll(Documentsplat, h);
    }

    public void addDocumentToContainer(String title, String description, String url) {
        VBox newVBox = new VBox(20);
        newVBox.setPrefSize(170, 200);
        newVBox.setStyle("-fx-border-color: #bdbdbd; -fx-border-width: 3px; -fx-border-radius: 15px; ");

        Button docButton = new Button(title);
        docButton.setWrapText(true);
        docButton.setAlignment(Pos.CENTER);
        docButton.setOnAction(e -> openDocument(url));

        newVBox.getChildren().add(docButton);

        Label docDescription = new Label(description);
        docDescription.setWrapText(true);
        docDescription.setAlignment(Pos.CENTER);
        docDescription.setPadding(new Insets(0, 0, 0, 10));

        newVBox.getChildren().add(docDescription);
        VBox.setMargin(newVBox, new Insets(0, 10, 0, 0));

        hboxContainer.getChildren().add(newVBox);
        docButton.setStyle("-fx-min-height: 50px;-fx-font-size: 14px; -fx-max-height: 50px;-fx-min-width: 200px; -fx-max-width: 200px;-fx-font-weight: bold;-fx-background-color: #bdbdbd; -fx-background-radius: 10px;");
    }


    private void openDocument(String url) {
        // Ouvrir le document PDF
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(new File(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop n'est pas pris en charge");
        }
    }



    // Constructor
    public tacheDetailView(TachesFormController tachesFormController) {
        init();
        style();
        action();
        this.controller = new tacheDetailController(this, tachesFormController) ;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("TacheStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tache details");
        primaryStage.show();
    }

    public void init() {
        initLabels();
        initIndexes();
        initButton();
        initBoxes();
        VBox navbarContainer = createNavbarContainer();
        StackPane container = Designer();
        getChildren();
        Alignement();
        root = createBorderPane(navbarContainer, container);
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
        HBox returnButtonBox = new HBox(20, returnButton);
        HBox navbar = new HBox(30, returnButtonBox, buttonsBar);
        navbar.setPadding(new Insets(10, 20, 10, 20)); // 20px padding left and right, 10px padding top and bottom
        navbar.getStyleClass().add("navbar");
        VBox navbarContainer = new VBox(navbar);
        navbarContainer.getStyleClass().add("navbar-container");
        return navbarContainer;
    }

    // private StackPane Designer() {
    //     container = new StackPane();
    //     HBox.setHgrow(hbox1, javafx.scene.layout.Priority.ALWAYS);
    //     HBox.setHgrow(hbox2, javafx.scene.layout.Priority.ALWAYS);
    //     container.getStyleClass().add("container");
    //     container.setPadding(new Insets(20, 20, 20, 25));        
    //     hbox1.setPadding(new Insets(0, 0, 0, 30));
    //     hbox2.setPadding(new Insets(0, 30, 0, 0));
    //     DocumentSection();
    //     return container;
    // }

    public void getChildren() {
        vbox1.getChildren().addAll(indexDebut, dateDebutLabel);
        vbox2.getChildren().addAll(indexFin, dateFinLabel);
        vbox3.getChildren().addAll(indexCategorie, categorieLabel);
        hbox1.getChildren().add(titleabel);
        HeadBox.getChildren().addAll(hbox1, hbox2);
        hbox2.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
        DescriptionBox.getChildren().addAll(indexdescription);
        DescriptionBox.getChildren().add(descriptionLabel);
        vbox.getChildren().addAll(HeadBox, DescriptionBox, DocSection);
        container.getChildren().add(vbox);
        
    }

    public void Alignement() {
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        vbox3.setAlignment(Pos.CENTER);
        vbox4.setAlignment(Pos.CENTER);
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        Update.setAlignment(Pos.BOTTOM_RIGHT);
    }

    private ScrollPane createScrollPaneWithButton(HBox hboxContainer) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hboxContainer);
        return scrollPane;
    }


   

    public void initLabels() {
        descriptionLabel = new TextField();
        titleabel = new TextField();
        descriptionLabel = new TextField();
        dateDebutLabel = new Label();
        dateFinLabel = new Label();
        categorieLabel = new Label();
        typeLabel = new Label();
        descriptionLabel = new TextField();       
    }

    public void initIndexes(){
        indexDebut = new Label("Debut");
        indexFin = new Label("Fin");
        indexCategorie = new Label("Categorie");
        indexdescription = new Label("Description");
    }

    public void initButton(){
        returnButton = createButtonWithIcon("", "file:./mygroup/src/main/java/Pictures/left-arrow.png", 35, 35);
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        Update = new Button("Save Changes");
    }


    public void initBoxes(){
        hbox1 = new HBox(10);
        hbox2 = new HBox(30);
        vbox1 = new VBox(5);
        vbox2 = new VBox(5);
        vbox3 = new VBox(5);
        vbox4 = new VBox(5);
        vbox = new VBox(30);
        HeadBox = new HBox();
        DescriptionBox = new VBox();
    }


    private void style() {
        returnButton.getStyleClass().add("left-btn-style");
        listesButton.getStyleClass().add("button-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        dateDebutLabel.getStyleClass().add("title-style");
        dateFinLabel.getStyleClass().add("title-style");
        categorieLabel.getStyleClass().add("title-style");
        typeLabel.getStyleClass().add("title-style");
        indexDebut.getStyleClass().add("index-style");
        indexFin.getStyleClass().add("index-style");
        indexCategorie.getStyleClass().add("index-style");
        indexdescription.getStyleClass().add("index-style");
        descriptionLabel.getStyleClass().add("description-style");
        descriptionLabel.getStyleClass().add("description-label");
        titleabel.getStyleClass().add("index-style");
        Update.getStyleClass().add("ajout-style");
        // descriptionLabel.setWrapText(true);
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



    // private void DocumentSection() {
    //     DocSection = new VBox(20);
    //     HBox hboxContainer = new HBox(); // Utiliser HBox pour disposer les VBox horizontalement
    //     ScrollPane Documentsplat = createScrollPaneWithButton(hboxContainer); // Créer le ScrollPane avec le HBox
    //     Documentsplat.getStyleClass().add("scroll-pane-style");
    //     Documentsplat.setPrefHeight(220);
    //     hboxContainer.setSpacing(10); // Espacement horizontal entre les VBox

    //     addDocButton = new Button("Ajouter un document");
    //     addDocButton.getStyleClass().add("ajout-style");

    //     // Gestionnaire d'événements pour le bouton
    //     addDocButton.setOnAction(event -> {
    //         VBox newVBox = new VBox(20);
    //         newVBox.setPrefSize(170, 200);
    //         newVBox.setStyle("-fx-border-color: #bdbdbd; -fx-border-width: 3px; -fx-border-radius: 15px; ");

    //         // Ajouter un Label de texte à la nouvelle VBox
    //         Button Systeme = new Button("Systeme d'exploitation 12.12.2023");
    //         // wrap text
    //         Systeme.setWrapText(true);
    //         Systeme.setAlignment(Pos.CENTER);

    //         Systeme.setOnAction(e -> {
    //             // Chemin vers le fichier PDF
    //             String cheminPDF = "mygroup\\src\\main\\java\\mygroup\\presentation\\tache_detail\\systeme.pdf";

    //             // Vérifier si Desktop est pris en charge par la plateforme
    //             if (Desktop.isDesktopSupported()) {
    //                 Desktop desktop = Desktop.getDesktop();
    //                 try {
    //                     desktop.open(new File(cheminPDF));
    //                 } catch (IOException ex) {
    //                     ex.printStackTrace();
    //                 }
    //             } else {
    //                 // Si Desktop n'est pas pris en charge, afficher un message d'erreur
    //                 System.out.println("Desktop n'est pas pris en charge");
    //             }
    //         });

    //         newVBox.getChildren().add(Systeme);
    //         Label description = new Label(
    //                 "la bureautique informatique, sans que son cont  la bureautique informatique, sans que son cont  la bureautiquela bureautique informatiqu");
    //         newVBox.getChildren().add(description);

    //         newVBox.setPadding(new Insets(3, 5, 5, 5));
    //         description.setWrapText(true);
    //         // text aligne center
    //         description.setAlignment(Pos.CENTER);
    //         description.setPadding(new Insets(0, 0, 0, 10));
    //         // Ajouter des marges entre les VBox
    //         VBox.setMargin(newVBox, new Insets(0, 10, 0, 0)); // Marge droite de 10 pixels

    //         // Ajouter la nouvelle VBox à droite de l'ancienne dans le HBox container
    //         hboxContainer.getChildren().add(newVBox);
    //         Systeme.setStyle(
    //                 "-fx-min-height: 50px;-fx-font-size: 14px; -fx-max-height: 50px;-fx-min-width: 200px; -fx-max-width: 200px;-fx-font-weight: bold;-fx-background-color: #bdbdbd; -fx-background-radius: 10px;");
    //     });

    //     // Ajouter le ScrollPane et le bouton à la VBox principale
    //     HBox h = new HBox(10);
    //     h.getChildren().addAll(addDocButton, Update);
    //     DocSection.getChildren().addAll(Documentsplat, h);
    // }

    void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public String getTitleLabel() {
        return titleabel.getText();
    }

    public String getDateFinLabel() {
        return dateFinLabel.getText();
    }

    public String getDateDebutLabel() {
        return dateDebutLabel.getText();
    }

    public String getCategorieLabel() {
        return categorieLabel.getText();
    }

    public String getTypeLabel() {
        return typeLabel.getText();
    }

    public String getDescriptionLabel() {
        return descriptionLabel.getText();
    }

    public void setTitleLabel(String titleabel) {
        this.titleabel.setText(titleabel);
    }
    

    public void setDateFinLabel(String dateFinLabel) {
        this.dateFinLabel.setText(dateFinLabel);
    }

    public void setDateDebutLabel(String dateDebutLabel) {
        this.dateDebutLabel.setText(dateDebutLabel);
    }

    public void setCategorieLabel(String categorieLabel) {
        this.categorieLabel.setText(categorieLabel);
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel.setText(typeLabel);
    }

    public void setDescriptionLabel(String descriptionLabel) {
        this.descriptionLabel.setText(descriptionLabel);
    }

    private void action() {
        listesButton.setOnAction(event -> {
            controller.listesButtonAction(event);
        });
        Update.setOnAction(event -> {
            controller.UpdateButtonAction(event);
        });
        projectsButton.setOnAction(event -> {
            controller.projectsButtonAction(event);
        });
        archiveButton.setOnAction(event -> {
            controller.archiveButtonAction(event);
        });
        addDocButton.setOnAction(event -> {
            controller.addDocButtonAction();
        });
    }

    public void displayDocuments(LinkedHashMap<String, ArrayList<String>> documents) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayDocuments'");
    }
}
