package mygroup.presentation.statistiques;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import org.bson.Document;

public class ProjectStatisticsView extends Application {

    private ProjectStatistics projectStatistics;

    @Override
    public void start(Stage primaryStage) {
        projectStatistics = new ProjectStatistics();

        // Create Labels to display statistics
        VBox vbox = new VBox();
        List<Document> allProjects = projectStatistics.getAllProjects();
        for (Document project : allProjects) {
            String projectId = project.getObjectId("_id").toString();
            int workHours = projectStatistics.calculerHeuresTravail(projectId);
            int numberOfDocuments = projectStatistics.getNumberOfDocumentsPerProject(projectId);

            Label titleLabel = new Label("Project Title: " + projectStatistics.getProjetTitle(projectId));
            Label workHoursLabel = new Label("Work Hours: " + workHours);
            Label numberOfDocumentsLabel = new Label("Number of Documents: " + numberOfDocuments);

            vbox.getChildren().addAll(titleLabel, workHoursLabel, numberOfDocumentsLabel);
        }

        // Calculate total work hours
        int totalWorkHours = projectStatistics.calculateTotalWorkHours();
        Label totalWorkHoursLabel = new Label("Total Work Hours: " + totalWorkHours);
        vbox.getChildren().add(totalWorkHoursLabel);

        // Calculate and display percentage of work hours for each type
        String[] types = {"PFE", "PFA", "Cours","Examen", "Autre", "Thèse"}; // Example types
        for (String type : types) {
            int totalWorkHoursForType = projectStatistics.getTotalWorkHoursForType(type);
            double percentageOfWorkHoursForType = projectStatistics.calculatePercentageOfWorkHours(totalWorkHoursForType, totalWorkHours);
            Label percentageLabel = new Label("Percentage of Work Hours for " + type + ": " + percentageOfWorkHoursForType + "%");
            vbox.getChildren().add(percentageLabel);
        }

        // Calculate and display percentage of work hours for each category
        String[] categories = {"Enseignement", "Encadrement", "Autre"}; // Example categories
        for (String category : categories) {
            int totalWorkHoursForCategory = projectStatistics.getTotalWorkHoursForCategory(category);
            double percentageOfWorkHoursForCategory = projectStatistics.calculatePercentageOfWorkHours(totalWorkHoursForCategory, totalWorkHours);
            Label percentageLabel = new Label("Percentage of Work Hours for " + category + ": " + percentageOfWorkHoursForCategory + "%");
            vbox.getChildren().add(percentageLabel);
        }

        // Create Scene
        Scene scene = new Scene(vbox, 400, 600);

        // Set title and show the stage
        primaryStage.setTitle("Project Statistics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
}
