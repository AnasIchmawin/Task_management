package presentation.listes;
// ajouter les donnees utilise dans cette interface et les getters et setters

import java.util.ArrayList;

import persistence.DAO.liste;

public class modeleList {
    private String searchField;
    private ArrayList<liste> listes;

    public modeleList() {
        this.searchField = "";
        this.listes = new ArrayList<>();
    }

    public modeleList(String searchField, ArrayList<liste> listes) {
        this.searchField = searchField;
        this.listes = listes;
    }

    public String getSearchField() {
        return searchField;
    }
    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
    public ArrayList<liste> getListes() {
        return listes;
    }
    public void setListes(ArrayList<liste> listes) {
        this.listes = listes;
    }
}
