package presentation.listes;
import java.util.*;


public class ListModel {
    private LinkedHashMap<String , String> Listes;

    public ListModel(LinkedHashMap<String , String> Listes) {
        this.Listes = Listes;
    }

    public ListModel() {
        super() ;
    }

    public LinkedHashMap<String , String> getLists() {
        return Listes;
    }


    public void setLists(LinkedHashMap<String , String> Listes) {
        this.Listes = Listes;
    }

    public void sortLists() {
       //trier liste par le titre 
        List<Map.Entry<String, String>> list = new LinkedList<>(Listes.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        LinkedHashMap<String, String> sortedList = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : list) {
            sortedList.put(entry.getKey(), entry.getValue());
        }
        this.setLists(sortedList);
    }
}
