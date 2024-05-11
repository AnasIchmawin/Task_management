package presentation.seance;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import presentation.archive.ArchiveFormController;
import presentation.archive.ArchiveFormView;
import presentation.listes.ListeFormView;
import presentation.projets.ProjetsFormView;

public class SeanceFormController {
    private SeanceFormView view;
    private SeanceModel model;

    SeanceFormController(SeanceFormView view) {
        this.view = view;
    }

    public void goToListes(ActionEvent event) {
        ListeFormView nextView = new ListeFormView();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextView.start(stage);
    }

    public void goToProjects(ActionEvent event) {
        ProjetsFormView nextView = new ProjetsFormView();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextView.start(stage);
    }

    public void goToArchive(ActionEvent event) {
        ArchiveFormController contoller = new ArchiveFormController();
        ArchiveFormView nextView = new ArchiveFormView(contoller);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextView.start(stage);
    }

    public void goToHome() {
        //
    }

    public void goToAjoutDoc(ActionEvent event) {
        //
    }

}
