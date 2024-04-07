package presentation.login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class screenLogin extends Application{

                @Override
                public void start(Stage primaryStage) {
                        AnchorPane root = new AnchorPane();

                        // Background Image Blue
                        Image BGimage = new Image(
                                                        "file:/C:/Users/hp/Desktop/projet%20java%202024/Task_management-main/Pictures/Login-Page.png");
                        ImageView BGimageView = new ImageView(BGimage);
                        BGimageView.setFitWidth(600);
                        BGimageView.setFitHeight(350);
                        AnchorPane.setTopAnchor(BGimageView, 150.0);
                        AnchorPane.setLeftAnchor(BGimageView, 334.0);

                        // Logo Ensa
                        Image logo = new Image(
                                        "file:/C:/Users/hp/Desktop/projet%20java%202024/Task_management-main/Pictures/Logo-Ensa.png");
                        ImageView logoView = new ImageView(logo);

                        logoView.setFitWidth(80);
                        logoView.setFitHeight(50);
                        Pane LogoPane = new Pane(logoView);
                        LogoPane.setLayoutX(345);
                        LogoPane.setLayoutY(155);

                        // text field email
                        TextField textField = new TextField();
                        textField.getStyleClass().add("text-field");
                        textField.setPromptText("Example@Gmail.com");

                        Pane inputContainer = new Pane(textField);
                        inputContainer.setLayoutX(490);
                        inputContainer.setLayoutY(330);

                        Button connectButton = new Button(
                                        "Connexion");
                        connectButton.getStyleClass().add("connect-button");
                        connectButton.setLayoutX(600);
                        connectButton.setLayoutY(400);

                        root.getChildren().add(BGimageView);
                        root.getChildren().add(inputContainer);
                        root.getChildren().add(LogoPane);
                        root.getChildren().add(connectButton);
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
                        primaryStage.setTitle("TO DO LIST");
                        primaryStage.setMaximized(true);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                }
        }

