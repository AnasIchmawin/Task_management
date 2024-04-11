package presentation.seance;
import javafx.scene.control.TextArea;


public class SceanceFormController {

    public static void handleEditerButton(TextArea ZoneDescription){
            ZoneDescription.setEditable(true);

        };

    public static void handleSaveButton(TextArea ZoneDescription , TextArea ZoneNote){
            ZoneDescription.setEditable(false);
            ZoneNote.setEditable(false);
        };
    
}
