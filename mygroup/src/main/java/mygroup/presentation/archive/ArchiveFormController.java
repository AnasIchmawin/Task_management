package mygroup.presentation.archive ;


import javafx.stage.Stage;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projets.ProjetsFormView ;

public class ArchiveFormController {
    private ArchiveFormView archiveView;

    public ArchiveFormController(ArchiveFormView archiveView) {
        this.archiveView = archiveView;
    }

    public void handleListesButtonAction() {
        Stage stage = (Stage) archiveView.getZoneListes().getScene().getWindow();
        ListeFormView liste = new ListeFormView();
        liste.start(stage);
    }

    //handleProjectsButtonAction
    public void handleProjectsButtonAction() {
        Stage stage = (Stage) archiveView.getZoneListes().getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

}