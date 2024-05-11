package metier;

import java.util.List;

import org.bson.Document;

import persistence.DAO.DAODocument;
import metier.POJODocument;

public class GestionnaireDocument {
    private DAODocument daoDocument;
    private POJODocument pojoDocument;

       public void GestionnaireDocument() {
        this.daoDocument = new DAODocument();
    }

    public List<Document> getAllDocuments() {
        return daoDocument.getAllDocuments();
    }

    public GestionnaireDocument() {
        this.daoDocument = new DAODocument();
        this.pojoDocument = new POJODocument();
    }

    public DAODocument getDaoDocument() {
        return daoDocument;
    }

    public POJODocument getPojoDocument() {
        return pojoDocument;
    }



    public void setPojoDocument(POJODocument pojoDocument) {
        this.pojoDocument = pojoDocument;
    }

    // Method to create a new document
    public void creerDocument() {
        daoDocument.create(this.pojoDocument.getTitre(), this.pojoDocument.getDescription(), this.pojoDocument.getURL(), this.pojoDocument.getDateAjout(), this.pojoDocument.getIdProjet(), this.pojoDocument.getIdTache(), this.pojoDocument.getIdSeance());
    }
    
}
