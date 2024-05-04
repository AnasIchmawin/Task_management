package presentation.login;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import metier.service;

public class ModelLogin {

    private String gmail;

    public ModelLogin(String gmail) {
        this.gmail = gmail;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    // methode de verification :
    public  boolean isValidEmailAddress() throws IOException, ParseException {
        return service.isValidEmailAddress(this.gmail);
    }
}
