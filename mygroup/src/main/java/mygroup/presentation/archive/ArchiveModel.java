package mygroup.presentation.archive;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArchiveModel {
    private LinkedHashMap<String, String> projets;

    public ArchiveModel(LinkedHashMap<String, String> projets) {
        this.projets = projets;
    }

	public void setProjets(LinkedHashMap<String, String> projets) {
        this.projets = projets;
	}

    public void sortProjets() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortProjets'");
    }

    public LinkedHashMap<String, String> getProjets() {
        return projets;
    }
    
}
