package presentation.listes;
import java.util.List;

public class modeleList {
    private List<String> Listes;

    public modeleList(List<String> Listes) {
        this.Listes = Listes;
    }

    public modeleList() {
        // TODO Auto-generated constructor stub
    }

    public List<String> getListes() {
        return Listes;
    }

    public void setListes(List<String> Listes) {
        this.Listes = Listes;
    }
}