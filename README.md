<div style="text-align: center;">

# <span style="color:#fffff">TASK MANAGEMENT

### JAVA PROJECT

</div><br>

| Realisé par : | Encadré par : |
|--------------|--------------|
| - IMANI Mourad | - HAFIDI Imade |
| - ICHMAWIN Anas | |
| - MOUSSALIM Sohail | |
| - FATIH Mohamed-Amine | |

## Introduction
La gestion efficace des tâches et des projets académiques représente un défi majeur pour les professeurs. Avec la multiplicité des responsabilités, allant de l'enseignement à l'encadrement des projets de fin d'études et des thèses, il devient crucial de disposer d'un outil performant pour organiser et suivre ces activités. C’est dans ce contexte que s’inscrit notre projet : développer une application de suivi des projets et tâches académiques.

Cette application a pour vocation d’aider les professeurs à mieux gérer leurs différentes tâches en les structurant sous forme de projets et de listes de tâches. Chaque projet peut inclure diverses activités telles que des cours, des thèses, des PFE (Projets de Fin d’Études), ou encore des tâches administratives. L'application permet non seulement de planifier et de suivre ces projets, mais aussi d'importer des événements depuis Google Calendrier, offrant ainsi une intégration fluide avec les outils existants.

Les fonctionnalités principales de l'application incluent la gestion des projets et des tâches, la consultation et la modification des informations associées, la gestion des séances de travail, et la capacité de filtrer et de rechercher des projets et des tâches par différents critères.

Techniquement, cette application a été développée en Java, utilisant JavaFX pour l'interface graphique, et MongoDB pour le stockage des données. Tous les paramètres de configuration sont stockés dans un fichier de propriétés, assurant ainsi une flexibilité et une facilité d'adaptation aux besoins spécifiques des utilisateurs. L'application est conçue pour être multiplateforme, avec des fichiers d’installation disponibles pour Windows, Mac, et Linux.

En conclusion, cette application se veut être un outil complet et intuitif, capable de répondre aux besoins de gestion des tâches académiques des professeurs, tout en leur offrant des fonctionnalités avancées pour optimiser leur temps et leur efficacité.

## Les fonctionnalités de l’application
Le logiciel fonctionnera de la manière suivante :
- Il permettra à l'utilisateur de se connecter avec une adresse e-mail Google.
- Il autorisera l'utilisateur à naviguer entre les projets existants et à les ordonner/filtrer par catégorie ou type.
- Il offrira à l'utilisateur l'option de recherche en utilisant les mots-clés existants dans la description.
- Le logiciel offrira la possibilité :
    - d'ajouter un projet.
    - de clôturer un projet.
    - de cloner un projet.
    - de modifier les informations d'un projet.
- Il permettra à l'utilisateur de naviguer entre les différentes tâches.
- Il offrira l'option de :
    - création d'une tâche.
    - modification de la description.
    - ajout d'une tâche existante.
    - suppression d'une tâche.
- si l'utilisateur supprime une tâche, la tâche supprimée de la liste restera toujours existante.
- si l'utilisateur supprime une liste de tâches, les tâches dans cette liste ne seront pas supprimées.
- L'utilisateur peut consulter les tâches ordonnées par :
    - Ordre alphabetique 
    - Date (DD/MM/AAAA)
- Il va permettre à l'utilisateur l'option de recherche sur les tâches en utilisant les mots-clés existants dans la description.
- Il va permettre à l'utilisateur d'ajouter, cloner ou modifier les informations des tâches.
- Il va permettre à l'utilisateur d'importer les tâches de son Google calendrier. Les tâches importées peuvent être attribuées à un projet, et cela s'applique également aux séances. De plus, les tâches peuvent être créées avec les séances.
- Le logiciel va permettre la gestion des séances en offrant les options suivantes:
    - Consultation des séances.
    - Ajout des séances.
    - Modification des séances (modifier les informations telles que la date, la description, insérer des documents).
    - Suppression des séances.
