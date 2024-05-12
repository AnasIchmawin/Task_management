// TableViewPane.java
package presentation.GetTaskFromCalendar;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class TableViewPane extends VBox {
    private ObservableList<Item> data;

    @SuppressWarnings("unchecked")
    public TableViewPane(ObservableList<Item> data) {
        this.data = data;  // Use the provided data

        TableView<Item> table = new TableView<Item>();
        table.setEditable(true);

        TableColumn<Item, Boolean> checkBoxCol = new TableColumn<Item, Boolean>("Checkbox");
        checkBoxCol.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("selected"));
        checkBoxCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxCol));
        checkBoxCol.setPrefWidth(70);

        TableColumn<Item, String> titleCol = new TableColumn<Item, String>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        titleCol.setPrefWidth(120);

        TableColumn<Item, String> descriptionCol = new TableColumn<Item, String>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        descriptionCol.setPrefWidth(150);

        TableColumn<Item, String> startDateCol = new TableColumn<Item, String>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<Item, String>("startDate"));
        startDateCol.setPrefWidth(160);

        TableColumn<Item, String> endDateCol = new TableColumn<Item, String>("End Date");
        endDateCol.setCellValueFactory(new PropertyValueFactory<Item, String>("endDate"));
        endDateCol.setPrefWidth(160);

        table.setItems(data);
        table.getColumns().addAll(checkBoxCol, titleCol, descriptionCol, startDateCol, endDateCol);

        getChildren().add(table);
    }
}
