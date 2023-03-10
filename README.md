# Securaudit

## Gestion complète d’une API CRUD avec BDD

#### Context :

« Securaudit » est une PME basée à Lyon qui réalise des audits de sécurité dans des industries réparties
sur le territoire français. Régulièrement, les auditeurs doivent intervenir dans ces industries.
À cette occasion, ils réalisent des frais (des dépenses) que « Securaudit » leur rembourse.
Vous devez concevoir et utiliser la base de données permettant de gérer ces frais.

## Installation

#### SQL :

Pour installer la base de données "securaudit", vous pouvez utiliser les requêtes SQL suivantes :

<pre><code>
CREATE DATABASE securaudit;

USE securaudit;

CREATE TABLE auditeur(
    id_auditeur INT AUTO_INCREMENT,
    civilite VARCHAR(3),
    nom VARCHAR(50),
    prenom VARCHAR(50),
    PRIMARY KEY(id_auditeur)
);

INSERT INTO auditeur (civilite, nom, prenom)
    VALUES 
    ("M.", "Bardet","Antoine"),
    ("Mme", "Delorme","Christelle"),
    ("M.", "Foulard","Eric"),
    ("M.", "Hoquart","Gerard"),
    ("Mme", "Jacquet","Isabelle"),
    ("M.", "Lopez", "Kevin");

CREATE TABLE industrie(
    id_industrie INT auto_increment,
    numero_siret BIGINT, 
    raison_sociale VARCHAR(50),
    id_auditeur INT NOT NULL,
    PRIMARY KEY(id_industrie),
    FOREIGN KEY(id_auditeur) REFERENCES auditeur(id_auditeur)
);

CREATE TABLE audit(
    id_audit INT auto_increment,
    date_debut DATE,
    duree INT,
    cout_jour DECIMAL(15,2),
    cout_total DECIMAL(15,2),
    id_industrie INT NOT NULL,
    id_auditeur INT NOT NULL,
    PRIMARY KEY(id_audit),
    FOREIGN KEY(id_industrie)
    REFERENCES industrie(id_industrie),
    FOREIGN KEY(id_auditeur) REFERENCES auditeur(id_auditeur)
);

CREATE TABLE categorie(
    id_categorie INT auto_increment,
    libelle VARCHAR(10),
    PRIMARY KEY(id_categorie)
);

INSERT INTO categorie (libelle)
VALUES ("Restaurant"),("Hôtel"),("Trajet"),("Matériel");

CREATE TABLE frais(
    id_frais INT auto_increment,
    date_debut_frais DATE,
    montant DECIMAL(15,2),
    est_rembourse boolean,
    id_audit INT NOT NULL,
    id_categorie INT NOT NULL,
    id_auditeur INT NOT NULL,
    PRIMARY KEY(id_frais),
    FOREIGN KEY(id_audit) REFERENCES audit(id_audit),
    FOREIGN KEY(id_categorie)
    REFERENCES categorie(id_categorie),
    FOREIGN KEY(id_auditeur) REFERENCES auditeur(id_auditeur)
);


-- boucle pour ajouter 50 industries
DELIMITER //
CREATE PROCEDURE insert_random_industries()
BEGIN
    DECLARE i INT DEFAULT 1;
        WHILE i <= 50 DO
            -- génération de valeurs aléatoires pour chaque industrie
            SET @numero_siret := FLOOR(RAND()*(99999999999999-10000000000000)+10000000000000);
            SET @raison_sociale := CONCAT('industrie_', i);
            SET @id_auditeur := FLOOR(RAND()*(6-1)+1); -- 6 étant le nombre d'auditeurs actuels dans la table auditeur     -- requête pour insérer les valeurs générées dans la table industrie
            INSERT INTO industrie(numero_siret, raison_sociale, id_auditeur)
            VALUES (@numero_siret, @raison_sociale, @id_auditeur);
            SET i = i + 1;
    END WHILE;
END //
DELIMITER ;

CALL insert_random_industries();

