package presentation.GetTasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetTasksModel {
    Map<String , String> tasks;

    public GetTasksModel() {
        super();
        tasks = new HashMap<>();
    }

    public GetTasksModel(Map<String, String> map) {
        this.tasks = map;
    }

    public Map<String , String> getSelectedTasksMap() {
        return tasks;
    }

    public void setSelectecTasks(Map<String , String> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String id, String title) {
        tasks.put(id, title);
    }

    public List<String>  getTaskList() {
        return (List<String>) tasks.values();
    }
    
    
}
