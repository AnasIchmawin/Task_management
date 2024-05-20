package mygroup;

import javafx.application.Application;
import javafx.stage.Stage;
import mygroup.presentation.login.LoginFormView;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginFormView login = new LoginFormView(primaryStage);
        login.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        // Appel de la méthode pour effacer le contenu du token
        // TaskQuickstart.clearTokenContent();
        // CalendarQuickstart.clearTokenContent();
        // System.out.println("les tokens sont effacés");
    }

    public static void main(String[] args) {
        launch(args);
    }
}