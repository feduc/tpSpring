drop database if exists NikoSmile;

create database NikoSmile;

use NikoSmile;

#------------------------------------------------------------
# Table: utilisateur
#------------------------------------------------------------

CREATE TABLE utilisateur(
        id            int (11) Auto_increment  NOT NULL ,
        nom           Varchar (25) NOT NULL,
        prenom        Varchar (25) NOT NULL,
        matricule_CGI Varchar (25) NOT NULL,
        verticale     Varchar (25) NOT NULL,
        agence        Varchar (25) NOT NULL,
        id_securite   Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: projet
#------------------------------------------------------------

CREATE TABLE projet(
        id                  int (11) Auto_increment  NOT NULL ,
        nom_projet          Varchar (25) NOT NULL,
        chef_de_projet      Varchar (25) NOT NULL,
        verticale           Varchar (25) NOT NULL,
        date_debut          Datetime NOT NULL,
        date_fin            Datetime ,
        anonyme             boolean NOT NULL,
        cache               boolean NOT NULL,
        couleur_heureux     Varchar (25),
        couleur_neutre      Varchar (25),
        couleur_mecontent   Varchar (25),
        cinq_choix          boolean,
        PRIMARY KEY (id )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: avis
#------------------------------------------------------------

CREATE TABLE avis(
        id             int (11) Auto_increment  NOT NULL ,
        avis_journee   Varchar (25) NOT NULL,
        date_jour      Datetime NOT NULL,
        texte_jour     Varchar (200) ,
        id_utilisateur Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: date_modif
#------------------------------------------------------------

CREATE TABLE date_modif(
        id             int (11) Auto_increment  NOT NULL ,
        date_modif     Datetime NOT NULL,
        id_avis Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: securite
#------------------------------------------------------------

CREATE TABLE securite(
        id             int (11) Auto_increment  NOT NULL ,
        login          Varchar (25) NOT NULL,
        mot_de_passe   Varchar (25) NOT NULL,
        statut         Varchar (25) NOT NULL,
        id_utilisateur Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: equipe
#------------------------------------------------------------

CREATE TABLE equipe(
        id_projet      Int NOT NULL ,
        id_utilisateur Int NOT NULL ,
        PRIMARY KEY (id_projet ,id_utilisateur )
)ENGINE=InnoDB;

ALTER TABLE utilisateur ADD CONSTRAINT FK_utilisateur_id_securite FOREIGN KEY (id_securite) REFERENCES securite(id);
ALTER TABLE avis ADD CONSTRAINT FK_avis_id_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id);
ALTER TABLE avis ADD CONSTRAINT FK_avis_id_projet FOREIGN KEY (id_projet) REFERENCES projet(id);
ALTER TABLE securite ADD CONSTRAINT FK_securite_id_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id);
ALTER TABLE equipe ADD CONSTRAINT FK_equipe_id_projet FOREIGN KEY (id_projet) REFERENCES projet(id);
ALTER TABLE equipe ADD CONSTRAINT FK_equipe_id_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id);
ALTER TABLE date_modif ADD CONSTRAINT FK_date_modif_id_avis FOREIGN KEY (id_avis) REFERENCES avis(id);