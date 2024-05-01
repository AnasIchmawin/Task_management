package presentation.login;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;

public class LoginController {

    public void handleLoginButtonClick(ActionEvent event, String email) throws IOException, ParseException {
        // Vérifier la validité de l'e-mail
        boolean emailIsValid = modeleLogin.isValidEmailAddress(email);
        if (emailIsValid) {
            ListeFormController controller = new ListeFormController();
            ListeFormView view = new ListeFormView(controller);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la fenêtre actuelle
            view.start(stage);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'adresse e-mail est incorrecte.");

            // Afficher la fenêtre d'alerte
            alert.showAndWait();
        }
    }
}
