SET FOREIGN_KEY_CHECKS=0; 
/*
ALTER TABLE clients NOCHECK CONSTRAINT ALL;
ALTER TABLE employees NOCHECK CONSTRAINT ALL;
ALTER TABLE reports NOCHECK CONSTRAINT ALL;
*/
INSERT INTO clients (name_cli,last_name,dni,email,phone_number,city,address) VALUES 
('Juan Manuel','Cerro Adan','56693421K','juanmanuelcerro@example.com',246875241,'Noia','Avinguda Mara, 9, 7º'),
('Pedro','Palenzuela Llobet','25856121G','pedropalenzuela@example.com',478523691,'Santiago','Travessera Lorenzo, 51, 4º'),
('Victor','Bustillo Bolaños','64312717W','victorbustillo@example.com',564253789,'Muros','Calle Caldera, 6, 6º A'),
('Ignacio','Marco Alfonso','83414661D','ignaciomarco@example.com',224569863,'Noia','Plaza Betancourt, 89, 65º D'),
('Julian','Galvan Sedano','65827922J','juliangalvan@example.com',558869142,'Boiro','Avinguda Nayara, 8, 6º E'),
('Miguel','Zarco Anton','64706153T','miguelzarco@example.com',789685861,'Santiago','Avinguda Noa, 01, 4º F'),
('Gonzalo','Giner Del Rosario','21936977Z','gonzaloginer@example.com',458395781,'Noia','Ronda Castro, 010, 3º B'),
('Jesús','Gamiz Briones','44599457L','jesusgamiz@example.com',967131458,'Porto do Son','Ronda Castro, 010, 3º B'),
('Santiago','Molero Sandoval','76588254W','santiagomolero@example.com',685593127,'Portosín','Ronda Castro, 010, 3º B'),
('Luis Miguel','Velazquez Parras','52451043A','luismiguel@example.com',475869132,'Muros','Camiño Ruiz, 510, 41º B');

INSERT INTO employees (name_emp,last_name,dni,email,phone_number,status_emp,password_emp) VALUES 
('Jorge','Núñez Santiago','78806952M','jorge.nusan@gmail.com',627943192,'desconectado','abc123.'),
('Mariano','Belmonte Benavent','23417467V','marianobelmonte@example.com',369258147,'desconectado','abc123.'),
('Joan','Muro Centeno','60690724A','joanmuro@example.com',123789456,'desconectado','abc123.'),
('Ismael','Castañeda Barranco','87170626M','ismaelcastañeda@example.com',852456357,'desconectado','abc123.');
 
INSERT INTO reports (start_date,ending_date,date_apointment,priority,state,machine,brand,observations,payment,payment_method,id_emp,id_cli) VALUES 
('2020-11-05','2020-12-10','2020-11-12','media','cerrado','Nevera','Balay','',50,'efectivo',1,2),
('2021-02-11','2021-02-23','2021-02-14','baja','cerrado','Vitroceramica de Inducción','Bosch','',70,'efectivo',4,3),
('2021-01-22','2021-02-03','2021-01-25','alta','cerrado','Monitor','Acer','Píxel muerto',60,'tarjeta',4,9);

INSERT INTO reports (start_date,date_apointment,priority,state,machine,brand,observations,id_emp,id_cli) VALUES 
('2021-02-04',CURRENT_DATE,'baja','abierto','Televisor','LG','Panel roto',1,1),
('2021-04-10',CURRENT_DATE,'media','abierto','Secadora','Electrolux','',3,5),
('2021-03-28','2021-04-01','media','abierto','Lavadora','Bosch','Motor roto',2,10);

INSERT INTO reports (start_date,priority,state,machine,brand,observations,id_cli) VALUES 
(CURRENT_DATE,'alta','pendiente','Microondas','Balay','',6),
('2021-04-01','alta','pendiente','Televisor','Samsung','Placa base rota',2),
(CURRENT_DATE,'baja','pendiente','Televisor','HP','Placa de sonido',7);

INSERT INTO reports (start_date,priority,state,machine,brand,id_emp,id_cli) VALUES 
(CURRENT_DATE,'media','abierto','Campana de extrancción','Fagor',4,4),
(CURRENT_DATE,'baja','abierto','Nevera','AEG',2,8);

/*
ALTER TABLE clients CHECK CONSTRAINT ALL;
ALTER TABLE employees CHECK CONSTRAINT ALL;
ALTER TABLE reports CHECK CONSTRAINT ALL;
*/
SET FOREIGN_KEY_CHECKS=1;