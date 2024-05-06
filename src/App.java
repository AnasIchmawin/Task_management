import javafx.application.Application;
import javafx.stage.Stage;
import presentation.projet_detail.*;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ProjetDetailController controller = new ProjetDetailController();
        Projet_Detail_View projets = new Projet_Detail_View(controller);
        projets.start(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
