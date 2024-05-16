package mygroup.presentation.seance;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projets.ProjetsFormView;

public class SeanceFormController {
    @SuppressWarnings("unused")
    private SeanceFormView view;
    @SuppressWarnings("unused")
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
        ArchiveFormView nextView = new ArchiveFormView();
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
