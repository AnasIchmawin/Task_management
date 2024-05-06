package presentation.login;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import presentation.listes.ListeFormView;

public class LoginController {
    private LoginFormView loginFormView;
    private ModelLogin modelLogin;

    public LoginController(LoginFormView loginFormView) {
        super();
        this.loginFormView = loginFormView;
    }

    public void handleLoginButtonClick(ActionEvent event) throws IOException, ParseException {

        // Vérifier la validité de l'e-mail
        modelLogin = new ModelLogin(loginFormView.getEmailField());
        boolean emailIsValid = modelLogin.isValidEmailAddress();
        if (emailIsValid) {
            ListeFormView view = new ListeFormView();
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
