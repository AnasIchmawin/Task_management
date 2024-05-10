package metier;

import java.util.List;

import org.bson.Document;

import persistence.DAO.DAODocument;

public class GestionnaireDocument {
    DAODocument daoDocument ;

       public GestionnaireDocument() {
        this.daoDocument = new DAODocument();
    }

    public List<Document> getAllDocuments() {
        return daoDocument.getAllDocuments();
    }
    
}
