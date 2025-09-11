CREATE DATABASE IF NOT EXISTS test_db;
USE test_db;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    contraseña VARCHAR(50) NOT NULL
);

INSERT INTO usuarios (nombre, contraseña) VALUES
('admin', 'admin123'),
('user', 'user123');

SELECT * FROM usuarios;