package mygroup.presentation.GetTasks;

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
import mygroup.presentation.NewList.AddListController;
import mygroup.presentation.NewProjet.AddProjetController;
import mygroup.presentation.tache_ajoute.ControllerFromTacheAjout;
import mygroup.presentation.projet_detail.ProjetDetailController;

public class GetTasksView {

    private GetTasksController controller;
    private Label titleLabel;
    private BorderPane root;
    private Button confirmButton;
    private Button cancelButton;
    private ScrollPane scrollPane;
    private GridPane ZoneTasks;

    public GetTasksView(AddListController addListController ,ControllerFromTacheAjout controllerFromTacheAjout) {
        init();
        style();
        action();
        this.controller = new GetTasksController(this, addListController, controllerFromTacheAjout);
    }


    public GetTasksView(AddProjetController addProjetController , ControllerFromTacheAjout controllerFromTacheAjout) {
        init();
        style();
        action();
        this.controller = new GetTasksController(this, addProjetController ,controllerFromTacheAjout);
    }

    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 550, 520);
        scene.getStylesheets().add(getClass().getResource("GetTaskStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liste des tâches");
        primaryStage.show();
    }

    private void init() {
        titleLabel = new Label("Liste des tâches");
        root = new BorderPane();
        confirmButton = createButton("Confirmer", "file:./mygroup/src/main/java/Pictures/confirmer.png", 16, 16);
        cancelButton = createButton("Annuler", "file:./mygroup/src/main/java/Pictures/annuler.png", 20, 20);
        scrollPane = createScrollPane(createTaskGridPane());
        VBox taskContainer = createVBox(5, Pos.TOP_CENTER);
        taskContainer.setPadding(new Insets(20, 0, 0, 20));
        root.setCenter(scrollPane);
        HBox buttonContainer = createHBox(25, Pos.CENTER);
        buttonContainer.getChildren().addAll(confirmButton, cancelButton);
        root.setTop(titleLabel);
        root.setBottom(buttonContainer);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        Insets buttonMargin = new Insets(0, 0, 20, 0);
        BorderPane.setMargin(buttonContainer, buttonMargin);
    }

    private void style() {
        titleLabel.getStyleClass().add("titleLabel-style");
        root.getStyleClass().add("root-style");
        confirmButton.getStyleClass().add("footBtn-style");
        cancelButton.getStyleClass().add("footBtn-style");
        scrollPane.getStyleClass().add("scroll-pane-style");
    }

    public void showView(Stage primaryStage, ActionEvent event) {
        start(primaryStage);
    }

    private GridPane createTaskGridPane() {
        ZoneTasks = new GridPane();
        ZoneTasks.getStyleClass().add("scroll-pane-style");
        ZoneTasks.setAlignment(Pos.TOP_CENTER);
        ZoneTasks.setHgap(20);
        ZoneTasks.setVgap(15);
        ZoneTasks.setPadding(new Insets(20));
        return this.ZoneTasks;
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

    private ScrollPane createScrollPane(GridPane content) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return scrollPane;
    }

    public GridPane getZoneTasks() {
        return ZoneTasks;
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
