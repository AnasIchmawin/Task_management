package presentation.GetProjets;

import java.util.List;

public class GetProjetsModel {
    List<String> ProjetDeleted;

    public GetProjetsModel(List<String> projetDeleted) {
        projetDeleted = projetDeleted;
    }

    public GetProjetsModel() {
        super();
    }

    public List<String> getProjetDeleted() {
        return ProjetDeleted;
    }

    public void setProjetDeleted(List<String> projetDeleted) {
        ProjetDeleted = projetDeleted;
    }

}
