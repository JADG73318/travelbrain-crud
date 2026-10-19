-- TravelBrain - Script de base de datos para el modulo de Destinos
-- Ejecutar en MySQL: SOURCE sql/travelbrain.sql;

CREATE DATABASE IF NOT EXISTS travelbrain
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE travelbrain;

CREATE TABLE IF NOT EXISTS destino (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    pais         VARCHAR(60)  NOT NULL,
    descripcion  VARCHAR(500),
    mejor_epoca  VARCHAR(80),
    CONSTRAINT uq_destino_nombre_pais UNIQUE (nombre, pais)
);

-- Datos de ejemplo
INSERT IGNORE INTO destino (nombre, pais, descripcion, mejor_epoca) VALUES
    ('Cartagena', 'Colombia', 'Ciudad amurallada con playas, historia y gastronomia caribena.', 'Diciembre a marzo'),
    ('San Andres', 'Colombia', 'Isla caribena conocida por el mar de siete colores.', 'Enero a abril');
