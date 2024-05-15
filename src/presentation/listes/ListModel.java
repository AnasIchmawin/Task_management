package presentation.listes;

import java.util.*;

public class ListModel {
    private LinkedHashMap<String, String> DisplayedLists;
    private LinkedHashMap<List<List<String>>, String> gridInfoCase;
    private String listID;

    public ListModel() {
        super();
    }

    public ListModel(LinkedHashMap<String, String> Listes,
            LinkedHashMap<List<List<String>>, String> gridInfoCase) {

        this.DisplayedLists = Listes;
        this.gridInfoCase = gridInfoCase;
    }

    public LinkedHashMap<String, String> getLists() {
        return DisplayedLists;
    }

    public void setLists(LinkedHashMap<String, String> Listes) {
        this.DisplayedLists = Listes;
    }

    public LinkedHashMap<List<List<String>>, String> getGridInfoCase() {
        return gridInfoCase;
    }

    public void setGridInfoCase(LinkedHashMap<List<List<String>>, String> gridInfoCase) {
        this.gridInfoCase = gridInfoCase;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public void putInGridInfoCase(Integer Row, Integer Column, String id) {
        List<List<String>> caseInfo = new ArrayList<>();
        caseInfo.add(Arrays.asList(Row.toString(), Column.toString()));
        gridInfoCase.put(caseInfo, id);
    }

    // Method to sort the lists by alphabetical order
    public void sortListsByTitle() {
        List<Map.Entry<String, String>> list = new LinkedList<>(DisplayedLists.entrySet());
        list.sort(Map.Entry.comparingByValue());
        DisplayedLists.clear();
        list.forEach(entry -> DisplayedLists.put(entry.getKey(), entry.getValue()));
    }

    public void addList(String id, String titre) {
        DisplayedLists.put(id, titre);

    }

    public void removeList(List<String> id) {
        for (String i : id) {
            DisplayedLists.remove(i);
        }
    }

}
