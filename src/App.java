

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.tache_ajoute.ViewFromTacheAjout;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    	ViewFromTacheAjout view = new ViewFromTacheAjout();
        Scene scene = new Scene(view.getView(), 1160, 652);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}