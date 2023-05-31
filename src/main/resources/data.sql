INSERT INTO vendeur (id, nom, siret) VALUES (1, 'SeptArche', '12345678');
INSERT INTO vendeur (id, nom, siret) VALUES (2, 'Amazon', '999999999');
INSERT INTO article (id, reference, designation, prixHT, tva, stock, vendeur_id) VALUES (1, '123', 'vélo', 124, 0.2, 3, 2);
INSERT INTO article (id, reference, designation, prixHT, tva, stock, vendeur_id) VALUES (2, '456', 'télé', 45, 0.2, 4, 1);
INSERT INTO article (id, reference, designation, prixHT, tva, stock, vendeur_id) VALUES (3, '789', 'les misérables de Victor Hugo', 30, 0.2, 4, 1);
INSERT INTO livre (id, titre, auteur) VALUES (3, 'les misérables', 'Hugo');