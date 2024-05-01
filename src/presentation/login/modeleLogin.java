package presentation.login;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import metier.service;

public class modeleLogin {

    private String gmail;

    public modeleLogin(String gmail) {
        this.gmail = gmail;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public static boolean isValidEmailAddress(String email) throws IOException, ParseException {
        return service.isValidEmailAddress(email);
    }
}
