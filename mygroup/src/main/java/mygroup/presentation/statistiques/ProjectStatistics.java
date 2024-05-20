package mygroup.presentation.statistiques;

import java.util.List;
import org.bson.Document;
import mygroup.metier.Gestionnaire.GestionnaireProjet;

public class ProjectStatistics {

    private GestionnaireProjet gestionnaireProjet;

    public ProjectStatistics() {
        gestionnaireProjet = new GestionnaireProjet();
    }

    public int calculateTotalWorkHours() {
        int totalWorkHours = 0;
        List<Document> allProjects = gestionnaireProjet.obtenirToutesLesProjets();
        for (Document project : allProjects) {
            String projectId = project.getObjectId("_id").toString();
            totalWorkHours += gestionnaireProjet.calculerHeuresTravail(projectId);
        }
        return totalWorkHours;
    }

    public int getTotalWorkHoursForCategory(String category) {
        int totalWorkHours = 0;
        List<Document> allProjects = gestionnaireProjet.obtenirToutesLesProjets();
        for (Document project : allProjects) {
            String projectId = project.getObjectId("_id").toString();
            String projectCategory = gestionnaireProjet.getCategory(projectId);
            if (projectCategory.equals(category)) {
                totalWorkHours += gestionnaireProjet.calculerHeuresTravail(projectId);
            }
        }
        return totalWorkHours;
    }

    public int getTotalWorkHoursForType(String type) {
        int totalWorkHours = 0;
        List<Document> allProjects = gestionnaireProjet.obtenirToutesLesProjets();
        for (Document project : allProjects) {
            String projectId = project.getObjectId("_id").toString();
            String projectType = gestionnaireProjet.getType(projectId);
            if (projectType.equals(type)) {
                totalWorkHours += gestionnaireProjet.calculerHeuresTravail(projectId);
            }
        }
        return totalWorkHours;
    }

    public String getProjetTitle(String projetId) {
        return gestionnaireProjet.getProjetTitle(projetId);
    }

    public int getNumberOfDocumentsPerProject(String projectId) {
        return gestionnaireProjet.NmbrDocumentParProjet(projectId);
    }

    public double calculatePercentageOfWorkHours(int totalWorkHours, int totalHours) {
        return (double) totalWorkHours / totalHours * 100;
    }

    public int calculerHeuresTravail(String projectId) {
        return gestionnaireProjet.calculerHeuresTravail(projectId);
    }
    
    public List<Document> getAllProjects() {
        return gestionnaireProjet.obtenirToutesLesProjets();
    }

}