- Le logiciel offre l'option de recherche par les mots-clés existants dans les documents attachés aux projets.
- Le logiciel contient un tableau de bord affichant les différentes statistiques de l'utilisateur, telles que le nombre d'heures de travail dans un projet.
- Le logiciel peut contenir des options avancées telles que l'insertion des notes vocales et les attacher à des séances, et en plus, l'option de recherche sur le contenu des documents.

## Diagramme de navigation :
![alt text](<diagramme de navigation.png>)

## La conception générale
Nous avons réalisé une application en trois couches :
### 1. Couche persistance :
- Cette couche était responsable de la communication avec la base de données.
- Chaque objet dans la base de données était représenté par une classe DAO.
- Chaque table DAO offrait les services CRUD au minimum pour l'objet qu'elle représentait.
- Nous avons ajouté une classe responsable de la connexion, de la lecture et de l'écriture avec la base de données en utilisant le pattern singleton.
- Un objet était équivalent à une entité dans le modèle conceptuel de données.

### 2. Couche Métier :
- Cette couche était divisée en trois packages : POJO, Gestion et Service.
- POJO : Les classes métier étaient des objets BD avec des getters et des setters.
- Gestion : Les classes géraient les opérations CRUD et la logique métier des POJO (collections de POJO + méthodes CRUD).
- Service : Les classes géraient les appels aux API extérieures.
- Seules les classes Gestion communiquaient avec la couche persistance.

