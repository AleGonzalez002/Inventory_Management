USE inventory_system;

INSERT INTO categorias (nombre, descripcion) VALUES
('Bebidas', 'Refrescos, jugos y agua'),
('Limpieza', 'Productos para limpieza y desinfección'),
('Alimentos', 'Comestibles de larga duración'),
('Papelería', 'Artículos de oficina y papelería');

INSERT INTO productos (nombre, descripcion, id_categoria, precio, stock) VALUES
('Coca-Cola 500ml', 'Refresco gaseoso', 1, 0.80, 50),
('Jabón líquido', 'Jabón para limpieza de manos', 2, 1.20, 30),
('Arroz 1kg', 'Grano básico empacado', 3, 1.10, 100),
('Cuaderno universitario', 'Cuaderno 100 hojas', 4, 2.50, 40);

INSERT INTO proveedores (nombre, telefono, direccion) VALUES
('Distribuidora El Sol', '7123-4567', 'Av. Central #123'),
('Productos Limpios SA', '7890-2345', 'Col. San Martín'),
('Alimentos La Granja', '7654-9876', 'Zona Industrial'),
('Papeles y Más', '7012-8899', 'Calle Reforma 56');

INSERT INTO entradas (id_producto, id_proveedor, fecha, cantidad, costo) VALUES
(1, 1, '2025-10-01', 100, 0.60),
(2, 2, '2025-10-02', 50, 0.90),
(3, 3, '2025-10-03', 200, 0.85),
(4, 4, '2025-10-04', 60, 1.90);

INSERT INTO salidas (id_producto, fecha, cantidad, destino) VALUES
(1, '2025-10-05', 20, 'Tienda Central'),
(2, '2025-10-06', 10, 'Sucursal Norte'),
(3, '2025-10-06', 30, 'Restaurante El Buen Sabor'),
(4, '2025-10-07', 15, 'Oficina Principal');

INSERT INTO inventario (id_producto, stock_actual) VALUES
(1, 80),
(2, 40),
(3, 170),
(4, 45);

INSERT INTO usuarios (nombre, usuario, contrasena, rol) VALUES
('Administrador General', 'admin', 'admin123', 'admin'),
('Empleado 1', 'empleado1', '12345', 'empleado');