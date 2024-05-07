package presentation.listes;
import java.util.List;

import org.bson.Document;

public class modeleList {
    private List<Document> Listes;

    public modeleList(List<Document> Listes) {
        this.Listes = Listes;
    }

    public modeleList() {
        // TODO Auto-generated constructor stub
    }

    public List<Document> getListes() {
        return Listes;
    }

    public void setListes(List<Document> Listes) {
        this.Listes = Listes;
    }
}