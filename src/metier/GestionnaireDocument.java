package metier;

import persistence.DAO.DAODocument;

public class GestionnaireDocument {
    private DAODocument daoDocument;
    private POJODocument pojoDocument;

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

    public void setDaoDocument(DAODocument daoDocument) {
        this.daoDocument = daoDocument;
    }

    public void setPojoDocument(POJODocument pojoDocument) {
        this.pojoDocument = pojoDocument;
    }

    // Method to create a new document
    public void creerDocument() {
        daoDocument.create(this.pojoDocument.getTitre(), this.pojoDocument.getDescription(), this.pojoDocument.getURL(), this.pojoDocument.getDateAjout());
    }
    
}
