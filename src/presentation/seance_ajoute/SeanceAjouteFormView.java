package presentation.seance_ajoute;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



public class SeanceAjouteFormView{

    private AnchorPane view;

    public SeanceAjouteFormView() {
        view = new AnchorPane();
        createUI();
    }

    private void createUI() {
    	// Set background color for the root AnchorPane
        view.setStyle("-fx-background-color: #f0f0f0;");

        HBox navigationBar = createNavigationBar();
        HBox bottomBar = createBottomBar();
         
        Label titleLabel = new Label("Titre de ma seance");
        titleLabel.getStyleClass().add("titleLabel"); 
        
        TextField titleField = new TextField();
        titleField.getStyleClass().add("titleField"); 
        
        Label descreptionLabel = new Label("Descreption");
        descreptionLabel.getStyleClass().add("descriptionLabel");
        
        TextArea descreptionField = new TextArea();
        descreptionField.getStyleClass().add("descriptionField");
        
        // Create container for title label and input
        VBox titleContainer = new VBox();
        titleContainer.getChildren().addAll(titleLabel, titleField,descreptionLabel,descreptionField);
        titleContainer.setSpacing(5); 
        Label docLabel = new Label("Documents");
        docLabel.getStyleClass().add("titleLabel");
        Button docsButton = createButton("Document 1", "docs-button-style");

        // Create HBox to contain description field and the button
        VBox descriptionContainer = new VBox();
        descriptionContainer.getChildren().addAll( docLabel,docsButton);
        descriptionContainer.setSpacing(10);
        
        HBox dateCategoryContainer = createDateCategoryContainer();
        
        VBox NoteContainer = NoteContainer();
        
        AnchorPane.setTopAnchor(dateCategoryContainer, 70.0);
        AnchorPane.setRightAnchor(dateCategoryContainer, 90.0);
        
        AnchorPane.setTopAnchor(NoteContainer, 150.0);
        AnchorPane.setRightAnchor(NoteContainer,40.0);
        // Add title container under navigation bar
        AnchorPane.setTopAnchor(titleContainer, 70.0); // Adjust top anchor as needed
        AnchorPane.setLeftAnchor(titleContainer, 20.0); // Adjust left anchor as needed
        AnchorPane.setRightAnchor(titleContainer, 20.0); // Adjust right anchor as needed
        
        AnchorPane.setTopAnchor(descriptionContainer, 380.0); // Adjust top anchor as needed
        AnchorPane.setLeftAnchor(descriptionContainer, 20.0); // Adjust left anchor as needed
        AnchorPane.setRightAnchor(descriptionContainer, 20.0);
        // Add navigation bar to the top of the root
        AnchorPane.setTopAnchor(navigationBar, 0.0);
        AnchorPane.setLeftAnchor(navigationBar, 0.0);
        AnchorPane.setRightAnchor(navigationBar, 0.0);

        // Add bottom bar to the bottom of the root
        AnchorPane.setBottomAnchor(bottomBar,  10.0);
        AnchorPane.setLeftAnchor(bottomBar, 10.0);
        AnchorPane.setRightAnchor(bottomBar, 0.0);

        view.getChildren().addAll(titleContainer,navigationBar, bottomBar,descriptionContainer,dateCategoryContainer,NoteContainer);
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox();
        navigationBar.getStyleClass().add("navigationBar"); 
        navigationBar.setPadding(new Insets(8)); 
        navigationBar.setSpacing(10);

        Button backButton = new Button();
        backButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 35, 35);
        backButton.getStyleClass().add("button-left-style");
        Button tachesButton = createButton("Taches", "button-style");
        Button projectsButton = createButton("Projects", "button-style");
        Button archiveButton = createButton("Archive","button-style");

        
        navigationBar.getChildren().addAll(backButton,tachesButton, projectsButton, archiveButton);
        
        return navigationBar;
    }
    
    private HBox createDateCategoryContainer() {
        HBox container = new HBox();
        container.setSpacing(10);

        // Label and DatePicker for "Début"
        Label debutLabel = new Label("Début:");
        debutLabel.getStyleClass().add("titleLabel");
        DatePicker debutDatePicker = new DatePicker();
        debutDatePicker.getStyleClass().add("");
        debutDatePicker.setPrefWidth(150);

        // Label and DatePicker for "Fin"
        Label finLabel = new Label("Fin:");
        finLabel.getStyleClass().add("titleLabel");
        DatePicker finDatePicker = new DatePicker();
        finDatePicker.getStyleClass().add("");
        finDatePicker.setPrefWidth(150);

        

        container.getChildren().addAll(debutLabel, debutDatePicker, finLabel, finDatePicker);
        return container;
    }
    
    private VBox NoteContainer() {
        VBox container = new VBox();
        container.setSpacing(10);
        
        Label NoteLabel = new Label("                            Note");
        NoteLabel.getStyleClass().add("titleLabel"); 
        
        TextArea NoteField = new TextArea();
        NoteField.getStyleClass().add("NoteField");


        container.getChildren().addAll(NoteLabel, NoteField );
        return container;
    }

    private HBox createBottomBar() {
        HBox bottomBar = new HBox();
        bottomBar.setAlignment(Pos.BASELINE_LEFT); // Align buttons to the left in a horizontal line
        bottomBar.setSpacing(10);

        // Create buttons
        Button ajouterButton = createButtonWithIcon("Ajouter", "file:./Pictures/add.png", 20, 20);
        ajouterButton.getStyleClass().add("button-style");
        Button saveButton = createButtonWithIcon("Enregistrer", "file:./Pictures/save.png", 20, 20);
        saveButton.getStyleClass().add("button-style");
        Button annulerButton = createButton("Annuler", "button-style");

        // Add buttons to the bottom bar
        bottomBar.getChildren().addAll(ajouterButton, saveButton, annulerButton);

        return bottomBar;
    }

    private Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        
       
        button.setOnMousePressed(event -> button.getStyleClass().add("button-clicked-style"));
        
        
        button.setOnMouseReleased(event -> button.getStyleClass().remove("button-clicked-style"));
        
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
        
        button.setOnMouseEntered(event -> {
            button.getStyleClass().add("button-clicked-style");
            button.setCursor(Cursor.HAND);
        });
        
        button.setOnMouseExited(event -> {
            button.getStyleClass().remove("button-clicked-style");
            button.setCursor(Cursor.DEFAULT);
        });
        
        return button;
    }
    private Button createButtonWithIcon(String name, String string, int i, int j) {
        Button button = new Button(name);
        try {
            Image icon = new Image(string);
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(i);
            iconView.setFitHeight(j);
            button.setGraphic(iconView);
            button.setOnMousePressed(event -> button.getStyleClass().add("button-clicked-style"));
            
            
            button.setOnMouseReleased(event -> button.getStyleClass().remove("button-clicked-style"));
            
            button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
            button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
            
            button.setOnMouseEntered(event -> {
                button.getStyleClass().add("button-clicked-style");
                button.setCursor(Cursor.HAND);
            });
            
            button.setOnMouseExited(event -> {
                button.getStyleClass().remove("button-clicked-style");
                button.setCursor(Cursor.DEFAULT);
            });
        } catch (Exception e) {
            System.out.println("Error loading the icon: " + e.getMessage());
        }
        return button;
        
    }


    public AnchorPane getView() {
        return view;
    }
}
