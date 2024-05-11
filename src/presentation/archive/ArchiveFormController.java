package presentation.archive;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presentation.listes.ListeFormView;
import presentation.projets.ProjetsFormView;

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