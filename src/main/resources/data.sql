INSERT INTO autor (nombre) VALUES ('Miguel de Cervantes');
INSERT INTO autor (nombre) VALUES ('George Orwell');

INSERT INTO libro (titulo, autor_id, disponible) VALUES ('El Quijote', 1, true);
INSERT INTO libro (titulo, autor_id, disponible) VALUES ('1984', 2, true);

INSERT INTO prestamo (libro_id, fecha_prestamo) VALUES (1, '2024-01-01');