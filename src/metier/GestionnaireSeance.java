package metier;

import metier.Errors.NonValidList;
import persistence.DAO.DAOSeance;

public class GestionnaireSeance {
    private DAOSeance daoSeance;
    private POJOSeance seance;

    public GestionnaireSeance() {
        this.daoSeance = new DAOSeance();
        this.seance = new POJOSeance();
    }

    public DAOSeance getDaoSeance() {
        return daoSeance;
    }

    public void setDaoSeance(DAOSeance daoSeance) {
        this.daoSeance = daoSeance;
    }

    public POJOSeance getSeance() {
        return seance;
    }

    public void setSeance(POJOSeance seance) {
        this.seance = seance;
    }
}
