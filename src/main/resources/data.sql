INSERT INTO roomtype(id, nbradults, nbrchildrens, ppn, surface, type, description) VALUES 
(1, 2, 1, 121, 40, 'Standard','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(2, 3, 1, 146, 50, 'Deluxe','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(3, 3, 1, 161, 55, 'Deluxe Superior','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(4, 3, 1, 196, 65, 'Premium','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(5, 4, 2, 266, 80, 'Suite Junior','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(6, 5, 2, 412, 110, 'Suite Royale','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.');


INSERT INTO room(id, fk_roomtype) VALUES 
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 2),(10, 2),(11, 2),(12, 3), (13, 3), (14, 3), (15, 4), (16, 4), (17, 4), (18, 5), (19, 5),(20, 6);
/*(1, 1), (2, 1), (3, 1), (4, 1), (9, 1),(20, 5);*/

/*
INSERT INTO CUSTOMER (id,name,firstname,dob,mail,address,zipcode,city,country,newsletter) VALUES
(1,'NOM1','PRENOM1','10/02/2001','toto1@toto.fr','1 RUE DE VINCENNES',75001,'PARIS1','FRANCE',TRUE),
(2,'NOM2','PRENOM2','10/02/2002','toto2@toto.fr','2 RUE DE VINCENNES',75002,'PARIS2','FRANCE',TRUE),
(3,'NOM3','PRENOM3','10/02/2003','toto3@toto.fr','3 RUE DE VINCENNES',75003,'PARIS3','FRANCE',TRUE),
(4,'NOM4','PRENOM4','10/02/2004','toto4@toto.fr','4 RUE DE VINCENNES',75004,'PARIS4','FRANCE',TRUE),
(5,'NOM5','PRENOM5','10/02/2004','toto5@toto.fr','5 RUE DE VINCENNES',75005,'PARIS5','FRANCE',TRUE),
(6,'NOM6','PRENOM6','10/02/2006','toto6@toto.fr','6 RUE DE VINCENNES',75006,'PARIS6','FRANCE',TRUE),
(7,'NOM7','PRENOM7','10/02/2007','toto7@toto.fr','7 RUE DE VINCENNES',75007,'PARIS7','FRANCE',TRUE),
(8,'NOM8','PRENOM8','10/02/2008','toto8@toto.fr','8 RUE DE VINCENNES',75008,'PARIS8','FRANCE',TRUE),
(9,'NOM9','PRENOM9','10/02/2009','toto9@toto.fr','9 RUE DE VINCENNES',75009,'PARIS9','FRANCE',TRUE);

INSERT INTO BOOKING (id,numbooking,fk_idcustomer,datein,dateout,nbrnights,nbradults,nbrchildrens,breakfast,fk_idroom) VALUES

(1,'ABCDE',1,'01/09/2018','15/09/2018',15,2,0,TRUE,1),
(2,'CZERR',2,'08/09/2018','10/09/2018',1,2,0,FALSE,4),
(3,'BZERT',3,'11/09/2018','13/09/2018',1,2,0,FALSE,2),
(4,'DZERT',4,'12/09/2018','15/09/2018',1,2,0,FALSE,9),
(5,'EZERT',5,'09/09/2018','11/09/2018',1,2,0,FALSE,3),
(6,'FZERT',6,'10/09/2018','12/09/2018',1,2,0,FALSE,20);
*/
