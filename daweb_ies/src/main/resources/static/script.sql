-- Eliminamos tablas si existen para poder re-ejecutar el script sin errores
DROP TABLE IF EXISTS Matricula;
DROP TABLE IF EXISTS Asignatura;
DROP TABLE IF EXISTS Profesor;
DROP TABLE IF EXISTS Alumno;

CREATE TABLE Alumno(
    id SERIAL PRIMARY KEY, -- Usamos SERIAL para que el ID se genere solo
    nombre varchar(200),
    apellidos varchar(200),
    ipasen varchar(50)
);

CREATE TABLE Profesor(
    id SERIAL PRIMARY KEY,
    nombre varchar(200),
    apellidos varchar(200),
    especialidad VARCHAR(200)
);

CREATE TABLE Asignatura(
    id SERIAL PRIMARY KEY,
    nombre varchar(200),
    horas int,
    imparte int,
    constraint fk_asig_prof foreign key (imparte) references Profesor(id)
);

CREATE TABLE Matricula(
    id_mat SERIAL PRIMARY KEY, -- Importante: SERIAL para que no de error al insertar
    id_alum int,
    id_asig int,
    curso int,
    nota_media decimal(16,2),
    constraint fk_mat_alum foreign key (id_alum) references Alumno(id),
    constraint fk_mat_asig foreign key (id_asig) references Asignatura(id)
);

-- Inserciones (Nota: Al usar SERIAL, no hace falta poner el campo 'id' en el INSERT)

INSERT INTO Profesor (nombre, apellidos, especialidad) VALUES
('José Antonio', 'Martínez López', 'Bases de Datos'),
('María del Carmen', 'Ruiz Sánchez', 'Programación'),
('Francisco', 'López García', 'Sistemas Informáticos'),
('Laura', 'Sánchez Moreno', 'Entornos de Desarrollo'),
('Miguel Ángel', 'Torres Navarro', 'Lenguajes de Marcas');

INSERT INTO Alumno (nombre, apellidos, ipasen) VALUES
('Antonio', 'García Ruiz', 'ALU001'),
('María', 'López Sánchez', 'ALU002'),
('Juan', 'Pérez Martín', 'ALU003'),
('Lucía', 'Fernández Ortega', 'ALU004'),
('David', 'Romero Castro', 'ALU005'),
('Carmen', 'Navarro Díaz', 'ALU006'),
('Javier', 'Morales Vega', 'ALU007'),
('Paula', 'Jiménez Ruiz', 'ALU008'),
('Sergio', 'Torres Molina', 'ALU009'),
('Ana', 'Castillo Moreno', 'ALU010'),
('Pablo', 'Herrera León', 'ALU011'),
('Laura', 'Domínguez Ríos', 'ALU012'),
('Daniel', 'Ruiz Blanco', 'ALU013'),
('Marta', 'Gómez Serrano', 'ALU014'),
('Adrián', 'Sánchez Vega', 'ALU015'),
('Sara', 'Molina Torres', 'ALU016'),
('Diego', 'Vargas Ruiz', 'ALU017'),
('Elena', 'Martín Castro', 'ALU018'),
('Hugo', 'Fernández Ramos', 'ALU019'),
('Inés', 'Romero Navarro', 'ALU020');

INSERT INTO Asignatura (nombre, horas, imparte) VALUES
('Bases de Datos', 160, 1),
('Programación', 240, 2),
('Sistemas Informáticos', 200, 3),
('Entornos de Desarrollo', 120, 4),
('Lenguajes de Marcas', 100, 5);

INSERT INTO Matricula (id_alum, id_asig, curso, nota_media) VALUES
(1, 1, 2025, 7.50),
(1, 2, 2025, 8.00),
(2, 1, 2025, 6.25),
(2, 3, 2025, 7.80),
(3, 2, 2025, 5.50),
(3, 5, 2025, 6.75),
(4, 1, 2025, 9.00),
(4, 4, 2025, 8.25),
(5, 3, 2025, 7.10),
(5, 2, 2025, 6.40),
(6, 1, 2025, 5.90),
(7, 5, 2025, 7.30),
(8, 2, 2025, 8.90),
(9, 3, 2025, 6.60),
(10, 4, 2025, 7.70),
(11, 1, 2025, 8.10),
(12, 2, 2025, 6.80),
(13, 3, 2025, 7.90),
(14, 4, 2025, 5.60),
(15, 5, 2025, 8.30);