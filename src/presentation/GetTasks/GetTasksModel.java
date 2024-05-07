package presentation.GetTasks;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetTasksModel {
    LinkedHashMap<String, String> tasks;

    public GetTasksModel() {
        super();
        tasks = new LinkedHashMap<>();
    }

    public GetTasksModel(LinkedHashMap<String, String> map) {
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