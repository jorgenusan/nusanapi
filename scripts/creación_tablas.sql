use db_nusan;
CREATE TABLE IF NOT EXISTS clients(
	idClient INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name_cli varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
    dni VARCHAR(9) NOT NULL,
	email varchar(50),
	phone_number INT NOT NULL,
    city varchar(50),
	address varchar(255)
 );
 CREATE TABLE IF NOT EXISTS employees(
	idEmployee INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name_emp varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
    dni VARCHAR(9) NOT NULL,
	email varchar(50),
	phone_number INT NOT NULL,
    status_emp enum('conectado','desconectado'),
    password_emp varchar(45) NOT NULL
  );
CREATE TABLE IF NOT EXISTS reports(
	idReport int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	start_date date NOT NULL,
	ending_date date,
    date_apointment date,
	priority enum('alta','media','baja'),
	state enum('abierto','pendiente','cerrado') NOT NULL,
    machine varchar(50),
    brand varchar(50),
    observations varchar(225),
    payment int,
    payment_method enum('efectivo','tarjeta'),
    id_emp int NULL,
    id_cli int NOT NULL,
    CONSTRAINT FK_EMP FOREIGN KEY (id_emp) REFERENCES employees (idEmployee),
	CONSTRAINT FK_CLI FOREIGN KEY (id_cli) REFERENCES clients (idClient)
);