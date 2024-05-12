// Item.java
package presentation.GetTaskFromCalendar;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty title;
    private final SimpleStringProperty description;
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty endDate;

    public Item(Boolean selected, String title, String description, String startDate, String endDate) {
        this.selected = new SimpleBooleanProperty(selected);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
    }

    public Boolean getSelected() {
        return selected.get();
    }

    public void setSelected(Boolean selected) {
        this.selected.set(selected);
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
}
