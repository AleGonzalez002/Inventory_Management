CREATE DATABASE IF NOT EXISTS inventory_system;
USE inventory_system;

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    id_categoria INT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(150)
);

CREATE TABLE entradas (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    id_proveedor INT,
    fecha DATE DEFAULT CURRENT_DATE,
    cantidad INT NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);

CREATE TABLE salidas (
    id_salida INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    fecha DATE DEFAULT CURRENT_DATE,
    cantidad INT NOT NULL,
    destino VARCHAR(100),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    stock_actual INT DEFAULT 0,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('admin','empleado') DEFAULT 'empleado',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE VIEW vista_movimientos AS
SELECT e.id_entrada AS id_movimiento, p.nombre AS producto, e.cantidad, 'Entrada' AS tipo, e.fecha
FROM entradas e
JOIN productos p ON e.id_producto = p.id_producto
UNION ALL
SELECT s.id_salida, p.nombre, s.cantidad, 'Salida', s.fecha
FROM salidas s
JOIN productos p ON s.id_producto = p.id_producto;