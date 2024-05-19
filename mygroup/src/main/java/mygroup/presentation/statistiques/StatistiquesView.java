package mygroup.presentation.statistiques;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatistiquesView {
    private final PieChart hoursByProjectChart;
    private final Label documentsByProjectLabel;
    private final LineChart<String, Number> hoursPerWeekChart;
    private final LineChart<String, Number> hoursPerMonthChart;
    private final LineChart<String, Number> hoursPerYearChart;
    private final PieChart hoursByCategoryChart;

    public StatistiquesView(Stage primaryStage) {
        primaryStage.setTitle("Statistiques");
        hoursByProjectChart = new PieChart();
        documentsByProjectLabel = new Label();
        hoursPerWeekChart = createLineChart("Hours per Week");
        hoursPerMonthChart = createLineChart("Hours per Month");
        hoursPerYearChart = createLineChart("Hours per Year");
        hoursByCategoryChart = new PieChart();

        VBox root = new VBox(20);
        root.getChildren().addAll(hoursByProjectChart, documentsByProjectLabel, hoursPerWeekChart, hoursPerMonthChart, hoursPerYearChart, hoursByCategoryChart);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("StatistiquesStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Statistiques");

        VBox root = new VBox(20);
        root.getChildren().addAll(hoursByProjectChart, documentsByProjectLabel, hoursPerWeekChart, hoursPerMonthChart, hoursPerYearChart, hoursByCategoryChart);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("StatistiquesStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private LineChart<String, Number> createLineChart(String title) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(title);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Hours");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);

        return lineChart;
    }

    public PieChart getHoursByProjectChart() {
        return hoursByProjectChart;
    }

    public Label getDocumentsByProjectLabel() {
        return documentsByProjectLabel;
    }

    public LineChart<String, Number> getHoursPerWeekChart() {
        return hoursPerWeekChart;
    }

    public LineChart<String, Number> getHoursPerMonthChart() {
        return hoursPerMonthChart;
    }

    public LineChart<String, Number> getHoursPerYearChart() {
        return hoursPerYearChart;
    }

    public PieChart getHoursByCategoryChart() {
        return hoursByCategoryChart;
    }
}
