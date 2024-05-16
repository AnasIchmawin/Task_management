package mygroup.presentation.GetTaskFromCalendar;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableViewPane extends VBox {
    @SuppressWarnings("unused")
    private ObservableList<Item> data;

    @SuppressWarnings("unchecked")
    public TableViewPane(ObservableList<Item> data) {
        this.data = data;

        TableView<Item> table = new TableView<>();
        table.setEditable(true);

        TableColumn<Item, Boolean> checkBoxCol = new TableColumn<>("Checkbox");
        checkBoxCol.setCellValueFactory(new PropertyValueFactory<>("selected"));
        checkBoxCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxCol));
        checkBoxCol.setPrefWidth(70);

        TableColumn<Item, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(120);

        TableColumn<Item, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setPrefWidth(150);

        TableColumn<Item, String> startDateCol = new TableColumn<>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateCol.setPrefWidth(160);

        TableColumn<Item, String> endDateCol = new TableColumn<>("End Date");
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endDateCol.setPrefWidth(160);

        table.setItems(data);
        table.getColumns().addAll(checkBoxCol, titleCol, descriptionCol, startDateCol, endDateCol);

        // Add listener to each item's selected property
        for (Item item : data) {
            item.selectedProperty().addListener((observable, oldValue, newValue) -> {
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
