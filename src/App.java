import javafx.application.Application;
import javafx.stage.Stage;
import presentation.listes.screenList;
import presentation.login.screenLogin;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        //screenList list = new screenList(); 
        screenLogin login = new screenLogin();
        login.start(primaryStage); // Appelez la méthode start() de Login
    }

    public static void main(String[] args) {
        launch(args);
    }
}
