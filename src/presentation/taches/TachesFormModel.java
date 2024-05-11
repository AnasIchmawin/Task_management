package presentation.taches;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TachesFormModel {
    private LinkedHashMap<String , String> taches;
    private LinkedHashMap<String , Boolean> tachesEtat;

    public TachesFormModel(LinkedHashMap<String , String> taches) {
        this.taches = taches;
    }

    public TachesFormModel() {
        super() ;
    }

    public LinkedHashMap<String , String> getTaches() {
        return taches;
    }

    public void setTaches(LinkedHashMap<String , String> taches) {
        this.taches = taches;
    }

    public Boolean getTachesEtat(String id) {
        return tachesEtat.get(id);
    }

    public void setTachesEtat(String id, Boolean etat) {
        this.tachesEtat.put(id, etat);
    }

    //sortTaches
    public void sortTaches() {
        //trier taches par le titre
        List<Map.Entry<String, String>> tache = new LinkedList<>(taches.entrySet());
        Collections.sort(tache, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        LinkedHashMap<String, String> sortedTaches = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : tache) {
            sortedTaches.put(entry.getKey(), entry.getValue());
        }
        this.setTaches(sortedTaches);
    }
}
