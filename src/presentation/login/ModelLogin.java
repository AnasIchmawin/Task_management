package presentation.login;

import metier.service ;
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
    public boolean isValidEmailAddress() {
        try {
            return service.isValidEmailAddress(this.gmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
