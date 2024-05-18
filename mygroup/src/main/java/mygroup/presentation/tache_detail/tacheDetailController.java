package mygroup.presentation.tache_detail;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.taches.TachesFormController;

public class tacheDetailController {
    private tacheDetailModel model;
    private GestionnaireTache gestionnaireTache;
    private tacheDetailView view;
    private TachesFormController tachesFormController;

    // amine
    public tacheDetailController(tacheDetailView tacheDetailView, TachesFormController tachesFormController) {
        this.view = tacheDetailView;
        this.tachesFormController = tachesFormController;
        gestionnaireTache = new GestionnaireTache();
        Fillcahmps();
        implementView();
    }

    public void Fillcahmps() {
        String idTache = tachesFormController.getTaskSelectedId();
        model = new tacheDetailModel(gestionnaireTache.getTitle(idTache), gestionnaireTache.getStartDate(idTache),
                gestionnaireTache.getEndDate(idTache), gestionnaireTache.getCategorie(idTache),
                gestionnaireTache.getType(idTache), gestionnaireTache.getDescription(idTache));

    }

    public void implementView() {
        String Title = model.getTitleLable();
        String DateDebut = model.getDateDebut();
        String DateFin = model.getDateFin();
        String Categorie = model.getCategorie();
        String Type = model.getType();
        String Description = model.getDescription();

        view.setTitleLabel(Title);
        view.setDateDebutLabel(DateDebut);
        view.setDateFinLabel(DateFin);
        view.setCategorieLabel(Categorie);
        view.setTypeLabel(Type);
        view.setDescriptionLabel(Description);
    }

    public void UpdateButtonAction(ActionEvent event) {
        String Title = view.getTitleLabel();
        String Description = view.getDescriptionLabel();
        if (Title.equals("") || Description.equals("")) {
            System.out.println("Veuillez remplir tous les champs");
        } else {
            gestionnaireTache.updateTask(Title, Description, this.tachesFormController.getTaskSelectedId());
            System.out.println("Tache modifiée avec succès");
        }
    }

    public void listesButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        ListeFormView listes = new ListeFormView();
        listes.start(stage);

    }

}
