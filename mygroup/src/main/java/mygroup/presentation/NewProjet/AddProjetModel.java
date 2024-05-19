package mygroup.presentation.NewProjet;

import java.util.LinkedHashMap;

public class AddProjetModel {
    private LinkedHashMap<String, String> TachesSelectionnees;
    private LinkedHashMap<String, String> SeancesSelectionnees;
    private LinkedHashMap<String, String> DocumentsSelectionnes;

    public AddProjetModel(LinkedHashMap<String, String> tachesSelectionnees, LinkedHashMap<String, String> seancesSelectionnees,
            LinkedHashMap<String, String> documentsSelectionnes) {

        this.TachesSelectionnees = tachesSelectionnees;
        this.SeancesSelectionnees = seancesSelectionnees;
        this.DocumentsSelectionnes = documentsSelectionnes;
    }


 

    public LinkedHashMap<String, String> getTachesSelectionnees() {
        return TachesSelectionnees;
    }



    public void setTachesSelectionnees(LinkedHashMap<String, String> tachesSelectionnees) {
        this.TachesSelectionnees = tachesSelectionnees;
    }

    public void addTask(LinkedHashMap<String, String> task) {
        TachesSelectionnees.putAll(task);
    }

    public void addSeance(LinkedHashMap<String, String> seance) {
        SeancesSelectionnees.putAll(seance);
    }

    public void addDocument(LinkedHashMap<String, String> document) {
        DocumentsSelectionnes.putAll(document);

    }

    public LinkedHashMap<String, String> getSeancesSelectionnees() {
        return SeancesSelectionnees;
    }

    public LinkedHashMap<String, String> getDocumentsSelectionnes() {
       return DocumentsSelectionnes;
    }

}
