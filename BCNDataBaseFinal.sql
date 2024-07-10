USE master
CREATE DATABASE BCNDataBase
USE BCNDataBase

CREATE TABLE CLIENTE (
id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
nombre VARCHAR(50),
direccion VARCHAR(60),
numero_telefono VARCHAR(15)
);

CREATE TABLE FACILITADOR (
id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
nombre VARCHAR (20)
);

CREATE TABLE TARJETA(
id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
numero VARCHAR (30),
fecha_expiracion DATE,
tipo VARCHAR(30),
id_facilitador INT NOT NULL,
id_cliente INT NOT NULL
);
-- FK TARJETA
ALTER TABLE TARJETA
ADD CONSTRAINT FK_TARJETA_CLIENTE
FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id) ON DELETE CASCADE;

ALTER TABLE TARJETA
ADD CONSTRAINT FK_TARJETA_FACILITADOR
FOREIGN KEY (id_facilitador) REFERENCES FACILITADOR (id) ON DELETE CASCADE;

CREATE TABLE COMPRA(
id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
fecha DATE,
monto MONEY,
descripcion VARCHAR(40),--
id_tarjeta INT NOT NULL
);
-- FK COMPRA
ALTER TABLE COMPRA
ADD CONSTRAINT FK_COMPRA_TARJETA
FOREIGN KEY (id_tarjeta) REFERENCES TARJETA (id) ON DELETE CASCADE;

CREATE TABLE COMPRAXCLIENTE(
id_cliente INT NOT NULL,
id_compra INT NOT NULL
);
--PK COMPRAXCLIENTE
ALTER TABLE COMPRAXCLIENTE
ADD PRIMARY KEY(id_cliente, id_compra);
--FK COMPRAXCLIENTE
ALTER TABLE COMPRAXCLIENTE
ADD CONSTRAINT FK_COMPRAXCLIENTE_CLIENTE
FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id) ON DELETE NO ACTION;

ALTER TABLE COMPRAXCLIENTE
ADD CONSTRAINT FK_COMPRAXCLIENTE_COMPRA
FOREIGN KEY (id_compra) REFERENCES COMPRA (id) ON DELETE CASCADE;

--TRIGGER PARA TABLA COMPRAXCLIENTE
CREATE TRIGGER trigger_COMPRAXCLIENTE
ON COMPRA
AFTER INSERT 
AS
BEGIN
	INSERT INTO COMPRAXCLIENTE (id_cliente, id_compra) 
	SELECT T.id_cliente, i.id 
	FROM inserted i 
	INNER JOIN TARJETA T ON i.id_tarjeta = T.id;

END


 INSERT INTO CLIENTE(nombre, direccion, numero_telefono) VALUES
('Karla Martinez', 'Santa Tecla, La Libertad', '7101-2345'),
('Violeta Amaya', 'Comasagua, La Libertad', '7102-3456'),
('Fernando Diaz', 'Zaragoza, La Libertad', '7103-4567'),
('Emely Chavez', 'San Juan Opico, La Libertad', '7104-5678'),
('Daniela Ferrer', 'San Jose Villanueva, La Libertad', '7105-6789'),
('Lisbeth Gonzalez', 'San Salvador, San Salvador', '7106-7890'),
('Kevin Nativi', 'Mejicanos, San Salvador', '7107-8901'),
('Alexandra Mejia', 'Soyapango, San Salvador', '7108-9012'),
('Jose Linares', 'Ciudad Delgado, San Salvador', '7109-0123'),
('Natalia Coto', 'Apopa, San Salvador', '7110-1234'),
('Sofia Mendoza', 'Berlin, Usulutan', '7111-2345'),
('Patricio Zelaya', 'Alegria, Usulutan', '7112-3456'),
('Maria Hernandez', 'El Triunfo, Usulutan', '7113-4567'),
('Catherine Herrera', 'Santa Elena, Usulutan', '7114-5678'),
('Marco Bermudez', 'Jiquilisco, Usulutan', '7115-6789');

INSERT INTO FACILITADOR(nombre) VALUES
('Visa'),
('MasterCard'),
('American Express');

INSERT INTO TARJETA(numero, fecha_expiracion, tipo, id_facilitador, id_cliente) VALUES
('1234-5678-9012-3456', '2026-01-07', 'DEBITO', 1, 1),
('2345-6789-0123-4567', '2026-01-19', 'DEBITO', 2, 2),
('3456-7890-1234-5678', '2026-03-21', 'DEBITO', 3, 3),
('4567-8901-2345-6789', '2026-03-30', 'DEBITO', 1, 4),
('5678-9012-3456-7890', '2026-09-27', 'DEBITO', 2, 5),
('6789-0123-4567-8901', '2026-04-02', 'DEBITO', 3, 6),
('7890-1234-5678-9012', '2026-11-17', 'CREDITO', 1, 7),
('8901-2345-6789-0123', '2026-12-12', 'CREDITO', 2, 8),
('9012-3456-7890-1234', '2026-11-09', 'CREDITO', 3, 9),
('0123-4567-8901-2345', '2026-01-13', 'CREDITO', 1, 10),
('1234-5678-9012-3456', '2026-01-22', 'CREDITO', 2, 11),
('2345-6789-0123-4567', '2026-05-18', 'CREDITO', 3, 12),
('3456-7890-1234-5678', '2026-07-29', 'CREDITO', 1, 13),
('4567-8901-2345-6789', '2026-02-11', 'CREDITO', 2, 14),
('5678-9012-3456-7890', '2026-01-03', 'CREDITO', 3, 15);

INSERT INTO COMPRA(fecha, monto, descripcion, id_tarjeta) VALUES
('2024-04-19', 200.00, 'Electrodomestico', 1 ),
('2024-01-22', 350.00, 'Telefono', 7 ),
('2024-03-15', 50.00, 'Auriculares', 1 ),
('2024-02-22', 500.00, 'Camara', 3 ),
('2024-02-14', 230.99, 'Impresora', 8 ),
('2024-07-29', 1000.00, 'Laptop', 3 ),
('2024-04-10', 70.00, 'Mochila', 13 ),
('2024-06-02', 150.00, 'Reloj', 1 ),
('2024-05-04', 575.00, 'Electrodomestico', 10 ),
('2024-07-07', 400.99, 'Tablet', 9 );