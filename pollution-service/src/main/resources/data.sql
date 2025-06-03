-- Tabla con ID autoincremental
CREATE TABLE IF NOT EXISTS estaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direction VARCHAR(255),
    latitude FLOAT,
    longitude FLOAT
);

-- Elimina datos antiguos
DELETE FROM estaciones;

-- Reinicia el contador de ID
ALTER TABLE estaciones AUTO_INCREMENT = 1;

-- Inserta sin ID
INSERT INTO estaciones (direction, latitude, longitude) VALUES
('Av. Universidad 10', 39.4699, -0.3763),
('Calle Falsa 123', 39.4701, -0.3755),
('Plaza Mayor', 39.4710, -0.3770),
('Estación Central', 39.4685, -0.3782),
('Parque Natural', 39.4722, -0.3740);
