package mygroup.persistence.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import mygroup.persistence.DBConnection;

public class DAOProjet {

    // Create
    public void create(String titre, String description, String categorie, String type, String dateDebut,
            String dateFin, List<Document> taches, List<Document> documents, List<Document> seances) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            Document doc = new Document("titre", titre).append("description", description)
                    .append("categorie", categorie)
                    .append("type", type).append("dateDebut", dateDebut).append("dateFin", dateFin)
                    .append("taches", taches)
                    .append("documents", documents)
                    .append("seances", seances);
            collection.insertOne(doc);
            System.out.println("Projet créé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du projet : " + e.getMessage());
        }
    }

    // Read
    public Document read(String id) {
        Document projet = null;
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            ObjectId objId = new ObjectId(id);
            projet = collection.find(Filters.eq("_id", objId)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du projet : " + e.getMessage());
        }
        return projet;
    }

    // Update
    public void update(String id, String titre, String description, List<Document> taches) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            Document doc = new Document();
            if (titre != null) {
                doc.append("titre", titre);
            }
            if (description != null) {
                doc.append("description", description);
            }
            if (taches != null) {
                doc.append("taches", taches);
            }
            collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }
    }

    // Delete
    public void delete(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");
            ObjectId objId = new ObjectId(id);
            collection.deleteOne(Filters.eq("_id", objId));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la projet : " + e.getMessage());
        }
    }

    public String getProjetTitle(String id) {
        try {
                MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                .getCollection("projets");
                ObjectId objId = new ObjectId(id);
                Document Projet = collection.find(Filters.eq("_id", objId)).first();
                if (Projet != null) {
                        return Projet.getString("titre");
                } else {
                        System.err.println("Projet non trouvée avec l'ID: " + id);
                }
        } catch (Exception e) {
                System.err.println("Erreur lors de la récupération du titre de la Projet : " + e.getMessage());
        }
        return null;
}

public String getStartDate(String id) {
    try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("projets");
            ObjectId objId = new ObjectId(id);
            Document Projet = collection.find(Filters.eq("_id", objId)).first();
            if (Projet != null) {
                    return Projet.getString("dateDebut");
            } else {
                    System.err.println("Projet non trouvée avec l'ID: " + id);
            }
    } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de la date du Debut de la Projet : " + e.getMessage());
    }
    return null;
}

public String getEndDate(String id) {
    try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("projets");
            ObjectId objId = new ObjectId(id);
            Document Projet = collection.find(Filters.eq("_id", objId)).first();
            if (Projet != null) {
                    return Projet.getString("dateFin");
            } else {
                    System.err.println("Projet non trouvée avec l'ID: " + id);
            }
    } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de la date du fin de la Projet : " + e.getMessage());
    }
    return null;
}
 
public String getCategory(String id) {
    try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("projets");
            ObjectId objId = new ObjectId(id);
            Document Projet = collection.find(Filters.eq("_id", objId)).first();
            if (Projet != null) {
                    return Projet.getString("categorie");
            } else {
                    System.err.println("Projet non trouvée avec l'ID: " + id);
            }
    } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de la categorie du Projet : " + e.getMessage());
    }
    return null;
}

public String getType(String id) {
    try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("projets");
            ObjectId objId = new ObjectId(id);
            Document Projet = collection.find(Filters.eq("_id", objId)).first();
            if (Projet != null) {
                    return Projet.getString("type");
            } else {
                    System.err.println("Projet non trouvée avec l'ID: " + id);
            }
    } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du type du Projet : " + e.getMessage());
    }
    return null;
}

    public LinkedHashMap<String,Boolean> getTaches(String projetId) {
        LinkedHashMap<String,Boolean> taches = new LinkedHashMap<>();
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            ObjectId objId = new ObjectId(projetId);
            Document projet = collection.find(Filters.eq("_id", objId)).first();
            if (projet != null) {
                @SuppressWarnings("unchecked")
                List<Document> tachesList = (List<Document>) projet.get("taches");
                if (tachesList != null) {
                    for (Document tache : tachesList) {
                        taches.put(tache.getString("Id"), tache.getBoolean("checked"));
                    }
                }
            } else {
                System.err.println("Projet non trouvée avec l'ID: " + projetId);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des taches du projet : " + e.getMessage());
        }
        return taches;
    }

    public void setTacheToProjet(String ProjetId, String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");
            
            // Convert projetId to ObjectId
            ObjectId objectId = new ObjectId(ProjetId);
            
            Document projet = collection.find(Filters.eq("_id", objectId)).first();
            if (projet != null) {
                @SuppressWarnings("unchecked")
                List<Document> taches = (List<Document>) projet.get("taches");
                if (taches != null) {
                    Document tache = new Document();
                    tache.append("id", tacheId);
                    taches.add(tache);
                    collection.updateOne(Filters.eq("_id", objectId), new Document("$set", new Document("taches", taches)));
                }
            } else {
                System.err.println("List not found with ID: " + ProjetId);
            }
        } catch (Exception e) {
            System.err.println("Error adding task to the project: " + e.getMessage());
        }
    }

