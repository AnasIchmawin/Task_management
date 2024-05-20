package mygroup.presentation.statistiques;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.List;

public class ProjectStatisticsView extends Application {

    private ProjectStatistics projectStatistics;
    private Button listesButton;
    private Button projectsButton;
    private Button archiveButton;
    private HBox buttonsBar;
    private GridPane zonestats;
    private BorderPane root;
    private ScrollPane scrollPane;
    private VBox mainContentContainer;
    private StackPane container;
    private HBox navbar;
    private VBox navbarContainer;

    public ProjectStatisticsView() {
        initialize();
        style();
        draw();
        addActions();
    }

    @Override
    public void start(Stage primaryStage) {
        projectStatistics = new ProjectStatistics();

        // Create the table for project details
        TableView<ProjectDetails> projectTable = new TableView<>();
        projectTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ProjectDetails, String> titleColumn = new TableColumn<>("Project Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<ProjectDetails, String> workHoursColumn = new TableColumn<>("Work Hours");
        workHoursColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorkHours()));

        TableColumn<ProjectDetails, String> numDocumentsColumn = new TableColumn<>("Number of Documents");
        numDocumentsColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumDocuments()));

        TableColumn<ProjectDetails, String> numTachesColumn = new TableColumn<>("Number of Taches");
        numTachesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumTaches()));

        projectTable.getColumns().addAll(titleColumn, workHoursColumn, numDocumentsColumn, numTachesColumn);

        List<Document> allProjects = projectStatistics.getAllProjects();
        for (Document project : allProjects) {
            String projectId = project.getObjectId("_id").toString();
            String title = projectStatistics.getProjetTitle(projectId);
            String workHours = String.valueOf(projectStatistics.calculerHeuresTravail(projectId));
            String numDocuments = String.valueOf(projectStatistics.getNumberOfDocumentsPerProject(projectId));
            String numTaches = String.valueOf(projectStatistics.getNumberOftachesPerProject(projectId));
            projectTable.getItems().add(new ProjectDetails(title, workHours, numDocuments, numTaches));
        }

        // Create the table for work hours by type
        TableView<WorkPercentage> typeTable = new TableView<>();
        typeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<WorkPercentage, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategoryOrType()));

        TableColumn<WorkPercentage, String> typePercentageColumn = new TableColumn<>("Percentage");
        typePercentageColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPercentage()));

        typeTable.getColumns().addAll(typeColumn, typePercentageColumn);

        int totalWorkHours = projectStatistics.calculateTotalWorkHours();
        String[] types = {"PFE", "PFA", "Cours", "Examen", "Autre", "Thèse"};
        for (String type : types) {
            int totalWorkHoursForType = projectStatistics.getTotalWorkHoursForType(type);
            double percentageOfWorkHoursForType = projectStatistics.calculatePercentageOfWorkHours(totalWorkHoursForType, totalWorkHours);
            typeTable.getItems().add(new WorkPercentage(type, String.format("%.2f%%", percentageOfWorkHoursForType)));
        }

        // Create the table for work hours by category
        TableView<WorkPercentage> categoryTable = new TableView<>();
        categoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<WorkPercentage, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategoryOrType()));

        TableColumn<WorkPercentage, String> categoryPercentageColumn = new TableColumn<>("Percentage");
        categoryPercentageColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPercentage()));

        categoryTable.getColumns().addAll(categoryColumn, categoryPercentageColumn);

        String[] categories = {"Enseignement", "Encadrement", "Autre"};
        for (String category : categories) {
            int totalWorkHoursForCategory = projectStatistics.getTotalWorkHoursForCategory(category);
            double percentageOfWorkHoursForCategory = projectStatistics.calculatePercentageOfWorkHours(totalWorkHoursForCategory, totalWorkHours);
            categoryTable.getItems().add(new WorkPercentage(category, String.format("%.2f%%", percentageOfWorkHoursForCategory)));
        }

        VBox tablesContainer = new VBox(20, projectTable, typeTable, categoryTable);
        tablesContainer.setAlignment(Pos.CENTER);
        tablesContainer.setPadding(new Insets(10));

        mainContentContainer.getChildren().add(tablesContainer);
        mainContentContainer.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1160, 652);
        scene.getStylesheets().add(getClass().getResource("StatistiquesStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Project Statistics");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initialize() {
        listesButton = new Button("Listes");
        projectsButton = new Button("Projets");
        archiveButton = new Button("Archive");
        buttonsBar = new HBox(20, listesButton, projectsButton, archiveButton);
        navbar = new HBox(buttonsBar);
        navbarContainer = new VBox(navbar);
        mainContentContainer = new VBox();
        mainContentContainer.setSpacing(10);
        container = new StackPane();
        zonestats = createGridPane();
        scrollPane = createScrollPane(zonestats);
        root = new BorderPane();
        surveillerButton(projectsButton, "100px", "40px", "#3F72AF");
        surveillerButton(archiveButton, "100px", "40px", "#3F72AF");
    }

    private void style() {
        listesButton.getStyleClass().add("button-clicked-style");
        projectsButton.getStyleClass().add("button-style");
        archiveButton.getStyleClass().add("button-style");
        navbar.getStyleClass().add("navbar");
        navbarContainer.getStyleClass().add("navbar-container");
        container.getStyleClass().add("container");
        scrollPane.getStyleClass().add("scroll-pane");
    }

    private void draw() {
        navbar.setPadding(new Insets(10, 20, 10, 20));
        BorderPane.setMargin(navbarContainer, new Insets(0, 20, 0, 20));
        container.getChildren().addAll(mainContentContainer);
        BorderPane.setMargin(container, new Insets(20, 20, 20, 20));
        scrollPane.setPadding(new Insets(0, 50, 0, 50));
        root.setTop(navbarContainer);
        root.setCenter(container);
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(30);
        gridPane.setHgap(20);
        gridPane.setPrefHeight(350);
        gridPane.setStyle("-fx-background-color: #F0F0F0;");
        return gridPane;
    }

    private ScrollPane createScrollPane(GridPane gridPane) {
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    private void surveillerButton(Button button, String width, String height, String color) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: #8E9EB2; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: " + color + ";" +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
    }

    private void addActions() {
        projectsButton.setOnAction(event -> projectStatistics.handleProjectsButton());
        archiveButton.setOnAction(event -> projectStatistics.handleArchiveButton());
    }

    public GridPane getZonestats() {
        return zonestats;
    }

    public BorderPane getRoot() {
        return root;
    }

    // Helper class for project details
    public static class ProjectDetails {
        private final SimpleStringProperty title;
        private final SimpleStringProperty workHours;
        private final SimpleStringProperty numDocuments;
        private final SimpleStringProperty numTaches;

        public ProjectDetails(String title, String workHours, String numDocuments, String numTaches) {
            this.title = new SimpleStringProperty(title);
            this.workHours = new SimpleStringProperty(workHours);
            this.numDocuments = new SimpleStringProperty(numDocuments);
            this.numTaches = new SimpleStringProperty(numTaches);
        }

        public String getTitle() {
            return title.get();
        }

        public String getWorkHours() {
            return workHours.get();
        }

        public String getNumDocuments() {
            return numDocuments.get();
        }

        public String getNumTaches() {
            return numTaches.get();
        }
    }

    // Helper class for work percentage
    public static class WorkPercentage {
        private final SimpleStringProperty categoryOrType;
        private final SimpleStringProperty percentage;

        public WorkPercentage(String categoryOrType, String percentage) {
            this.categoryOrType = new SimpleStringProperty(categoryOrType);
            this.percentage = new SimpleStringProperty(percentage);
        }

        public String getCategoryOrType() {
            return categoryOrType.get();
        }

        public String getPercentage() {
            return percentage.get();
        }
    }
}
