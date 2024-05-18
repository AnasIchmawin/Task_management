package mygroup.persistence.DAO ;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import mygroup.persistence.DBConnection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DAOListe {

        // Create
        public void create(String Titre, String description, List<String> list) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");

                        // Ajouter les attributs du document
                        Document doc = new Document();
                        doc.append("titre", Titre)
                                        .append("description", description);
                        // covenvertit list to document
                        if(list != null){
                            List<Document> taches = new ArrayList<>();
                            for (String id_tache : list) {
                                    Document tacheDoc = new Document();
                                    tacheDoc.append("id", id_tache);
                                    taches.add(tacheDoc);
                            }
                            doc.append("taches", taches);
                        }
                        else{
                            doc.append("taches", null);
                        }
                        collection.insertOne(doc);
                } catch (Exception e) {
                        System.err.println("Erreur lors de la création de la liste : " + e.getMessage());
                }
        }

        // Read
        public Document read(String id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        return collection.find(Filters.eq("id", id)).first();
                } catch (Exception e) {
                        System.err.println("Erreur lors de la lecture de la liste : " + e.getMessage());
                        return null;
                }
        }

        // Update
        public void update(String id, String Titre, String description, List<Document> taches) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        Document doc = new Document();
                        if (Titre != null) {
                                doc.append("titre", Titre);
                        }
                        if (description != null) {
                                doc.append("description", description);
                        }
                        if (taches != null) {
                                doc.append("taches", taches);
                        }
                        collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
                } catch (Exception e) {
                        System.err.println("Erreur lors de la mise à jour de la liste : " + e.getMessage());
                }
        }

        // Delete
        public void delete(String id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                         ObjectId objId = new ObjectId(id);
                        collection.deleteOne(Filters.eq("_id", objId));
                } catch (Exception e) {
                        System.err.println("Erreur lors de la suppression de la liste : " + e.getMessage());
                }
        }

        public List<Document> getAllLists() {
                List<Document> allLists = new ArrayList<>();
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        FindIterable<Document> cursor = collection.find();

                        for (Document document : cursor) {
                                allLists.add(document);
                        }
                } catch (Exception e) {
                        System.err.println("Erreur lors de la récupération de toutes les listes : " + e.getMessage());
                }
                return allLists;
        }

        public LinkedHashMap<String,Boolean> getTaches(String listeId) {
                LinkedHashMap<String,Boolean> taches = new LinkedHashMap<>();
                try {
                    MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("listes");
                    
                    // Convert listeId to ObjectId
                    ObjectId objectId = new ObjectId(listeId);
                    
                    Document liste = collection.find(Filters.eq("_id", objectId)).first();
                    if (liste != null) {
                        @SuppressWarnings("unchecked")
                        List<Document> tachesList = (List<Document>) liste.get("taches");
                        if (tachesList != null) {
                            for (Document tache : tachesList) {
                                taches.put(tache.getString("id"), tache.getBoolean("checked"));
                            }
                        }
                    } else {
                        System.err.println("Liste not found with ID: " + listeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error retrieving tasks from the list: " + e.getMessage());
                }
                return taches;
            }

        public String getListTitle(String id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        ObjectId objId = new ObjectId(id);
                        Document liste = collection.find(Filters.eq("_id", objId)).first();
                        if (liste != null) {
                                return liste.getString("titre");
                        } else {
                                System.err.println("Liste non trouvée avec l'ID: " + id);
                        }
                } catch (Exception e) {
                        System.err.println("Erreur lors de la récupération du titre de la liste : " + e.getMessage());
                }
                return null;
        }

        public String getListDescription(String id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        ObjectId objId = new ObjectId(id);
                        Document liste = collection.find(Filters.eq("_id", objId)).first();
                        if (liste != null) {
                                return liste.getString("description");
                        } else {
                                System.err.println("Liste non trouvée avec l'ID: " + id);
                        }
                } catch (Exception e) {
                        System.err.println("Erreur lors de la récupération de la description de la liste : " + e.getMessage());
                }
                return null;
        }

        public void setTacheToliste(String listeId, String tacheId) {
                try {
                    MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("listes");
                    
                    // Convert listeId to ObjectId
                    ObjectId objectId = new ObjectId(listeId);
                    
                    Document liste = collection.find(Filters.eq("_id", objectId)).first();
                    if (liste != null) {
                        @SuppressWarnings("unchecked")
                        List<Document> taches = (List<Document>) liste.get("taches");
                        if (taches != null) {
                            Document tache = new Document();
                            tache.append("id", tacheId);
                            taches.add(tache);
                            collection.updateOne(Filters.eq("_id", objectId), new Document("$set", new Document("taches", taches)));
                        }
                    } else {
                        System.err.println("List not found with ID: " + listeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error adding task to the list: " + e.getMessage());
                }
            }

        public void deleteTacheFromListe(String listId, String tacheId) {
                try {
                    MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("listes");
                    
                    // Convert listId to ObjectId
                    ObjectId objectId = new ObjectId(listId);
                    
                    Document liste = collection.find(Filters.eq("_id", objectId)).first();
                    if (liste != null) {
                        @SuppressWarnings("unchecked")
                        List<Document> taches = (List<Document>) liste.get("taches");
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
                    System.err.println("Error deleting task from the list: " + e.getMessage());
                }
        }

        public Document getLastList() {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        return collection.find().sort(new Document("_id", -1)).first();
                } catch (Exception e) {
                        System.err.println("Erreur lors de la récupération de la dernière liste : " + e.getMessage());
                        return null;
                }
        }

        public String getLastListId() {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        Document lastList = collection.find().sort(new Document("_id", -1)).first();
                        if (lastList != null) {
                                return lastList.getObjectId("_id").toString();
                        } else {
                                System.err.println("Aucune liste trouvée");
                        }
                } catch (Exception e) {
                        System.err.println("Erreur lors de la récupération de l'ID de la dernière liste : " + e.getMessage());
                }
                return null;
        }
}