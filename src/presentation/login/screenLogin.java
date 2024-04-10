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
                ImageView BGimageView = createBGimageView("file:./Pictures/Login-Page.png", 600, 350, 334.0, 150.0);

                // Logo Ensa
                Pane LogoPane = createLogoPane("file:./Pictures/Logo-Ensa.png", 80, 50, 345, 155);

                // text field email
                Pane inputContainer = createInputContainer("Example@gmail.com", 490, 330);

                // Connect Button
                Button connectButton = createConnectButton("Connect", 600, 400);

                // Add all to the root
                root.getChildren().addAll(BGimageView, inputContainer, LogoPane, connectButton);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
                primaryStage.setTitle("TO DO LIST");
                primaryStage.setMaximized(true);
                primaryStage.setScene(scene);
                primaryStage.show();
        }

        private ImageView createBGimageView(String string, int i, int j, double d, double e) {
                Image BGimage = new Image(string);
                ImageView BGimageView = new ImageView(BGimage);
                BGimageView.setFitWidth(i);
                BGimageView.setFitHeight(j);
                AnchorPane.setTopAnchor(BGimageView, e);
                AnchorPane.setLeftAnchor(BGimageView, d);
                return BGimageView;
        }

        private Pane createLogoPane(String string, int i, int j, int k, int l) {
                Image logo = new Image(string);
                ImageView logoView = new ImageView(logo);
                logoView.setFitWidth(i);
                logoView.setFitHeight(j);
                Pane LogoPane = new Pane(logoView);
                LogoPane.setLayoutX(k);
                LogoPane.setLayoutY(l);
                return LogoPane;
        }

        private Pane createInputContainer(String string, int i, int j) {
                TextField textField = new TextField();
                textField.getStyleClass().add("text-field");
                textField.setPromptText(string);
                Pane inputContainer = new Pane(textField);
                inputContainer.setLayoutX(i);
                inputContainer.setLayoutY(j);
                return inputContainer;
        }
        
        private Button createConnectButton(String string, int i, int j) {
                Button connectButton = new Button(string);
                connectButton.getStyleClass().add("connect-button");
                connectButton.setLayoutX(i);
                connectButton.setLayoutY(j);
                return connectButton;
        }
}

