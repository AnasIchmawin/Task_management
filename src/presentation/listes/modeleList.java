package presentation.listes;

import java.util.ArrayList;
import persistence.DAO.liste;

public class modeleList {
    private String searchField;
    private ArrayList<liste> listes;
    private String description;

    public modeleList() {
        this.searchField = "";
        this.description = "";
        this.listes = new ArrayList<>();
    }

    public modeleList(String searchField, ArrayList<liste> listes, String description) {
        this.searchField = searchField;
        this.listes = listes;
        this.description = description;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
