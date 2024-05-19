package mygroup.presentation.projets;

import java.util.*;

public class ProjetsModel {
    private LinkedHashMap<String, String> projets;
    private String selectedProjetId="664a4834ea23091eee57a01a";

    public ProjetsModel(LinkedHashMap<String, String> projets) {
        this.projets = projets;
    }

    public ProjetsModel() {
        super();
    }

    public LinkedHashMap<String, String> getProjets() {
        return projets;
    }

    public void setProjets(LinkedHashMap<String, String> projets) {
        this.projets = projets;
    }

    public void sortProjets() {
        // trier liste par le titre
        List<Map.Entry<String, String>> list = new LinkedList<>(projets.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        LinkedHashMap<String, String> sortedList = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : list) {
            sortedList.put(entry.getKey(), entry.getValue());
        }
        this.setProjets(sortedList);
    }

    public String getSelectedProjetId() {
        return selectedProjetId;
    }
}
