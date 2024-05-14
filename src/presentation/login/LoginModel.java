package presentation.login;

import metier.LoginService;

public class LoginModel {

    private String gmail;

    public LoginModel(String gmail) {
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
            return LoginService.isValidEmailAddress(this.gmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
