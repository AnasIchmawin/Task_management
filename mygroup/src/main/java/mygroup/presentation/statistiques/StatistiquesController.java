package mygroup.presentation.statistiques;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;

public class StatistiquesController {
    private final StatistiquesView view;
    private final StatistiquesModel model;

    public StatistiquesController(StatistiquesView view, StatistiquesModel model) {
        this.view = view;
        this.model = model;
        updateView();
    }

    private void updateView() {
        updateHoursByProjectChart();
        updateDocumentsByProjectLabel();
        updateHoursPerWeekChart();
        updateHoursPerMonthChart();
        updateHoursPerYearChart();
        updateHoursByCategoryChart();
    }

    private void updateHoursByProjectChart() {
        ObservableList<Data> pieChartData = FXCollections.observableArrayList();
        model.getHoursByProject().forEach((project, hours) ->
            pieChartData.add(new Data(project, hours))
        );
        view.getHoursByProjectChart().setData(pieChartData);
    }

    private void updateDocumentsByProjectLabel() {
        StringBuilder labelContent = new StringBuilder("Documents by Project:\n");
        model.getDocumentsByProject().forEach((project, count) ->
            labelContent.append(project).append(": ").append(count).append("\n")
        );
        view.getDocumentsByProjectLabel().setText(labelContent.toString());
    }

    private void updateHoursPerWeekChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weekly Hours");
        model.getHoursPerWeek().forEach((week, hours) ->
            series.getData().add(new XYChart.Data<>(week, hours))
        );
        view.getHoursPerWeekChart().getData().add(series);
    }

    private void updateHoursPerMonthChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Hours");
        model.getHoursPerMonth().forEach((month, hours) ->
            series.getData().add(new XYChart.Data<>(month, hours))
        );
        view.getHoursPerMonthChart().getData().add(series);
    }

    private void updateHoursPerYearChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Yearly Hours");
        model.getHoursPerYear().forEach((year, hours) ->
            series.getData().add(new XYChart.Data<>(year, hours))
        );
        view.getHoursPerYearChart().getData().add(series);
    }

    private void updateHoursByCategoryChart() {
        ObservableList<Data> pieChartData = FXCollections.observableArrayList();
        model.getHoursByCategory().forEach((category, percentage) ->
            pieChartData.add(new Data(category, percentage))
        );
        view.getHoursByCategoryChart().setData(pieChartData);
    }
}
