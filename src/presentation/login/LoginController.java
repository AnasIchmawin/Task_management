package presentation.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node; // Importer la classe Node
import javafx.stage.Stage; // Importer la classe Stage
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;

public class LoginController {

    public void handleLoginButtonClick(ActionEvent event, String email) throws IOException {
        // Vérifier la validité de l'e-mail 
        boolean emailIsValid = modeleLogin.CheckEmail(email) ;
        if (emailIsValid) {
            ListeFormController controller = new ListeFormController();
            ListeFormView view = new ListeFormView(controller);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la fenêtre actuelle
            view.start(stage); // Afficher la vue ListeFormView dans la fenêtre actuelle
        } else {
            System.out.println("Email invalide ");
        }
    }
}
