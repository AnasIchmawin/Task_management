package mygroup.presentation.projets;

import java.util.*;

public class ProjetsModel {
    private LinkedHashMap<String, String> DisplayedProjects;
    private LinkedHashMap<List<List<String>>, String> gridInfoCase;
    private String selectedProjetId;

    public ProjetsModel(LinkedHashMap<String, String> projets ,LinkedHashMap<List<List<String>>, String> gridInfoCase) {
        this.DisplayedProjects = projets;
        this.gridInfoCase = gridInfoCase;
    }

    public ProjetsModel() {
        super();
        gridInfoCase = new LinkedHashMap<>();
    }



    public String getSelectedProjetId() {
        return selectedProjetId;
    }

    public void setSelectedProjetId(String selectedProjetId) {
        this.selectedProjetId = selectedProjetId;
    }

    public LinkedHashMap<String, String> getProjets() {
        return DisplayedProjects;
    }

    public void setProjets(LinkedHashMap<String, String> projets) {
        this.DisplayedProjects = projets;
    }

    public void sortProjets() {
        // trier liste par le titre
        List<Map.Entry<String, String>> list = new LinkedList<>(DisplayedProjects.entrySet());
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

  
    public void putInGridInfoCase(Integer Row, Integer Column, String id) {
        List<List<String>> caseInfo = new ArrayList<>();
        caseInfo.add(Arrays.asList(Row.toString(), Column.toString()));
        gridInfoCase.put(caseInfo, id);
    }


    public LinkedHashMap<List<List<String>>, String> getGridInfoCase() {
        return gridInfoCase;
    }
}
