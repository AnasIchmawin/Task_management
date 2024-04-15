package presentation.document_ajoute;

import javafx.scene.control.TextArea;

public class DocumentFormcontroleur {
	
	
	public static void handleEditerButton(TextArea ZoneDescription){
        ZoneDescription.setEditable(true);

    };
    
	public static void handleSaveButton(TextArea ZoneDescription){
        ZoneDescription.setEditable(false);
 
        
     
    };


}
