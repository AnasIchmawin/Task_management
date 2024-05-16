package mygroup.presentation.GetLists;

import java.util.List;

public class GetListsModel {
    List<String> ListDeleted;

    public GetListsModel(List<String> listDeleted) {
        ListDeleted = listDeleted;
    }

    public GetListsModel() {
        super();
    }

    public List<String> getListDeleted() {
        return ListDeleted;
    }

    public void setListDeleted(List<String> listDeleted) {
        ListDeleted = listDeleted;
    }

}
