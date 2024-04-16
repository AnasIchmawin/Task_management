package presentation.document_ajoute;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import presentation.document_ajoute.DocumentFormcontroleur;

public class screenDocument {

	
	public void start(Stage primaryStage) {

        
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background:#ffffff");
	
	 HBox navigationBar = new HBox();
     navigationBar.getStyleClass().add("navigationBar"); 

     
     Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
     double screenWidth = primaryScreenBounds.getWidth();
     
     navigationBar.setPrefHeight(40); 
     navigationBar.setPrefWidth(screenWidth - 40); 
     
     Button LeftButton = createButtonWithIcon("", "file:./Pictures/left-arrow.png", 33, 33, "button-left-style");
     Button ListesButton = createButtonWithIcon("Listes", "file:./Pictures/list.png", 20, 20, "button-style");
     Button ProjetsButton = createButtonWithIcon("Projets", "file:./Pictures/project.png", 20, 20, "button-style");
     Button ArchiveButton = createButtonWithIcon("Archive", "file:./Pictures/archive.png", 20, 20, "button-style");
     Button AjouterdDocButton = createButtonWithIcon("Ajouter", "file:./Pictures/add.png", 15, 15,
             "Ajouter-doc-style");
   
     Button SaveButton = createButtonWithIcon("Save", "file:./Pictures/save.png", 15, 15, "button-style");
     Button AnnulerButton = createButtonWithIcon("Annuler", "file:./Pictures/addIcon.png", 15, 15, "button-style");
     
     HBox.setMargin(ListesButton, new Insets(0, 0, 0, 40));
     HBox.setMargin(ProjetsButton, new Insets(0, 0, 0, 15));
     HBox.setMargin(ArchiveButton, new Insets(0, 0, 0, 15));
     HBox.setMargin(LeftButton, new Insets(0, 0, 0, 15));

     // rectangle de background
     Rectangle BkGround = createBackgroundRectangle(screenWidth);

     // Ajout des boutons à la barre de navigation
     navigationBar.getChildren().addAll(LeftButton, ListesButton, ProjetsButton, ArchiveButton);
     
  // Labels
     Label TitreDescription = createLabel("Description");
     //Zone discreptions 
     TextArea ZoneDescription = createTextArea(screenWidth - 600, 200, "Ajouter une description", "Text-Area-Style");
     
  // buttons special editer
     Button Editer_document = EditerButton(0, 0);

   //  Les actions des boutons
     Editer_document.setOnAction(e -> {
    	 DocumentFormcontroleur.handleEditerButton(ZoneDescription);});
     
         
    SaveButton.setOnAction(e -> {
    	    DocumentFormcontroleur.handleSaveButton(ZoneDescription);
    	});
         
         GridPane ZoneDesDocuments = Grid(screenWidth - 600, 200);
         GridPane.setConstraints(AjouterdDocButton, 0, 20, 1, 1);
         ZoneDesDocuments.getChildren().add(AjouterdDocButton);
         
      // Définir la position du titre de la description
         AnchorPane.setTopAnchor(TitreDescription, 120.0);
         AnchorPane.setLeftAnchor(TitreDescription, 80.0);
         
      // Définir la position du grid
         AnchorPane.setTopAnchor(ZoneDesDocuments, 380.0);
         AnchorPane.setLeftAnchor(ZoneDesDocuments, 80.0);
         
         AnchorPane.setTopAnchor(navigationBar, 0.0);
         AnchorPane.setLeftAnchor(navigationBar, 20.0);
         
      // Définir la position du bouton save
         AnchorPane.setTopAnchor(SaveButton, 600.0); 
         AnchorPane.setLeftAnchor(SaveButton, 80.0);
         
      // Définir la position du rectangle de fond
         AnchorPane.setTopAnchor(BkGround, 55.0); 
         AnchorPane.setLeftAnchor(BkGround, 20.0);
         
      // position des boutons editer
         Editer_document.setLayoutX(730);
         Editer_document.setLayoutY(163);
         
      // Boton d'Ajout
         AjouterdDocButton.setLayoutX(290);
         AjouterdDocButton.setLayoutY(600);
         
       
         root.getChildren().addAll(BkGround, navigationBar, AjouterdDocButton, SaveButton, TitreDescription, ZoneDescription);

         Scene scene = new Scene(root, 600, 400);
         scene.getStylesheets().add(getClass().getResource("DocumentAjoutStyle.css").toExternalForm());
         primaryStage.setTitle("DOCUMENT");
         primaryStage.setMaximized(true);
         primaryStage.setScene(scene);
         primaryStage.show();

     
	}
	
	
	
	private Button createButtonWithIcon(String name, String path, int WidthImage, int HeightImage, String style) {
        Button Button = new Button(name);
        Button.setOnMouseEntered(event -> Button.setCursor(javafx.scene.Cursor.HAND));
        Button.setOnMouseExited(event -> Button.setCursor(javafx.scene.Cursor.DEFAULT));
        Button.getStyleClass().add(style);
        try {
            Image Icon = new Image(path);
            ImageView IconView = new ImageView(Icon);
            IconView.setFitWidth(WidthImage);
            IconView.setFitHeight(HeightImage);
            Button.setGraphic(IconView);
        } catch (Exception e) {
            System.out.println("Error loading the Ordonner icon");
        }
        return Button;
    }
	private Rectangle createBackgroundRectangle(double width) {
        Rectangle background = new Rectangle();
        background.setWidth(width - 40);
        background.setHeight(590);
        background.setStroke(Color.web("#112D4E"));
        background.setStrokeWidth(2.0);
        background.setFill(Color.rgb(240, 240, 240));
        background.setArcWidth(15);
        background.setArcHeight(15);
        AnchorPane.setTopAnchor(background, 55.0);
        AnchorPane.setLeftAnchor(background, 20.0);
        return background;
    }

	private TextArea createTextArea(double width, double height, String promptText, String style) {
        TextArea ZoneDescription = new TextArea();
        ZoneDescription.setPrefWidth(width);
        ZoneDescription.setPrefHeight(height);
        ZoneDescription.setWrapText(true);
        ZoneDescription.setPromptText(promptText);
        ZoneDescription.setEditable(false);
        ZoneDescription.getStyleClass().add(style);

        return ZoneDescription;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("Label-style");
        return label;
    }

    private GridPane Grid(double width, double height) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPrefWidth(width);
        grid.setPrefHeight(height);
        grid.setStyle("-fx-background-color: #8E9EB2; -fx-background-radius: 20px;");
        return grid;
    }

    private Button EditerButton(double X, double Y) {
        Button newButton = new Button();
        newButton.setOnMouseEntered(event -> newButton.setCursor(javafx.scene.Cursor.HAND));
        newButton.setOnMouseExited(event -> newButton.setCursor(javafx.scene.Cursor.DEFAULT));
        newButton.setShape(new Rectangle(20, 20));
        newButton.setMinSize(20, 20);
        newButton.setMaxSize(20, 20);
        newButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
        newButton.setLayoutX(X);
        newButton.setLayoutY(Y);

        try {
            Image careeIcon = new Image("file:./Pictures/edit.png");
            ImageView careeIconView = new ImageView(careeIcon);
            careeIconView.setFitWidth(15);
            careeIconView.setFitHeight(15);
            newButton.setGraphic(careeIconView);
        } catch (Exception e) {
            System.out.println("Error loading the caree icon");
        }
        return newButton;
    }
    
}
