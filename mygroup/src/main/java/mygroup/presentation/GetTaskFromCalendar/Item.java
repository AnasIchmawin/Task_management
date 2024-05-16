package mygroup.presentation.GetTaskFromCalendar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleStringProperty title;
    private final SimpleStringProperty description;
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty endDate;
    private final BooleanProperty selected;

    public Item(boolean selected, String title, String description, String startDate, String endDate) {
        this.selected = selected ? new SimpleBooleanProperty(true) : new SimpleBooleanProperty(false);
        this.title = title == null ? new SimpleStringProperty("") : new SimpleStringProperty(title);
        this.description = description == null ? new SimpleStringProperty("") : new SimpleStringProperty(description);
        this.startDate = startDate == null ? new SimpleStringProperty("") : new SimpleStringProperty(startDate);
        this.endDate = endDate == null ? new SimpleStringProperty("") : new SimpleStringProperty(endDate);
    }

    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

    public String getEndDate() {
        return endDate.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
