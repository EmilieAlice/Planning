-- Toutes les Requetes


-- Requete pour avoir le menu déroulant des matières disponibles (changer le 2015 par la variable de l'année en cours)

SELECT * FROM lagarenne2015.heures_session_matiere
INNER JOIN module on heures_session_matiere.id_module = module.id_module
INNER JOIN session on heures_session_matiere.id_session = session.id_session
HAVING nbre_heures_disponibles > 0
AND year(session.date_debut)=2015;

-- Requete pour mettre à jour le nombre d'heures disponibles d'une matière après insertion dans le calendrier

UPDATE `lagarenne2015`.`heures_session_matiere`
SET `nbre_heures_disponibles` = 35
WHERE `id_session` = 1 AND `id_module` = 2;

-- Requete pour récupérer le nom et le prenom du formateur en fonction du module

SELECT * FROM lagarenne2015.formateur
INNER JOIN personne on personne.id_personne = formateur.id_personne
INNER JOIN module on module.id_module = formateur.id_module
WHERE module.nom = 'Maths' ;

-- Requete pour avoir les contraintes d'un formateur

SELECT jour.nom_jour, creneau.nom_creneau FROM lagarenne2015.contrainte_formateur
INNER JOIN formateur on formateur.id_personne = contrainte_formateur.id_formateur
INNER JOIN module on formateur.id_module = module.id_module
INNER JOIN creneau on contrainte_formateur.id_creneau = creneau.id_creneau
INNER JOIN jour on contrainte_formateur.id_jour = jour.id_jour
WHERE module.nom = 'SI2';