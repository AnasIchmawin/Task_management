package mygroup.presentation.tache_detail;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.presentation.archive.modele;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.taches.TachesFormController;

public class tacheDetailController {
    private tacheDetailModel model;
    private GestionnaireTache gestionnaireTache;
    private tacheDetailView view;
    private TachesFormController tachesFormController;
    private String Title;
    private String DateDebut;
    private String DateFin;
    private String Categorie;
    private String Type;
    private String Description;
    private String id;

    public tacheDetailController(String idTache, Stage primaryStage) {
        gestionnaireTache = new GestionnaireTache();
        view = new tacheDetailView();
        modeleCreate(idTache);
        implementVariables();
        implementView();
        id = idTache;
        view.start(primaryStage);
    }

    public tacheDetailController(TachesFormController tachesFormController) {
        this.tachesFormController = tachesFormController;
        gestionnaireTache = new GestionnaireTache();
        view = new tacheDetailView();
        String idTache = tachesFormController.getIdTacheClicked();
        modeleCreate(idTache);
        implementVariables();
        implementView();
        view.start(primaryStage);
    }

    public void modeleCreate(String idTache) {
        model = new tacheDetailModel(gestionnaireTache.getTitle(idTache), gestionnaireTache.getStartDate(idTache),
                gestionnaireTache.getEndDate(idTache), gestionnaireTache.getCategorie(idTache),
                gestionnaireTache.getType(idTache), gestionnaireTache.getDescription(idTache));
    }

    public void implementVariables() {
        Title = model.getTitleLable();
        DateDebut = model.getDateDebut();
        DateFin = model.getDateFin();
        Categorie = model.getCategorie();
        Type = model.getType();
        Description = model.getDescription();
    }

    public void implementView() {
        view.setTitleLabel(Title);
        view.setDateDebutLabel(DateDebut);
        view.setDateFinLabel(DateFin);
        view.setCategorieLabel(Categorie);
        view.setTypeLabel(Type);
        view.setDescriptionLabel(Description);
    }

    public void UpdateButtonAction(ActionEvent event) {
        Title = view.getTitleLabel();
        Description = view.getDescriptionLabel();
        if (Title.equals("") || Description.equals("")) {
            System.out.println("Veuillez remplir tous les champs");
        } else {
            gestionnaireTache.updateTask(Title, Description, id);
            System.out.println("Tache modifiée avec succès");
        }
    }

    public void listesButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        ListeFormView listes = new ListeFormView();
        listes.start(stage);

    }

}
