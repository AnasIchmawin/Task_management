package presentation.login;


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

    public static boolean CheckEmail(String email) {
        // Necessaites une implementation de la methode CheckEmail
        return true;
    }
}
