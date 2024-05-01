package presentation.login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormView extends Application {
    final static Integer MIN_WIDTH = 450;
    final static Integer MIN_HEIGHT = 450;
    private static VBox verticalPane;

    private final LoginController controller;
    private final BorderPane Formulaire;
    private final TextField emailField;
    private final Button connectButton;
    private Pane leftPane;
    private Pane rightPane;
    private Pane topPane;
    private Pane bottomPane;

    public LoginFormView(LoginController controller) {
        this.controller = controller;

        // Créer les éléments de la vue
        emailField = createEmailField();
        connectButton = createConnectButton();

        // Créer le BorderPane
        Formulaire = createBorderPane(emailField, connectButton);
        verticalPane = initialize();

    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(verticalPane, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoginForm");
        primaryStage.show();

        // Gestion de la taille minimale de la fenêtre
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

    private ImageView createImageView(String path, int width, int height) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }

    private Pane createPane(String styleClass, String BoxType) {
        Pane pane;
        if (BoxType.equals("HBox")) {
            pane = new HBox();
            HBox.setHgrow(pane, javafx.scene.layout.Priority.ALWAYS);
        } else if (BoxType.equals("VBox")) {
            pane = new VBox();
            VBox.setVgrow(pane, javafx.scene.layout.Priority.ALWAYS);
        } else {
            pane = new Pane();
        }
        pane.getStyleClass().add(styleClass);
        return pane;
    }

    private BorderPane createBorderPane(TextField emailField, Button connectButton) {
        BorderPane pane = new BorderPane();

        // Ajouter le logo dans le panneau supérieur
        ImageView logoView = createImageView("file:./Pictures/Logo-Ensa.png", 80, 50);
        VBox logoBox = createVBox(10, Pos.TOP_LEFT, logoView);
        pane.setTop(logoBox);

        // Ajouter l'image de bienvenue dans le panneau central
        ImageView welcomeView = createImageView("file:./Pictures/welcome.png", 350, 140);
        VBox centerContainer = createVBox(20, Pos.CENTER, welcomeView);
        pane.setCenter(centerContainer);

        // Ajouter les champs email et bouton de connexion dans le panneau inférieur
        VBox bottomContainer = createVBox(20, Pos.CENTER, emailField, connectButton);
        BorderPane.setMargin(bottomContainer, new Insets(0, 0, 70, 0)); // Définir la marge pour le conteneur inférieur
        pane.setBottom(bottomContainer);

        // Ajouter le style de la classe BorderPaneFF
        pane.setPrefSize(550, 400);
        pane.getStyleClass().add("BorderPane-style");

        return pane;
    }

    private VBox createVBox(int spacing, Pos alignment,
            Node... nodes) {
        VBox vbox = new VBox(spacing);
        vbox.setAlignment(alignment);

        vbox.getChildren().addAll(nodes);
        return vbox;
    }

    private TextField createEmailField() {
        TextField field = new TextField();
        field.setPromptText("Example@Gmail.com");
        field.getStyleClass().add("Email-Input-style");
        return field;
    }

    private Button createConnectButton() {
        Button button = new Button("Connexion");
        button.getStyleClass().add("Button-style");
        button.setOnAction(event -> {
            try {
                controller.handleLoginButtonClick(event, emailField.getText());
            } catch (Exception e) {
                System.out.println("Erreur pendant le clic : " + e.getMessage());
            }
        });
        return button;
    }

    private VBox initialize() {
        leftPane = createPane("Horizontal-Pane-style", "HBox");
        rightPane = createPane("Horizontal-Pane-style", "HBox");
        topPane = createPane("Vertical-Pane-style", "VBox");
        bottomPane = createPane("Vertical-Pane-style", "VBox");
        HBox horizontalPane = new HBox(leftPane, Formulaire, rightPane);
        VBox verticalPane = new VBox(topPane, horizontalPane, bottomPane);
        VBox.setVgrow(Formulaire, javafx.scene.layout.Priority.ALWAYS);
        return verticalPane;
    }
}