### 3. Couche Présentation :
- Chaque écran dans la maquette était représenté par une classe.
- Nous avons ajouté une classe modèle pour chaque écran (cette classe contenait les données de l'écran avec des getters et des setters).
- Une classe contrôleur gérait l'ensemble des écrans et actions d'un objet.
- Nous avons respecté le pattern MVC pour les classes précédentes.
- Seules les classes contrôleurs avaient le droit de communiquer avec la couche métier (classes Gestion), un contrôleur pouvait appeler un autre contrôleur.

Nous avons appliqué ce modèle pour réaliser notre application en suivant ces règles et en respectant le pattern MVC.

## Documentation des  API  utilisées et leurs  interactions avec le logiciel

### 1. <u> <b> Google Tasks </b> </u>
Notre application utilise l'API Google Tasks pour intégrer la gestion des tâches dans notre système. L'API Google Tasks permet aux utilisateurs d'afficher, de créer, de modifier et de supprimer des listes de tâches et des tâches individuelles. Cette intégration permet de synchroniser les tâches de l'application avec celles de Google Tasks, offrant une gestion unifiée et centralisée des tâches.

- ### Bibliothèques et API utilisées:
    Google API Client Library for Java: Cette bibliothèque permet d'interagir avec les API de Google. Nous utilisons principalement les classes et méthodes fournies par cette bibliothèque pour authentifier les utilisateurs et accéder à leurs données Google Tasks.

- ### Structure du package “mygroup.metier.Service”:
    Le package “mygroup.metier.Service” contient la classe `TaskQuickstart` qui gère l'interaction avec l'API Google Tasks. Voici une description détaillée des principales fonctionnalités :
    
    - <b> Authentification et autorisation : </b>
        - `getCredentials`: Cette méthode gère l'authentification de l'utilisateur et la récupération des informations d'identification nécessaires pour accéder à l'API Google Tasks. Elle : 
            - utilise OAuth 2.0 pour autoriser l'application à accéder aux données de l'utilisateur.
            - Lit les secrets du client à partir du fichier `credentials.json`.
            - Initialise le flux de l'autorisation OAuth 2.0.
            - Utilise un serveur local pour gérer le processus d'autorisation.
            - Rafraîchit le token si nécessaire ou redirige l'utilisateur pour renouveler les autorisations.
            - `hasPermissions`: Vérifie si l'utilisateur a les permissions nécessaires pour accéder aux données Google Tasks. Si le token est expiré ou invalide, il tente de le rafraîchir.

    - <b> Récupération des tâches : </b>
        - `getTasks`:Cette méthode récupère les tâches de l'utilisateur à partir de Google Tasks pour une date spécifique. Elle :
            - Vérifie les permissions.
            - Récupère les listes de tâches de l'utilisateur.
            - Pour chaque liste de tâches, récupère les tâches et les filtre par date due.
            - Formate les informations des tâches et les ajoute à une liste observable qui peut être utilisée dans l'interface utilisateur.

    - <b> Formatage et nettoyage des données : </b>
        - `getFormattedDateTime`: Formate la date et l'heure des tâches récupérées pour les afficher de manière conviviale.
        
        - `clearTokenContent`: Supprime les fichiers de token pour forcer une nouvelle autorisation lors de la prochaine connexion. Cela peut être utile en cas de problème avec les permissions ou pour réinitialiser l'état de l'authentification.

- ### Exemple d'interaction avec l'application :
    - <b> Authentification : </b>
    Lorsqu'un utilisateur ouvre l'application pour la première fois ou lorsque le token est expiré, l'application le redirige vers un navigateur pour se connecter avec son compte Google et autoriser l'accès à Google Tasks.
    
    - <b> Récupération des tâches : </b>
    Après authentification, l'application appelle `getTasks` pour récupérer les tâches du jour spécifié. Les tâches sont ensuite affichées dans l'interface utilisateur, permettant à l'utilisateur de voir ses tâches et leurs détails (titre, description, date due).
    
    - <b> Mise à jour et affichage des tâches : </b>
    Les tâches sont affichées dans une interface JavaFX sous forme de liste observable. L'utilisateur peut interagir avec cette liste pour afficher les détails des tâches, marquer des tâches comme complétées ou en créer de nouvelles (fonctionnalités supplémentaires potentielles).
    
    - <b> Gestion des erreurs : </b>
    En cas d'erreur d'authentification ou de problème de connexion à l'API, des messages d'erreur appropriés sont affichés, et des actions correctives comme le rafraîchissement du token ou la redirection pour une nouvelle autorisation sont entreprises.

### 2. <u> <b> Hunter </b> </u>
Notre application utilise l'API Hunter pour vérifier la validité des adresses e-mail. Hunter fournit un service de vérification d'e-mails qui permet de déterminer si une adresse e-mail est livrable, c'est-à-dire si elle est valide et peut recevoir des e-mails. Cette vérification est essentielle pour garantir la qualité des données des utilisateurs et éviter les erreurs lors de la communication par e-mail.

- ### Bibliothèques et API utilisées :
    - <b> Apache HttpClient : </b> Utilisé pour envoyer des requêtes HTTP à l'API Hunter et recevoir les réponses.
    - <b> JSON.simple : </b> Utilisé pour analyser et traiter les réponses JSON de l'API Hunter.
    - <b> Java I/O : </b> Utilisé pour lire les fichiers de configuration.

- ### Structure du package “mygroup.metier.Service” :
    Le package `mygroup.metier.Service` contient la classe `LoginService` qui gère l'interaction avec l'API Hunter pour vérifier les adresses e-mail. Voici une description détaillée des principales fonctionnalités :

    - <b> Vérification de l'adresse e-mail : </b>
        - `isValidEmailAddress`: Cette méthode vérifie si une adresse e-mail est valide en utilisant l'API Hunter. Elle :
            - Récupère la clé API à partir du fichier de configuration.
            - Exécute une requête de vérification d'adresse e-mail à l'API Hunter.
            - Analyse le résultat de la requête pour déterminer si l'adresse e-mail est livrable.
    
    - <b> Récupération de la clé API : </b>
        - `getAPIKeyFromConfig`: Cette méthode lit la clé API à partir d'un fichier de configuration JSON. Elle :
            - Lit le contenu du fichier JSON.
            - Analyse le JSON pour extraire la clé API.
    
    - <b> Exécution de la requête de vérification : </b>
        - `executeEmailVerificationRequest`:
              Cette méthode envoie une requête HTTP à l'API Hunter pour vérifier une adresse e-mail. Elle :
            - Crée une requête HTTP GET avec l'adresse e-mail et la clé API.
            - Envoie la requête et reçoit la réponse.
            - Convertit la réponse en une chaîne de caractères.

    - <b> Analyse du résultat de la vérification : </b>
        - `parseEmailVerificationResult`: Cette méthode analyse la réponse JSON de l'API Hunter pour déterminer si l'adresse e-mail est livrable. Elle :
            - Analyse le JSON de la réponse.
            - Extrait et évalue le résultat de la vérification.

    - <b> Lecture du fichier JSON : </b>
        - `getJSONFromFile`: Cette méthode lit le contenu d'un fichier JSON. Elle :
            - Ouvre et lit le fichier ligne par ligne.
            - Construit une chaîne de caractères contenant le contenu du fichier.

- ### Exemple d'interaction avec l'application :
    - <b> Vérification de l'adresse e-mail : </b>
    Lorsqu'un utilisateur saisit une adresse e-mail, l'application appelle la méthode `isValidEmailAddress` pour vérifier sa validité. Cette méthode :
        - Lit la clé API à partir du fichier de configuration en appelant `getAPIKeyFromConfig`.
        - Envoie une requête à l'API Hunter avec l'adresse e-mail et la clé API en appelant `executeEmailVerificationRequest`.
        - Analyse la réponse de l'API pour déterminer si l'adresse e-mail est livrable en appelant `parseEmailVerificationResult`.
        

    - <b> Gestion des erreurs : </b>
    Si une erreur survient lors de la lecture de la clé API ou de la requête à l'API Hunter, une exception est capturée et l'erreur est affichée dans la console. L'application retourne `false` pour indiquer que l'adresse e-mail n'est pas vérifiable.

    - <b> Utilisation des fichiers de configuration : </b>
    La clé API est stockée dans un fichier JSON (`config.json`) qui est lu par la méthode `getAPIKeyFromConfig`. Cette méthode utilise `getJSONFromFile` pour lire le fichier et extraire la clé API.

- ### Conclusion :
    L'intégration de l'API Hunter dans notre application permet de vérifier efficacement la validité des adresses e-mail, améliorant ainsi la qualité des données utilisateur et réduisant les erreurs de communication. Grâce à l'utilisation des bibliothèques Apache HttpClient et JSON.simple, nous avons pu implémenter une solution robuste et fiable pour interagir avec l'API Hunter.
    
### 3. <u> <b> Google Calendar </b> </u>
Dans notre application, nous utilisons l'API Google Calendar pour récupérer les événements du calendrier de l'utilisateur. Cette intégration permet d'afficher les événements planifiés dans l'application, offrant ainsi une vue consolidée de l'emploi du temps de l'utilisateur.

- ### Bibliothèques et API utilisées :
    - <b> Google API Client Library for Java : </b> Cette bibliothèque permet d'interagir avec les API de Google. Nous utilisons principalement les classes et méthodes fournies par cette bibliothèque pour authentifier les utilisateurs et accéder à leurs données de calendrier.

- ### Structure du package “mygroup.metier.Service” :
    Le package `mygroup.metier.Service` contient la classe `CalendarQuickstart` qui gère l'interaction avec l'API Google Calendar pour récupérer les événements du calendrier de l'utilisateur. Voici une description détaillée des principales fonctionnalités :

    - <b> Authentification et autorisation : </b>
        - `getCredentials`: Cette méthode gère l'authentification de l'utilisateur et la récupération des informations d'identification nécessaires pour accéder à l'API Google Calendar. Elle utilise OAuth 2.0 pour autoriser l'application à accéder aux données de calendrier de l'utilisateur.
            - Lit les secrets du client à partir du fichier `credentials.json`.
            - Initialise le flux de l'autorisation OAuth 2.0.
            - Utilise un serveur local pour gérer le processus d'autorisation.
            - Rafraîchit le token si nécessaire ou redirige l'utilisateur pour renouveler les autorisations.
        - `hasPermissions`: Vérifie si l'utilisateur a les permissions nécessaires pour accéder aux données du calendrier. Si le token est expiré ou invalide, il tente de le rafraîchir.
        
    
    - <b> Récupération des événements du calendrier : </b>
        - `getEvents`: Cette méthode récupère les événements du calendrier de l'utilisateur à partir de Google Calendar. Elle :
            - Vérifie les permissions.
            - Récupère les événements du calendrier de l'utilisateur pour la journée spécifiée.
            - Formate les informations des événements et les ajoute à une liste observable qui peut être utilisée dans l'interface utilisateur.
        
    
    - <b> Formatage et nettoyage des données : </b>
        - `getFormattedDateTime`: Formate la date et l'heure des événements récupérés pour les afficher de manière conviviale.
        
        - `clearTokenContent`: Supprime les fichiers de token pour forcer une nouvelle autorisation lors de la prochaine connexion. Cela peut être utile en cas de problème avec les permissions ou pour réinitialiser l'état de l'authentification.

- ### Exemple d'interaction avec l'application :
    - <b> Authentification : </b>
    Lorsqu'un utilisateur ouvre l'application pour la première fois ou lorsque le token est expiré, l'application le redirige vers un navigateur pour se connecter avec son compte Google et autoriser l'accès à Google Calendar. 

    - <b> Récupération des événements du calendrier : </b>
    Après authentification, l'application appelle `getEvents` pour récupérer les événements du calendrier de l'utilisateur pour la journée spécifiée. Les événements sont ensuite affichés dans l'interface utilisateur, permettant à l'utilisateur de voir ses rendez-vous et activités planifiées.

    - <b> Gestion des erreurs : </b>
    En cas d'erreur d'authentification ou de problème de connexion à l'API, des messages d'erreur appropriés sont affichés, et des actions correctives comme le rafraîchissement du token ou la redirection pour une nouvelle autorisation sont entreprises.

## Plan de Tests Fonctionnels :
- <b> Test de Connexion par Email : </b> Vérifier que l'utilisateur peut se connecter à l'application en utilisant son email.
- <b> Test des Boutons de la Barre de Navigation : </b> S'assurer que les boutons de la barre de navigation (Listes, Projets, Archive) fonctionnent correctement.
- <b> Test du Bouton "Ordonner" dans l'Interface des Listes : </b> Confirmer que le bouton "Ordonner" permet de trier les listes comme prévu.
- <b> Test de la Fonction de Recherche par Mot-Clé : </b> Vérifier que la recherche par mot-clé retourne les résultats appropriés.
- <b> Test d'Ajout d'une Nouvelle Tâche : </b> Valider que l'utilisateur peut ajouter une nouvelle tâche correctement.
- <b> Test d'Importation des Tâches depuis Google Calendrier : </b> S'assurer que les tâches peuvent être importées depuis Google Calendrier sans problème.
- <b> Test de Sélection d'une Liste : </b> Vérifier que l'utilisateur peut sélectionner et afficher le contenu d'une liste.
- <b> Test de Création d'une Liste : </b> Confirmer que l'utilisateur peut créer une nouvelle liste.
- <b> Test de Détails d'une Tâche : </b> S'assurer que les détails d'une tâche s'affichent correctement.
- <b> Test d'Ajout d'un Document à une Tâche : </b> Valider que l'utilisateur peut ajouter des documents aux tâches.
- <b> Test d'Importation d'une Tâche dans l'Interface Ajouter Tâche : </b> Vérifier que l'importation d'une tâche dans l'interface fonctionne correctement.
- <b> Test de Sauvegarde des Modifications d'une Tâche : </b> S'assurer que les modifications apportées à une tâche sont bien sauvegardées.
- <b> Test de Création d'un Projet : </b> Confirmer que l'utilisateur peut créer un nouveau projet.
- <b> Test d'Ajout d'un Document à un Projet : </b> Vérifier que des documents peuvent être ajoutés à un projet.
- <b> Test d'Ajout d'une Séance à un Projet : </b> Valider que l'utilisateur peut ajouter des séances à un projet.
- <b> Test de Sauvegarde d'un Projet : </b> S'assurer que les projets peuvent être sauvegardés correctement.
- <b> Test du Bouton "Annuler" : </b> Vérifier que le bouton "Annuler" fonctionne comme prévu.
- <b> Test d'Affichage des Éléments Attachés à un Projet : </b> Confirmer que les séances, tâches et documents attachés à un projet s'affichent correctement.
- <b> Test d'Ajout d'une Séance : </b> Valider que l'utilisateur peut ajouter une nouvelle séance.
- <b> Test d'Affichage d'une Séance : </b> Vérifier que les détails d'une séance s'affichent correctement.
- <b> Test d'Ajout des Projets Clôturés aux Archives : </b> S'assurer que les projets clôturés sont bien ajoutés à l'archive.
- <b> Test de Recherche dans l'Archive : </b> Valider que la recherche dans l'archive fonctionne correctement et retourne les résultats attendus.
- <b> Test de Suppression d'une Tâche : </b> Vérifier que l'utilisateur peut supprimer une tâche et que celle-ci disparaît de l'interface appropriée sans erreurs.
- <b> Test de Modification des Informations d'un Projet : </b> S'assurer que les informations d'un projet peuvent être modifiées et que les modifications sont sauvegardées correctement.
- <b> Test de Clonage d'unE tache : </b> Valider que l'utilisateur peut cloner un projet et que toutes les informations du projet original sont correctement dupliquées.
- <b> Test de Clôture d'un Projet : </b> Vérifier que l'utilisateur peut clôturer un projet et que le projet passe bien dans l'état "clôturé".
- <b> Test de Filtrage des Projets par Catégorie ou Type : </b> S'assurer que les projets peuvent être filtrés par catégorie (Enseignement, Encadrement, Autre) ou type (Thèse, PFE, etc.) et que les résultats sont corrects.
- <b> Test de Recherche des Projets par Mot-Clé : </b> Vérifier que la recherche de projets par mot-clé fonctionne correctement et retourne les résultats pertinents.
- <b> Test de Consultation des Statistiques : </b> Valider que les statistiques, telles que le nombre d'heures de travail et le nombre de documents par projet, s'affichent correctement.
- <b> Test de Performance : </b> Vérifier que l'application fonctionne de manière fluide et efficace, même avec un grand nombre de projets, tâches et documents.
- <b> Test d'Importation et d'Exportation de Données : </b> Vérifier que les données peuvent être importées et exportées sans perte ni corruption, facilitant ainsi les sauvegardes et les restaurations.
- <b> Test de Gestion des Séances Importées depuis Google Calendrier : </b> Valider que les séances importées depuis Google Calendrier s'affichent correctement et peuvent être éditées ou associées à des projets existants.
Ces tests permettent de garantir que l'application est fonctionnelle, robuste, et répond aux besoins de l'utilisateur tout en offrant une expérience utilisateur optimale.

## Maintenance : 
Les Fonctionnalités à Ajouter :
- <b> Gestion de la Participation des Étudiants : </b> La gestion de la participation des étudiants dans les séances pourrait être une fonctionnalité précieuse. En stockant les notes de participation des étudiants pour chaque séance, les professeurs pourraient suivre de manière plus précise et organisée l'engagement de leurs étudiants. Cette modification est relativement facile à implémenter puisqu'il s'agit principalement d'ajouter une interface utilisateur permettant d'ajouter et de consulter les notes de participation. En intégrant cette fonctionnalité, l'application offrirait une vue d'ensemble plus complète de chaque séance, facilitant ainsi l'évaluation continue des étudiants.

- <b> Notifications et Alertes : </b> Ajouter des notifications et des alertes pour rappeler les échéances et les tâches importantes est une autre fonctionnalité clé à intégrer. Cela peut être réalisé en utilisant des services de notification comme Firebase Cloud Messaging pour envoyer des notifications push. Cette tâche est relativement facile à accomplir car les services de notification sont bien documentés et leur intégration est généralement simple. Cependant, il sera crucial de gérer minutieusement les préférences des utilisateurs pour éviter les notifications excessives et garantir que les rappels restent utiles et non intrusifs.

- <b> Développement d'une Version Mobile : </b> Développer une version mobile de l'application permettrait aux professeurs de gérer leurs tâches et projets en déplacement, augmentant ainsi leur flexibilité et leur efficacité. Cela pourrait être réalisé en créant des applications natives pour iOS et Android, ou en utilisant des frameworks multiplateformes comme React Native ou Flutter. Cette tâche est plus difficile, car le développement d'applications mobiles nécessite des compétences spécifiques, notamment en matière de conception d'interfaces utilisateur adaptées aux petits écrans et de gestion de la synchronisation des données entre les appareils.

## Conclusion
Le développement de cette application de gestion des tâches et des projets académiques a été une expérience enrichissante et formatrice.
Le projet a été conçu spécifiquement pour répondre aux besoins de notre professeur de classe, offrant une solution pratique pour organiser et suivre ses diverses activités académiques.
- ### <b> Avis sur le Projet : </b>
    Le projet a été particulièrement intéressant à réaliser, car il s'agissait de créer un outil personnalisé et adapté aux exigences spécifiques de notre professeur. <br>
    L'application offre une interface conviviale et des fonctionnalités robustes pour la gestion des projets et des tâches, permettant ainsi une organisation efficace du travail.<br>
    Le choix des technologies, comme Java avec Swing ou JavaFX pour l'interface graphique, et JSON pour le stockage des données, a permis de développer une application fiable et performante.<br>
    Le développement a été axé sur la simplicité d'utilisation et la clarté de présentation, assurant ainsi que notre professeur puisse utiliser l'application sans difficulté.<br> 
    La documentation et les commentaires dans le code source ont été soigneusement préparés pour faciliter la maintenance future et les éventuelles modifications.

- ### <b> Résultats Produits : </b>
    Les résultats obtenus sont conformes aux attentes initiales. L'application répond pleinement aux besoins de gestion des projets et des tâches académiques, en offrant des fonctionnalités comme la création, la modification, la clôture, et la consultation de projets et de tâches. L'intégration avec Google Calendrier pour l'importation des séances de travail a ajouté une dimension pratique, permettant une synchronisation facile avec les événements existants.<br>
    Les tests effectués ont montré que l'application fonctionne de manière stable et fiable, offrant une performance fluide même avec un volume important de données. Les retours de notre professeur ont été très positifs, soulignant l'utilité et la facilité d'utilisation de l'outil.
    
- ### <b> Perspectives Éventuelles : </b>
    Pour l'avenir, plusieurs perspectives d'amélioration peuvent être envisagées :
    
    1. Amélioration de l'Interface Utilisateur : Bien que l'interface soit déjà conviviale, des améliorations peuvent toujours être apportées pour rendre l'expérience utilisateur encore plus agréable, comme l'ajout de thèmes personnalisables ou l'optimisation de la disposition des éléments.
    
    2. Fonctionnalités Avancées : Intégrer des fonctionnalités supplémentaires telles que des rappels automatiques pour les échéances des tâches, ou des statistiques plus détaillées sur le temps de travail et l'avancement des projets.
    
    3. Optimisation des Performances : Continuer à optimiser l'application pour assurer une performance optimale, même avec une augmentation du nombre de projets et de tâches.
    
    4. Maintenance et Mise à Jour : Assurer une maintenance régulière de l'application pour corriger d'éventuels bugs et ajouter de nouvelles fonctionnalités en fonction des retours de notre professeur.
    
    En conclusion, ce projet a permis de créer une application utile et efficace pour la gestion des tâches académiques de notre professeur. L'expérience acquise lors de ce développement est précieuse, et les perspectives d'amélioration offrent de nombreuses opportunités pour continuer à perfectionner l'outil. Nous sommes satisfaits des résultats obtenus et confiants que cette application aidera notre professeur à mieux organiser et gérer ses activités académiques.