public String getProjetDescription(String id) {
        try {
                MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                .getCollection("projets");
                ObjectId objId = new ObjectId(id);
                Document Projet = collection.find(Filters.eq("_id", objId)).first();
                if (Projet != null) {
                        return Projet.getString("description");
                } else {
                        System.err.println("Projet non trouvée avec l'ID: " + id);
                }
        } catch (Exception e) {
                System.err.println("Erreur lors de la récupération de la description de la Projet : " + e.getMessage());
        }
        return null;
}

public void deleteTacheFromProjet(String listId, String tacheId) {
    try {
        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                .getCollection("projets");
        
        // Convert listId to ObjectId
        ObjectId objectId = new ObjectId(listId);
        
        Document projet = collection.find(Filters.eq("_id", objectId)).first();
        if (projet != null) {
            @SuppressWarnings("unchecked")
            List<Document> taches = (List<Document>) projet.get("taches");
            if (taches != null) {
                for (Document tache : taches) {
                    if (tache.getString("id").equals(tacheId)) {
                        taches.remove(tache);
                        break;
                    }
                }
                collection.updateOne(Filters.eq("_id", objectId), new Document("$set", new Document("taches", taches)));
            }
        } else {
            System.err.println("List not found with ID: " + listId);
        }
    } catch (Exception e) {
        System.err.println("Error deleting task from the project: " + e.getMessage());
    }
}

    public List<Document> getAllProjects() {
        List<Document> allProjets = new ArrayList<>();
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            FindIterable<Document> cursor = collection.find();

            for (Document document : cursor) {
                allProjets.add(document);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de la liste des projets : " + e.getMessage());
        }
        return allProjets;
    }

    public Document getLastProjet() {
        try {
                MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                .getCollection("projets");
                return collection.find().sort(new Document("_id", -1)).first();
        } catch (Exception e) {
                System.err.println("Erreur lors de la récupération du dernier projet : " + e.getMessage());
                return null;
        }
    }

    public LinkedHashMap<String, String> getArchivedProjects() {
        LinkedHashMap<String, String> archivedProjects = new LinkedHashMap<>();
        String currentDate = java.time.LocalDate.now().toString() + " "
                + java.time.LocalTime.now().toString().substring(0, 5);
        List<Document> allProjets = getAllProjects();
        for (Document projet : allProjets) {
            String dateFin = projet.getString("dateFin");
            if (dateFin.compareTo(currentDate) < 0) {
                String id = projet.getObjectId("_id").toString();
                String titre = projet.getString("titre");
                archivedProjects.put(id, titre);
            }
        }
        return archivedProjects;
    }

    public String getLastProjetId() {
        String lastProjetId = null;
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            FindIterable<Document> cursor = collection.find().sort(new Document("_id", -1)).limit(1);
            for (Document document : cursor) {
                lastProjetId = document.getObjectId("_id").toString();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du dernier projet : " + e.getMessage());
        }
        return lastProjetId;
    }

    public List<String> getSeances(String selectedProjetId) {
        List<String> seances = new ArrayList<>();
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            ObjectId objId = new ObjectId(selectedProjetId);
            Document projet = collection.find(Filters.eq("_id", objId)).first();
            if (projet != null) {
                @SuppressWarnings("unchecked")
                List<Document> seancesList = (List<Document>) projet.get("seances");
                if (seancesList != null) {
                    for (Document seance : seancesList) {
                        seances.add(seance.getString("id"));
                    }
                }
            } else {
                System.err.println("Projet non trouvée avec l'ID: " + selectedProjetId);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des seances du projet : " + e.getMessage());
        }
        return seances;
    }
}