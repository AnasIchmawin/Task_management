package mygroup.presentation.GetTaskFromCalendar;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableViewPane extends VBox {
    @SuppressWarnings("unused")
    private ObservableList<ItemTask> data;

    @SuppressWarnings("unchecked")
    public TableViewPane(ObservableList<ItemTask> data) {
        this.data = data;

        TableView<ItemTask> table = new TableView<>();
        table.setEditable(true);

        TableColumn<ItemTask, Boolean> checkBoxCol = new TableColumn<>("Checkbox");
        checkBoxCol.setCellValueFactory(new PropertyValueFactory<>("selected"));
        checkBoxCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxCol));
        checkBoxCol.setPrefWidth(90);

        TableColumn<ItemTask, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(110);

        TableColumn<ItemTask, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setPrefWidth(230);

        TableColumn<ItemTask, String> startDateCol = new TableColumn<>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateCol.setPrefWidth(150);


        table.setItems(data);
        table.getColumns().addAll(checkBoxCol, titleCol, descriptionCol, startDateCol);

        // Add listener to each ItemTask's selected property
        for (ItemTask ItemTask : data) {
            ItemTask.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    System.out.println("La case à cocher est sélectionnée");
                } else {
                    System.out.println("La case à cocher n'est pas sélectionnée");
                }
            });
        }

        getChildren().add(table);
    }
}
