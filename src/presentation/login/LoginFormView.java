package presentation.login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormView extends Application {
    private static final int MIN_WIDTH = 1160;
    private static final int MIN_HEIGHT = 652;

    private LoginController controller;
    private BorderPane Formulaire;
    private TextField emailField;
    private Button connectButton;
    private Pane leftPane;
    private Pane rightPane;
    private Pane topPane;
    private Pane bottomPane;
    private HBox horizontalPane;
    private VBox verticalPane;
    private ImageView logoView;
    private VBox logoBox;
    private ImageView welcomeView;
    private VBox centerContainer;
    private VBox bottomContainer;
    private Stage primaryStage;

    public LoginFormView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Initialiser();
        Styler();
        Dessiner();
        Action();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(verticalPane, MIN_WIDTH, MIN_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoginForm");
        primaryStage.show();
    }

    private void Initialiser() {
        leftPane = createPane(HBox.class);
        rightPane = createPane(HBox.class);
        topPane = createPane(VBox.class);
        bottomPane = createPane(VBox.class);
        emailField = new TextField();
        emailField.setPromptText("Example@Gmail.com");
        connectButton = new Button("Connexion");
        logoView = createImageView("file:./Pictures/Logo-Ensa.png", 80, 50);
        logoBox = createVBox(10, Pos.TOP_LEFT, logoView);
        welcomeView = createImageView("file:./Pictures/welcome.png", 350, 140);
        centerContainer = createVBox(20, Pos.CENTER, welcomeView);
        bottomContainer = createVBox(20, Pos.CENTER, emailField, connectButton);
        Formulaire = new BorderPane();
        horizontalPane = new HBox(leftPane, Formulaire, rightPane);
        verticalPane = new VBox(topPane, horizontalPane, bottomPane);
        this.controller = new LoginController(this);
    }

    private void Styler() {
        leftPane.getStyleClass().add("Horizontal-Pane-style");
        rightPane.getStyleClass().add("Horizontal-Pane-style");
        topPane.getStyleClass().add("Vertical-Pane-style");
        bottomPane.getStyleClass().add("Vertical-Pane-style");
        emailField.getStyleClass().add("Email-Input-style");
        connectButton.getStyleClass().add("Button-style");
        Formulaire.getStyleClass().add("BorderPane-style");
    }

    private void Dessiner() {
        VBox.setVgrow(Formulaire, javafx.scene.layout.Priority.ALWAYS);
        Formulaire.setTop(logoBox);
        Formulaire.setCenter(centerContainer);
        BorderPane.setMargin(bottomContainer, new Insets(0, 0, 70, 0));
        Formulaire.setBottom(bottomContainer);
    }

    private ImageView createImageView(String path, int width, int height) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }

    @SuppressWarnings("deprecation")
    private <T extends Pane> T createPane(Class<T> paneType) {
        T pane;
        try {
            pane = paneType.newInstance();
            if (pane instanceof HBox) {
                HBox.setHgrow(pane, javafx.scene.layout.Priority.ALWAYS);
            } else if (pane instanceof VBox) {
                VBox.setVgrow(pane, javafx.scene.layout.Priority.ALWAYS);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            pane = null;
        }
        return pane;
    }

    private VBox createVBox(int spacing, Pos alignment, Node... nodes) {
        VBox vbox = new VBox(spacing);
        vbox.setAlignment(alignment);
        vbox.getChildren().addAll(nodes);
        return vbox;
    }

    public String getEmail() {
        return emailField.getText();
    }


    // Action
    private void Action() {
        connectButton.setOnAction(event -> {
            try {
                controller.handleLoginButtonClick(event);
            } catch (Exception e) {
                System.out.println("Erreur pendant l'action connexion : " + e.getMessage());
            }
        });
        connectButton.setOnMouseEntered(e -> {
            connectButton.getStyleClass().add("Button-style-hover");
            connectButton.setCursor(javafx.scene.Cursor.HAND);
        });
        connectButton.setOnMouseExited(e -> {
            connectButton.getStyleClass().remove("Button-style-hover");
            connectButton.setCursor(javafx.scene.Cursor.DEFAULT);
        });

        Scene scene = primaryStage.getScene();
        if (scene != null) {
            scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                double minWidth = MIN_WIDTH + 150;
                if (newWidth.doubleValue() < minWidth) {
                    primaryStage.setWidth(MIN_WIDTH + 150);
                }
            });
            scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
                double minHeight = MIN_HEIGHT + 100;
                if (newHeight.doubleValue() < minHeight) {
                    primaryStage.setHeight(MIN_HEIGHT + 50);
                }
            });
        }

    }
}
