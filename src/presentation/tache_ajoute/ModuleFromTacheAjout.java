package presentation.tache_ajoute;

import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;

public class ModuleFromTacheAjout {
    private String idListe;

    public ModuleFromTacheAjout(String idListe) {
        this.idListe = idListe;
    }

    public String getIdListe() {
        return idListe;
    }
    public void setIdListe(String idListe) {
        this.idListe = idListe;
    }
}

