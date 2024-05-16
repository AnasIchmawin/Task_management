package mygroup.presentation.GetSeances;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetSeancesModel {
    LinkedHashMap<String, String> tasks;

    public GetSeancesModel() {
        super();
        tasks = new LinkedHashMap<>();
    }

    public GetSeancesModel(LinkedHashMap<String, String> map) {
        this.tasks = map;
    }

    public Map<String, String> getSelectedTasksMap() {
        return tasks;
    }

    public void setSelectecTasks(LinkedHashMap<String, String> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String id, String title) {
        tasks.put(id, title);
    }

    public List<String> getTaskList() {
        return (List<String>) tasks.values();
    }

}