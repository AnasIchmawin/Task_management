package presentation.GetLists;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.listes.ListeFormController;

public class GetListsView {

    private GetListsController controller;
    private ListeFormController listeFormController;
    private Label Question;
    private BorderPane root;
    private Button confirmButton;
    private Button cancelButton;
    private ScrollPane scrollPane;
    private GridPane ZoneLists;

    public GetListsView(ListeFormController listeFormController) {
        this.listeFormController = listeFormController;
        init();
        style();
        action();
    }

    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 550, 520);
        scene.getStylesheets().add(getClass().getResource("GetList-Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Les listes");
        primaryStage.show();
    }

    private void init() {
        Question = new Label("quelle liste voulez-vous supprimer ?");
        root = new BorderPane();
        confirmButton = createButton("Confirmer", "file:./Pictures/confirmer.png", 16, 16);
        cancelButton = createButton("Annuler", "file:./Pictures/annuler.png", 20, 20);
        VBox listContainer = createVBox(5, Pos.TOP_CENTER);
        listContainer.setPadding(new Insets(20, 0, 0, 20));

        HBox buttonContainer = createHBox(25, Pos.CENTER);
        buttonContainer.getChildren().addAll(confirmButton, cancelButton);
        VBox TopContainer = createVBox(5, Pos.TOP_CENTER);
        TopContainer.getChildren().addAll(Question);
        TopContainer.setPadding(new Insets(15));
        root.setTop(TopContainer);
        root.setBottom(buttonContainer);
        Insets buttonMargin = new Insets(0, 0, 20, 0);
        this.ZoneLists = createListGridPane();
        System.out.println("ZoneLists: " + ZoneLists);
        scrollPane = createScrollPane();
        root.setCenter(scrollPane);
        this.controller = new GetListsController(this, this.listeFormController);
        BorderPane.setMargin(buttonContainer, buttonMargin);

    }

    private void style() {
        Question.getStyleClass().add("titleLabel-style");
        root.getStyleClass().add("root-style");
        confirmButton.getStyleClass().add("footBtn-style");
        cancelButton.getStyleClass().add("footBtn-style");
        scrollPane.getStyleClass().add("scroll-pane-style");
    }

    public void showView(Stage primaryStage, ActionEvent event) {
        start(primaryStage);
    }

    public GridPane createListGridPane() {
        GridPane gridPane = new GridPane();
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.getStyleClass().add("scroll-pane-style");
        gridPane.setHgap(35);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));
        return gridPane;
    }

    private VBox createVBox(int spacing, Pos position) {
        VBox vbox = new VBox();
        vbox.setSpacing(spacing);
        vbox.setAlignment(position);
        return vbox;
    }

    private HBox createHBox(int spacing, Pos position) {
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        hbox.setAlignment(position);
        return hbox;
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
        buttonContent.setSpacing(10);

        newButton.setGraphic(buttonContent);

        return newButton;
    }

    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(this.ZoneLists);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return scrollPane;
    }

    public GridPane getZoneLists() {
        return ZoneLists;
    }

    private void action() {
        confirmButton.setOnAction(event -> {
            this.controller.handleConfirmButton(event);
        });

        cancelButton.setOnAction(event -> {
            this.controller.handleCancelButton(event);
        });

    }

}
