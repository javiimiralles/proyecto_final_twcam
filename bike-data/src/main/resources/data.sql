-- Crea la tabla de aparcamientos (en principio Spring JPA ya lo hace, pero por si acaso)
CREATE TABLE IF NOT EXISTS aparcamientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(255),
    capacidad INT,
    num_bicis INT,
    latitud FLOAT,
    longitud FLOAT
);

-- Borrar todos los registros previos
DELETE FROM aparcamientos;

-- Reinicia el contador de ID
ALTER TABLE aparcamientos AUTO_INCREMENT = 1;

-- Insertar datos de ejemplo
INSERT INTO aparcamientos (direccion, capacidad, num_bicis, latitud, longitud) VALUES
('Av. Universidad 10', 20, 5, 39.4699, -0.3763),
('Calle Falsa 123', 15, 7, 39.4701, -0.3755),
('Plaza Mayor', 30, 12, 39.4710, -0.3770),
('Estación Central', 25, 10, 39.4685, -0.3782),
('Parque Natural', 18, 6, 39.4722, -0.3740),
('Calle Doctor Fleming', 12, 4, 39.4690, -0.3745),
('Paseo de la Alameda', 22, 11, 39.4715, -0.3733),
('Avenida Blasco Ibáñez', 16, 8, 39.4725, -0.3760),
('Mercado Central', 14, 3, 39.4680, -0.3778),
('Ciudad de las Ciencias', 28, 13, 39.4709, -0.3725),
('Jardín del Turia', 10, 2, 39.4702, -0.3747),
('Museo de Bellas Artes', 19, 6, 39.4713, -0.3789),
('Plaza del Ayuntamiento', 26, 9, 39.4696, -0.3752),
('Campus Tarongers', 17, 5, 39.4727, -0.3774),
('Hospital Clínico', 21, 7, 39.4688, -0.3766);