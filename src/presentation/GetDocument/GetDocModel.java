package presentation.GetDocument;

import java.util.LinkedHashMap;

public class GetDocModel {
    private LinkedHashMap<String , String> ListOfDocuments;

    public GetDocModel() {
        this.ListOfDocuments = new LinkedHashMap<>();
    }

    public LinkedHashMap<String , String>  getListOfDocuments() {
        return ListOfDocuments;
    }

    public void setListOfDocuments(LinkedHashMap<String , String>  listOfDocuments) {
        ListOfDocuments = listOfDocuments;
    }

    public void addDocumentToSeance(String id, String task) {
        ListOfDocuments.put(id, task);
    }
    
}