DELIMITER //
CREATE PROCEDURE insert_random_audits()
BEGIN
    DECLARE i INT DEFAULT 1;
        WHILE i <= 50 DO
            -- génération de valeurs aléatoires pour chaque audit
SET @date_debut := DATE_ADD(CURDATE(), INTERVAL -FLOOR(RAND()*(3652+1)) DAY);
            SET @duree := FLOOR(RAND()*(4-1)+1);
            SET @cout_jour := FLOOR(RAND()*(1000-200)+200);
            SET @id_industrie := FLOOR(RAND()*(50-1)+1); -- 50 étant le nombre d'industries ajoutées précédemment
            SET @id_auditeur := FLOOR(RAND()*(6-1)+1);
            -- calcul du coût total de l'audit
            SET @cout_total := @duree * @cout_jour;
            -- requête pour insérer les valeurs générées dans la table audit
            INSERT INTO audit(date_debut, duree, cout_jour, cout_total, id_industrie, id_auditeur)
            VALUES (@date_debut, @duree, @cout_jour, @cout_total, @id_industrie, @id_auditeur);         SET i = i + 1;
        END WHILE;
END //
DELIMITER ;

CALL insert_random_audits();

DELIMITER //
CREATE PROCEDURE insert_random_frais()
BEGIN
    DECLARE i INT DEFAULT 1;
        WHILE i <= 50 DO
            -- génération de valeurs aléatoires pour chaque frais
            SET @date_debut_frais := DATE_ADD(CURDATE(), INTERVAL -FLOOR(RAND()*(3652+1)) DAY);
            SET @montant := ROUND(RAND()*(200-10)+10, 2);
            SET @est_rembourse := FLOOR(RAND()*2);
            SET @id_audit := FLOOR(RAND()*(50-1)+1); -- 50 étant le nombre d'audits ajoutés précédemment
            SET @id_categorie := FLOOR(RAND()*(4-1)+1); -- 4 étant le nombre de catégories ajoutées précédemment
            SET @id_auditeur := FLOOR(RAND()*(6-1)+1);  
            -- requête pour insérer les valeurs générées dans la table frais
            INSERT INTO frais(date_debut_frais, montant, est_rembourse, id_audit, id_categorie, id_auditeur)
            VALUES (@date_debut_frais, @montant, @est_rembourse, @id_audit, @id_categorie, @id_auditeur);
            SET i = i + 1;
        END WHILE;
END //
DELIMITER ;

CALL insert_random_frais();
</code></pre>

## API CRUD JAVA

#### Objectifs :

- [x] Créer un CRUD pour chaque entité en respectant les règles de gestion.
- [x] En veillant à ne pas pouvoir supprimer des entités qui ont un lien avec d’autre <br>
      (par exemple : une entité qui a une fk).
- [x] Bien gérer les codes status HTTP.
- [x] Bien architecturer l’application (package par exemple, model, data, domain etc…)
- [x] Commenter son code.
- [x] Préparer des exemples d’exécutions des fonctionnalités. (POSTMAN)

- [x] Git : push sur un repo GitHub
- [x] Avoir minimum 2 branches (main et dev) + explication des branches
- [x] Commit commentés et justifiés (pas de commit sans remarque etc)
- [x] Fournir un document annexe avec les logs du Git

#### Bonus (OPTIONNEL) :

Créer un client :

- [x] Des formulaires
- [x] Des liens
- [x] Récupérations d’infos avec fetch

### Langages et outils utilisés :

<img align="left" alt="My SQL" width="8%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" style="padding-right:10px;" />
<img align="left" alt="IntelliJ" width="8%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" style="padding-right:10px;" />
<img align="left" alt="JAVA" width="8%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" />
<img align="left" alt="Git" width="8%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" style="padding-right:10px;" />
<img align="left" alt="HTML5" width=8%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg" style="padding-right:10px;" />
<img align="left" alt="CSS3" width=8%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg" style="padding-right:10px;" />
