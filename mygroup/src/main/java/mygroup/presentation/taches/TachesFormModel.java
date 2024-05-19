package mygroup.presentation.taches;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TachesFormModel {
    private LinkedHashMap<String, String> displayedTasks;
    private LinkedHashMap<String, Boolean> displayedTasksEtat;
    private LinkedHashMap<Integer, String> gridInfosCase;
    private String TaskSelectedId;

    public TachesFormModel(LinkedHashMap<String, String> displayedTasks) {
        this.displayedTasks = displayedTasks;
        this.displayedTasksEtat = new LinkedHashMap<>();
        this.gridInfosCase = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, String> getDisplayedTasks() {
        return displayedTasks;
    }

    public void setDisplayedTasks(LinkedHashMap<String, String> displayedTasks) {
        this.displayedTasks = displayedTasks;
    }

    public Boolean getDisplayedTaskEtat(String id) {
        return displayedTasksEtat.get(id);
    }

    public void setDisplayedTaskEtat(String id, Boolean etat) {
        this.displayedTasksEtat.put(id, etat);
    }

    public LinkedHashMap<Integer, String> getGridInfosCase() {
        return gridInfosCase;
    }

    public void setGridInfosCase(LinkedHashMap<Integer, String> gridInfosCase) {
        this.gridInfosCase = gridInfosCase;
    }

    public void addTask(String id, String title) {
        displayedTasks.put(id, title);
    }

    public void removeTask(String id) {
        displayedTasks.remove(id);
        displayedTasksEtat.remove(id); // Remove corresponding task state
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

    public String getTaskSelectedId() {
        return TaskSelectedId;
    }

    public void setTaskSelectedId(String id){
        TaskSelectedId = id ;
    }

    // getvaluer infoscase par key
    public String getValueGrid(Integer row) {
        return this.gridInfosCase.get(row);
    }

    // sortdisplayedTasks
    public void sortTasksByTitle() {
        List<Map.Entry<String, String>> list = new LinkedList<>(displayedTasks.entrySet());
        list.sort(Map.Entry.comparingByValue());
        displayedTasks.clear();
        list.forEach(entry -> displayedTasks.put(entry.getKey(), entry.getValue()));
    }

    public LinkedHashMap<Integer, String> getGridInfoCase() {
        return gridInfosCase;
    }

    public void putInGridInfoCase(Integer Row, String id) {
        gridInfosCase.put(Row, id);
    }


}
