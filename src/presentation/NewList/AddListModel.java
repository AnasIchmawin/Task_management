package presentation.NewList;

import java.util.HashMap;

public class AddListModel {
    private String Titre ; 
    private String Description ;
    private HashMap<String , String> TitreSelectionnes ;

    public AddListModel(String titre, String description ,HashMap<String , String> titreSelectionnes ) {
        Titre = titre;
        Description = description;
        TitreSelectionnes = titreSelectionnes ;

    }
    public String getTitre() {
        return Titre;
    }
    public String getDescription() {
        return Description;
    }
    public void setTitre(String titre) {
        Titre = titre;
    }


    public void setDiscription(String discription) {
        Description = discription;
    }
    public HashMap<String , String> getTitreSelectionnes() {
        return TitreSelectionnes ;
    }
    public void setTitreSelectionnes( HashMap<String , String> titreSelectionnes) {
        TitreSelectionnes = titreSelectionnes;
    }
    
}
