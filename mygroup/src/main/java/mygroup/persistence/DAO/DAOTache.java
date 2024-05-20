package mygroup.persistence.DAO;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import mygroup.persistence.DBConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

//mod
public class DAOTache {

    // Create tache
    public void create(String titre, Boolean etat, String categorie, String description, String dateDebut,
            String TempsDebut,
            String dateFin, String TempsFin, List<String> documents, String projet, String liste) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("taches");
            Document doc = new Document()
                    .append("titre", titre)
                    .append("categorie", categorie)
                    .append("description", description)
                    .append("etat", etat)
                    .append("dateDebut", dateDebut)
                    .append("TempsDebut", TempsDebut)
                    .append("dateFin", dateFin)
                    .append("TempsFin", TempsFin)
                    .append("documents", documents)
                    .append("projet", projet)
                    .append("liste", liste);
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Error creating task: " + e.getMessage());
        }
    }

    // Read
    public Document read(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            // Rechercher et retourner le document correspondant à l'ID spécifié
            ObjectId Oid = new ObjectId(id);
            return collection.find(Filters.eq("_id", Oid)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture de la tâche : " + e.getMessage());
            return null;
        }
    }

    // Update
    public void update(String titre, Boolean etat, String categorie, String description,
            String dateDebut, String tempsDebut, String dateFin,
            String tempsFin, List<String> docs, String projet, String liste) {
        try {
            // Récupérer la collection "taches"
            @SuppressWarnings("unused")
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("taches");

            // Créer les mises à jour à appliquer
            List<Document> updates = new ArrayList<>();

            // Ajouter les attributs non nuls à mettre à jour
            if (titre != null) {
                updates.add(new Document("$set", new Document("titre", titre)));
            }
            if (etat != null) {
                updates.add(new Document("$set", new Document("etat", etat)));
            }
            if (categorie != null) {
                updates.add(new Document("$set", new Document("categorie", categorie)));
            }
            if (description != null) {
                updates.add(new Document("$set", new Document("description", description)));
            }
            if (dateDebut != null) {
                updates.add(new Document("$set", new Document("dateDebut", dateDebut)));
            }
            if (tempsDebut != null) {
                updates.add(new Document("$set", new Document("HeureDebut", tempsDebut)));
            }
            if (dateFin != null) {
                updates.add(new Document("$set", new Document("dateFin", dateFin)));
            }
            if (tempsFin != null) {
                updates.add(new Document("$set", new Document("HeureDebut", tempsFin)));
            }
            if (docs != null) {
                updates.add(new Document("$set", new Document("documents", docs)));
            }
            if (projet != null) {
                updates.add(new Document("$set", new Document("projet", projet)));
            }
            if (liste != null) {
                updates.add(new Document("$set", new Document("liste", liste)));
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de la tâche : " + e.getMessage());
        }
    }

    public void updateListId(String task_Id, String list_Id) {
        try {
            ObjectId taskObjectId = new ObjectId(task_Id);

            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");

            Document updateDoc = new Document("$set", new Document("liste", list_Id));

            collection.updateOne(Filters.eq("_id", taskObjectId), updateDoc);

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid ObjectId format: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error updating the list: " + e.getMessage());
        }
    }

    public void updateTask(String title, String description, String taskId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");

            // Convert taskId to ObjectId
            ObjectId objectId = new ObjectId(taskId);

            Document tache = collection.find(Filters.eq("_id", objectId)).first();
            if (tache != null) {
                Document doc = new Document();
                if (title != null) {
                    doc.append("titre", title);
                }
                if (description != null) {
                    doc.append("description", description);
                }
                collection.updateOne(Filters.eq("_id", objectId), new Document("$set", doc));
            } else {
                System.err.println("Task not found with ID: " + taskId);
            }
        } catch (Exception e) {
            System.err.println("Error updating the task: " + e.getMessage());
        }
    }

    // Delete
    public void delete(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            // Convertir tacheId en ObjectId
            ObjectId objectId = new ObjectId(tacheId);
            // Supprimer la tâche correspondante à l'ID spécifié
            collection.deleteOne(Filters.eq("_id", objectId));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la tâche : " + e.getMessage());
        }
    }

    // GetAllTache return Map value and titre :
    public List<Document> getAllTaches() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            FindIterable<Document> iterDoc = collection.find();
            List<Document> taches = new ArrayList<>();
            for (Document doc : iterDoc) {
                taches.add(doc);
            }
            return taches;
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des tâches au niveau de Dao: " + e.getMessage());
            return null;
        }
    }

    // GetEtat
    public Boolean getEtat(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");

            // Convert tacheId to ObjectId
            ObjectId objectId = new ObjectId(tacheId);

            Document tache = collection.find(Filters.eq("_id", objectId)).first();
            if (tache != null) {
                return tache.getBoolean("etat");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving the state of the task: " + e.getMessage());
            return null;
        }
    }

    // SetEtat
    public void setEtat(String tacheId, Boolean etat) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");

            // Convert tacheId to ObjectId
            ObjectId objectId = new ObjectId(tacheId);

            collection.updateOne(Filters.eq("_id", objectId), Updates.set("etat", etat));
        } catch (Exception e) {
            System.err.println("Error setting the state of the task: " + e.getMessage());
        }
    }

    // get date debut
    public String getDateDebut(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(tacheId))).first();
            if (tache != null) {
                return tache.getString("dateDebut");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the start date of the task: " + e.getMessage());
            return null;
        }
    }

    // get titre
    public String getTitre(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(tacheId))).first();
            if (tache != null) {
                return tache.getString("titre");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the title of the task: " + e.getMessage());
            return null;
        }
    }

    // get date fin
    public String getDateFin(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(tacheId))).first();
            if (tache != null) {
                return tache.getString("dateFin");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the end date of the task: " + e.getMessage());
            return null;
        }
    }

    // get categorie
    public String getCategorie(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(tacheId))).first();
            if (tache != null) {
                return tache.getString("categorie");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the category of the task: " + e.getMessage());
            return null;
        }
    }

    // get description
    public String getDescription(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(tacheId))).first();
            if (tache != null) {
                return tache.getString("description");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the description of the task: " + e.getMessage());
            return null;
        }
    }

    // get type
    public String getType(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(tacheId))).first();
            if (tache != null) {
                return tache.getString("type");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the type of the task: " + e.getMessage());
            return null;
        }
    }

    // getLastTacheId
    public String getLastTacheId() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document lastTache = collection.find().sort(new Document("_id", -1)).first();
            if (lastTache != null) {
                return lastTache.getObjectId("_id").toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the last task ID: " + e.getMessage());
            return null;
        }
    }

    public void cloneTask(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = read(tacheId);
            if (tache != null) {
                Document clone = new Document(tache);
                clone.remove("_id");
                // change the title of the cloned task
                clone.append("titre", tache.getString("titre"));
                collection.insertOne(clone);
            } else {
                System.err.println("Task not found with ID: " + tacheId);
            }
        } catch (Exception e) {
            System.err.println("Error cloning the task: " + e.getMessage());
        }
    }

    public void setProjetId(List<String> listTaskIds, String lastProjetId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            for (String taskId : listTaskIds) {
                collection.updateOne(Filters.eq("_id", new ObjectId(taskId)), Updates.set("projet", lastProjetId));
            }
        } catch (Exception e) {
            System.err.println("Error setting the project ID for the tasks: " + e.getMessage());
        }
    }

    public void addDocIdToTask(String idTache, String idLastDoc) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            ObjectId id = new ObjectId(idTache);
            Document Tache = collection.find(Filters.eq("_id", id)).first();
            if (Tache != null) {
                @SuppressWarnings("unchecked")
                List<String> docs = (List<String>) Tache.get("documents");
                if (docs != null) {
                    docs.add(idLastDoc);
                }
                collection.updateOne(Filters.eq("_id", id),
                        new Document("$set", new Document("documents", docs)));
            }
        } catch (Exception e) {
            System.err.println("Error adding the document ID to the task: " + e.getMessage());
        }
    }

    public LinkedHashMap<String, ArrayList<String>> getDocuments(String taskSelectedId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document tache = collection.find(Filters.eq("_id", new ObjectId(taskSelectedId))).first();
            if (tache != null) {
                @SuppressWarnings("unchecked")
                List<String> docs = (List<String>) tache.get("documents");
                LinkedHashMap<String, ArrayList<String>> documents = new LinkedHashMap<>();
                for (String docId : docs) {
                    Document doc = DBConnection.getInstance().getDatabase().getCollection("documents")
                            .find(Filters.eq("_id", new ObjectId(docId))).first();
                    if (doc != null) {
                        ArrayList<String> docInfo = new ArrayList<>();
                        docInfo.add(doc.getString("titre"));
                        docInfo.add(doc.getString("description"));
                        docInfo.add(doc.getString("path"));
                        documents.put(docId, docInfo);
                    }
                }
                return documents;
            } else {
                System.err.println("Task not found with ID: " + taskSelectedId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error getting the documents of the task: " + e.getMessage());
            return null;
        }
    }

    public String getLastIdTache() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            Document lastTache = collection.find().sort(new Document("_id", -1)).first();
            if (lastTache != null) {
                return lastTache.getObjectId("_id").toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            System.err.println("Error getting the last task ID: " + e.getMessage());
            return "null";
        }
    }

}