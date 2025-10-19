USE inventory_system;

INSERT INTO categorias(nombre, descripcion) VALUES
('Electrónica', 'Productos electrónicos'),
('Muebles', 'Muebles para oficina y hogar');

INSERT INTO proveedores(nombre, telefono, direccion) VALUES
('Proveedor A', '12345678', 'Calle 1'),
('Proveedor B', '87654321', 'Calle 2');

INSERT INTO sucursales(nombre, direccion) VALUES
('Sucursal Centro', 'Av. Central 123'),
('Sucursal Norte', 'Av. Norte 456');

INSERT INTO productos(nombre, descripcion, id_categoria, id_proveedor, precio) VALUES
('Laptop', 'Laptop Gamer', 1, 1, 1200.00),
('Escritorio', 'Escritorio Oficina', 2, 2, 250.00);

INSERT INTO inventario(id_producto, id_sucursal, stock) VALUES
(1, 1, 10),
(1, 2, 5),
(2, 1, 15),
(2, 2, 8);

INSERT INTO entradas(id_producto, id_sucursal, id_proveedor, cantidad, costo) VALUES
(1, 1, 1, 10, 1100.00),
(2, 2, 2, 8, 230.00);

INSERT INTO salidas(id_producto, id_sucursal, cantidad, destino) VALUES
(1, 1, 2, 'Venta Cliente A'),
(2, 2, 3, 'Venta Cliente B');

INSERT INTO usuarios (nombre, usuario, contrasena, rol) VALUES
('Administrador General', 'admin', 'Admin', 'admin'),
('Empleado', 'empleado', 'Empleado', 'empleado');