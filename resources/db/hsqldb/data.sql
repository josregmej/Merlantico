-- Administrador
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

-- Clientes
INSERT INTO users(username,password,enabled) VALUES ('enrmorvaz','administrador',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'enrmorvaz','cliente');

INSERT INTO users(username,password,enabled) VALUES ('josregmej','usuario',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'josregmej','cliente');

INSERT INTO users(username,password,enabled) VALUES ('pedcarmor','adm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'pedcarmor','cliente');

INSERT INTO users(username,password,enabled) VALUES ('pedmuncif','administrador',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (20,'pedmuncif','cliente');

INSERT INTO users(username,password,telefono,dni,enabled) VALUES ('javhidrod1','A6min','617736165','31023797V',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'javhidrod1','cliente');

--Propietarios

INSERT INTO users(username,password,telefono,dni,enabled) VALUES ('manoloP','propietario','317732165','31012797R',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (90,'manoloP','propietario');

INSERT INTO users(username,password,telefono,dni,enabled) VALUES ('juanC','propietario','317342165','31212797C',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (91,'juanC','propietario');

--Agencias

INSERT INTO agenacts VALUES (1,'SurEventos','Sevilla', '687805782');
INSERT INTO agenacts VALUES (2,'Babalua','Huelva', '662157053');
INSERT INTO agenacts VALUES (3,'Otravis Eventos','Gines', '954546184');

--Hoteles

INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (2,'Av. Republica Argentina 1',5,'Apartments Vertice Aljarafe','Sevilla','955063360', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (75,'Calle Pages del Corro',5,'Hotel Zenit Sevilla','Sevilla','954347434', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (3,'Calle Marques de Paradas',3,'Hotel NH Sevilla','Sevilla','954901992', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (10,'Calle San Jacinto',3,'Hotel NH Malaga','Malaga','954071323', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (4,'Calle Pacifico',4,'Hotel Vincci Malaga','Malaga','952175060', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (11,'Calle Heroe de Sostoa',4,'Barcelo Malaga','Malaga','952047494', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (5,'Paseo de la Victoria',4,'Eurostars','Cordoba','957760452', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (12,'Plaza de las Tendillas',4,'H10 Palacio Colomera','Cordoba','957216800', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (6,'Av de Cadiz',3,'Exe Ciudad de Cordoba','Cordoba','957013600', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (13,'Calle Argantonio',3,'Hotel Argantonio','Cadiz','956211640', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (7,'Unnamed Road',4,'Wild House Tarifa','Cadiz','64965342', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (14,'Calle Pleamar',4,'Hipotels Gran Conil Hotel & Spa','Cadiz','856905000', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (8,'Calle BailenMotril',4,'Hotel Juleca','Jaen','953326006', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (15,'Calle Cronista Juan de la Torre',4,'Hotel RL Ciudad de Ubeda','Jaen','2333635', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (9,'Calle Magistrado Ruiz',3,'CazorlaPueblo Hotel','Jaen','639789955', true);
INSERT INTO hoteles(id,direccion,estrellas,nombre,provincia,telefono, valido) VALUES (16,'Av Juan Carlos',3,'Hotel Don Angel','Almeria','950328350', true);

INSERT INTO habitaciones(id,nhabitacion,ncamas,precio,disponible,hotel_id) VALUES (1,123,2,20,true,2);
INSERT INTO habitaciones(id,nhabitacion,ncamas,precio,disponible,hotel_id) VALUES (2,456,2,20,true,2);
INSERT INTO habitaciones(id,nhabitacion,ncamas,precio,disponible,hotel_id) VALUES (3,789,2,20,false,3);
INSERT INTO habitaciones(id,nhabitacion,ncamas,precio,disponible,hotel_id) VALUES (4,012,2,20,false,2);
INSERT INTO habitaciones(id,nhabitacion,ncamas,precio,disponible,hotel_id) VALUES (5,756,2,20,true,3);
INSERT INTO habitaciones(id,nhabitacion,ncamas,precio,disponible,hotel_id) VALUES (6,345,2,20,true,3);

INSERT INTO inscripcionhoteles(id,actividades,direccion,nombre,provincia) VALUES (2,'Spa en Sevilla','Calle Aire','INSCRIPCION1','Sevilla');
INSERT INTO inscripcionhoteles(id,actividades,direccion,nombre,provincia) VALUES (3,'Spa en Malaga','Calle Padel','INSCRIPCION2','Sevilla');


INSERT INTO comentarioshotel(id,puntuacion,mensaje,hotel_id) VALUES (1,10,'HOTEL CHULO!',75);

INSERT INTO actividades(id,direccion,nombre,descripcion,precio,valoracion,fecha,agenact_id,provincia) VALUES (1, 'Playa de Cadiz','Surf Cadiz', 'El mejor Surf en Cadiz', 20, 3,'2021-12-17',1,'Cadiz');
INSERT INTO actividades(id,direccion,nombre,descripcion,precio,valoracion,fecha,agenact_id,provincia) VALUES (2, 'Sierra de Grazalema','Senderismo', 'Increible paisaje', 1 , 4,'2021-12-17',1,'Cadiz');
INSERT INTO actividades(id,direccion,nombre,descripcion,precio,valoracion,fecha,agenact_id,provincia) VALUES (3, 'Ayuntamiento de Sevilla', 'Visita guiada', 'Sevilla es preciosa', 5, 4,'2021-12-17',2,'Sevilla');

INSERT INTO comentariosactividad(id,puntuacion,mensaje,actividad_id) VALUES (1,10,'Bonitas vistas!',1);

INSERT INTO compvuelos VALUES (1, 'Iberia', 'España', 'Madrid');
INSERT INTO compvuelos VALUES (2, 'Ryanair', 'Irlanda', 'Dublin');
INSERT INTO compvuelos VALUES (3, 'American Airlines', 'EEUU', 'Texas');

INSERT INTO users_actividades(username, actividades_id) VALUES ('enrmorvaz', 3);
INSERT INTO users_habitaciones(username, habitacion_id) VALUES ('enrmorvaz', 3);

INSERT INTO vuelos(id,billetes,destino,fecha_ida,fecha_vuelta,origen,precio,numero_plazas,compvuelo_id) VALUES (1, 2, 'Malaga', '2021-12-17', '2021-12-24', 'Sevilla', 70,1,1);
INSERT INTO vuelos(id,billetes,destino,fecha_ida,fecha_vuelta,origen,precio,numero_plazas,compvuelo_id) VALUES (2, 1, 'Almeria', '2021-12-16', '2021-12-25', 'Malaga', 40,50,2);
INSERT INTO vuelos(id,billetes,destino,fecha_ida,fecha_vuelta,origen,precio,numero_plazas,compvuelo_id) VALUES (3, 3, 'Cadiz', '2021-12-19', '2021-12-26','Cordoba', 120,32,2);
INSERT INTO vuelos(id,billetes,destino,fecha_ida,fecha_vuelta,origen,precio,numero_plazas,compvuelo_id) VALUES (4, 2, 'Jaen', '2021-12-18', '2021-12-23', 'Sevilla', 70,2,3);
INSERT INTO vuelos(id,billetes,destino,fecha_ida,fecha_vuelta,origen,precio,numero_plazas,compvuelo_id) VALUES (5, 1, 'Malaga', '2021-12-17', '2021-12-24', 'Sevilla', 69,3,3);

