package mygroup.presentation.statistiques;

import java.util.Map;

public class StatistiquesModel {
    // Replace these methods with actual DAO methods
    public Map<String, Integer> getHoursByProject() {
        // Implement actual data fetching logic
        return Map.of("Project A", 40, "Project B", 35);
    }

    public Map<String, Integer> getDocumentsByProject() {
        // Implement actual data fetching logic
        return Map.of("Project A", 10, "Project B", 8);
    }

    public Map<String, Integer> getHoursPerWeek() {
        // Implement actual data fetching logic
        return Map.of("Week 1", 20, "Week 2", 25);
    }

    public Map<String, Integer> getHoursPerMonth() {
        // Implement actual data fetching logic
        return Map.of("January", 100, "February", 110);
    }

    public Map<String, Integer> getHoursPerYear() {
        // Implement actual data fetching logic
        return Map.of("2023", 1200, "2024", 1300);
    }

    public Map<String, Double> getHoursByCategory() {
        // Implement actual data fetching logic
        return Map.of("Development", 60.0, "Testing", 40.0);
    }
}
