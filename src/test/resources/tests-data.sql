-- Datos para tests: 3 contactos, 4 teléfonos

-- Insertar contactos
INSERT INTO Contacto (id, nombre) VALUES (1, 'Ana Torres');
INSERT INTO Contacto (id, nombre) VALUES (2, 'Luis Pérez');
INSERT INTO Contacto (id, nombre) VALUES (3, 'Mia Solis');

-- Insertar teléfonos
INSERT INTO NumeroTelefono (id, codigoArea, numero) VALUES (1, '0299', '1234567');
INSERT INTO NumeroTelefono (id, codigoArea, numero) VALUES (2, '0299', '7654321');
INSERT INTO NumeroTelefono (id, codigoArea, numero) VALUES (3, '0114', '654321');
INSERT INTO NumeroTelefono (id, codigoArea, numero) VALUES (4, '0114', '234567');

-- Relacionar teléfonos con contactos
UPDATE NumeroTelefono SET contacto_id = 1 WHERE id IN (1,2);
UPDATE NumeroTelefono SET contacto_id = 2 WHERE id = 3;
UPDATE NumeroTelefono SET contacto_id = 3 WHERE id = 4;

-- Ajusto las secuencias para futuros inserts
DROP SEQUENCE Contacto_SEQ RESTRICT;
CREATE SEQUENCE Contacto_SEQ START WITH 200 INCREMENT BY 50;

DROP SEQUENCE NumeroTelefono_SEQ RESTRICT;
CREATE SEQUENCE NumeroTelefono_SEQ START WITH 250 INCREMENT BY 50;
