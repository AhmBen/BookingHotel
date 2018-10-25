INSERT INTO roomtype(id, nbradults, nbrchildrens, ppn, surface, type, description) VALUES 
(1, 2, 1, 121, 40, 'Standard','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(2, 3, 1, 146, 50, 'Deluxe','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(3, 3, 1, 161, 55, 'Deluxe Superior','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(4, 3, 1, 196, 65, 'Premium','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(5, 4, 2, 266, 80, 'Suite Junior','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.'),
(6, 5, 2, 412, 110, 'Suite Royale','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et turpis dapibus, ultricies dui in, euismod quam. Sed nisl ligula, malesuada at lacus bibendum, elementum pretium erat. Fusce tempus vitae urna eget suscipit. Phasellus ut cras amet.');


INSERT INTO room(id, fk_roomtype) VALUES 
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 2),(10, 2),(11, 2),(12, 3), (13, 3), (14, 3), (15, 4), (16, 4), (17, 4), (18, 5), (19, 5),(20, 6);
