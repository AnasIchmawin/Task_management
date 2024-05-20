package mygroup.presentation.tache_detail;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.util.List;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.taches.TachesFormController;
import mygroup.presentation.projets.*;
import mygroup.presentation.archive.*;
import mygroup.presentation.NewDocument.*;

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
        displayDocuments();
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

        System.out.println("Title : " +Title);

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

    public void projectsButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

    public void archiveButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        ArchiveFormView archive = new ArchiveFormView();
        archive.start(stage);
    }

    

    public void addDocToTache(String idLastDoc, String titre , String description, String url) {
        this.model.addDocToTache(idLastDoc, titre, description, url);
        String idTache = tachesFormController.getTaskSelectedId();
        this.gestionnaireTache.addDocIdToTask(idTache,idLastDoc);
        System.out.println("Document ajouté à la tâche avec succès");
        
    }


    // afficher dans viewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
    // public void displayDocuments() {
    //     LinkedHashMap<String, ArrayList<String>> documents = model.getListOfDocuments();
    //     for (String id : documents.keySet()) {
    //         System.out.println("ID Document : " + id);
    //         System.out.println("Titre Document : " + documents.get(id).get(0));
    //         System.out.println("Description Document : " + documents.get(id).get(1));
    //         System.out.println("URL Document : " + documents.get(id).get(2));
    //     }
    // }

    public void displayDocuments() {
        LinkedHashMap<String, ArrayList<String>> documents = model.getListOfDocuments();
        for (String id : documents.keySet()) {
            String title = documents.get(id).get(0);
            String description = documents.get(id).get(1);
            String url = documents.get(id).get(2);
            view.addDocumentToContainer(title, description, url);
        }
    }

    public void addDocButtonAction() {
        System.out.println("Ajouter Button Clicked");
        AddDocumentView view = new AddDocumentView(this);
        Stage stage = new Stage();
        view.start(stage);
    }
}
