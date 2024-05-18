package mygroup.presentation.taches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TachesFormModel {
    private LinkedHashMap<String, String> displayedTasks;
    private LinkedHashMap<String, Boolean> displayedTasksEtat;
    private LinkedHashMap<List<List<String>>,String> gridInfosCase;
    private String idTacheClicked;

    public TachesFormModel(LinkedHashMap<String, String> displayedTasks) {
        this.displayedTasks = displayedTasks;
        this.displayedTasksEtat = new LinkedHashMap<>();
        this.gridInfosCase = new LinkedHashMap<>();
    }

    public TachesFormModel() {
        super();
    }

    public LinkedHashMap<String, String> getdisplayedTasks() {
        return displayedTasks;
    }

    public void setdisplayedTasks(LinkedHashMap<String, String> displayedTasks) {
        this.displayedTasks = displayedTasks;
    }

    public Boolean getdisplayedTasksEtat(String id) {
        return displayedTasksEtat.get(id);
    }

    public void setdisplayedTasksEtat(String id, Boolean etat) {
        this.displayedTasksEtat.put(id, etat);
    }

    public LinkedHashMap<List<List<String>>, String> getGridInfosCase() {
        return gridInfosCase;
    }

    public void setGridInfosCase(LinkedHashMap<List<List<String>>, String> gridInfosCase) {
        this.gridInfosCase = gridInfosCase;
    }

    public void addTask(String id, String title) {
        displayedTasks.put(id, title);
    }

    public void removeTask(String id) {
        displayedTasks.remove(id);
    }

    public void updateTask(String id, String title) {
        displayedTasks.put(id, title);
    }

    public void addTaskEtat(String id, Boolean etat) {
        displayedTasksEtat.put(id, etat);
    }

    public Boolean getTaskEtat(String id) {
        return displayedTasksEtat.get(id);
    }

    public void removeTaskEtat(String id) {
        displayedTasksEtat.remove(id);
    }

    public void updateTaskEtat(String id, Boolean etat) {
        displayedTasksEtat.put(id, etat);
    }

    

    // sortdisplayedTasks
    public void sortTasksByTitle() {
        List<Map.Entry<String, String>> list = new LinkedList<>(displayedTasks.entrySet());
        list.sort(Map.Entry.comparingByValue());
        displayedTasks.clear();
        list.forEach(entry -> displayedTasks.put(entry.getKey(), entry.getValue()));
    }

    public void setIdTacheClicked(String idTacheClicked) {
        this.idTacheClicked = idTacheClicked;
    }

    public String getIdTacheClicked() {
        return idTacheClicked;
    }

    public void setTaskID(String taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTaskID'");
    }

    public LinkedHashMap<List<List<String>>, String> getGridInfoCase() {
        return gridInfosCase;
    }

    public void putInGridInfoCase(Integer Row, Integer Column, String id) {
        List<List<String>> caseInfo = new ArrayList<>();
        caseInfo.add(Arrays.asList(Row.toString(), Column.toString()));
        gridInfosCase.put(caseInfo, id);
    }

}
