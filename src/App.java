import javafx.application.Application;
import javafx.stage.Stage;
import presentation.archive.screenArchive;
import presentation.listes.screenList;
import presentation.login.screenLogin;
import presentation.projets.screenProjets;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // screenList list = new screenList(); 
        // screenLogin login = new screenLogin();
        screenProjets projets = new screenProjets();
        // screenArchive archive = new screenArchive();
        // login.start(primaryStage); // Appelez la méthode start()
        projets.start(primaryStage); // Appelez la méthode start()
        // list.start(primaryStage); // Appelez la méthode start()
        // archive.start(primaryStage); // Appelez la méthode start()
    }

    public static void main(String[] args) {
        launch(args);
    }
}
