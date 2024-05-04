package presentation.NewList;

import java.util.List;

public class AddListModel {
    private String Titre ; 
    private String Discription ;
    private List<String> TitreSelectionnes ;

    public AddListModel(String titre, String discription ,List<String> titreSelectionnes ) {
        Titre = titre;
        Discription = discription;
        TitreSelectionnes = titreSelectionnes ;

    }
    public String getTitre() {
        return Titre;
    }
    public String getDiscription() {
        return Discription;
    }
    public void setTitre(String titre) {
        Titre = titre;
    }
    public void setDiscription(String discription) {
        Discription = discription;
    }
    public List<String> getTitreSelectionnes() {
        return TitreSelectionnes;
    }
    public void setTitreSelectionnes(List<String> titreSelectionnes) {
        TitreSelectionnes = titreSelectionnes;
    }
    
}
